#!/usr/bin/env groovy
/*
Tested on: Jenkins ver. 2.7.20.2 (CloudBees Jenkins Enterprise 2.7.20.2-rolling)
Reference: https://wiki.jenkins-ci.org/display/JENKINS/Artifactory+-+Working+With+the+Pipeline+Jenkins+Plugin
Basic Example: https://github.com/JFrogDev/project-examples/blob/master/jenkins-pipeline-examples/maven-example/Jenkinsfile
NOTE: IT DOES NOT WORK
*/
node {

//Artifactory initialization: 'my-Artifactory' is defined in Jenkins > Manage Jenkins > Configure System 
def server = Artifactory.server('my-Artifactory')
def rtMaven = Artifactory.newMavenBuild()
def buildInfo = null
def release = input message: 'Choose release parameters', ok: 'Done',
      parameters: [
        string(defaultValue: '', description: 'Version for the release', name: 'version'),
        string(defaultValue: '', description: 'Next development version', name: 'nextVersion')
      ]
echo "Release Version=" + release.version +"| Development Version=" + release.nextVersion       
      
  // Checkout code from Github.
  stage ('Checkout') {
   checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'carlosrodlop-GH', url: 'https://github.com/mock-carlosrodlop-org/maven-helloworld']]])
  }

  //Set up artifactory repository and maven
  stage ('Artifactory and Maven configuration') {
    // Tool name from Jenkins configuration on Global Configuration
    rtMaven.tool = "Apache-Maven-3.3.1"
    //adding properties of JAVA
    rtMaven.opts = '-DreleaseVersion=' + release.version +' -DdevelopmentVersion=' + release.nextVersion
    // Where our build artifacts should be DEPLOYED TO
    rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server 
    // Where the Maven build should DOWNLOAD ITS DEPENDENCIES FROM
    rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
    buildInfo = Artifactory.newBuildInfo()
  }
  
  stage ('Exec Maven'){
    rtMaven.run pom: 'pom.xml', goals: "clean release:prepare release:perform -B", buildInfo: buildInfo
  }

  stage ('Publish build info'){
    server.publishBuildInfo buildInfo
  }
}
