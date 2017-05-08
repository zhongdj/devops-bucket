#!/usr/bin/env groovy
/*
Tested on: Jenkins ver. 2.7.20.2 (CloudBees Jenkins Enterprise 2.7.20.2-rolling)
NOTE: release:perform does not work. ERROR: [INFO] [ERROR] Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy (default-deploy) on project example: Deployment failed: repository element was not specified in the POM inside distributionManagement element or in -DaltDeploymentRepository=id::layout::url parameter
*/
def release = null

node{

  stage ('Requesting info'){
    release = input message: 'Choose release parameters', ok: 'Done',
      parameters: [
        string(defaultValue: '', description: 'Version for the release', name: 'version'),
        string(defaultValue: '', description: 'Next development version', name: 'nextVersion')
      ]
    echo "Release Version=" + release.version +"| Development Version=" + release.nextVersion 
  } 
       
  stage ('Checkout') {
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'carlosrodlop-GH', url: 'https://github.com/mock-carlosrodlop-org/maven-helloworld']]])
  } 
    
  stage ('Build & Deploy') {
    withMaven(
        maven: 'Apache-Maven-3.3.1', // Maven installation declared in the Jenkins "Global Tool Configuration"
        mavenSettingsConfig: '38d821ea-f55b-4bb8-8ce3-7388afc02433', // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
        mavenLocalRepo: '.repository') {
 
      // Run the maven build
      sh "mvn release:prepare release:perform -B -Dusername=carlosrodlop -DskipTests" +
        (release?.version?.trim() ? " -DreleaseVersion=" + release.version : '') +
        (release?.nextVersion?.trim() ? " -DdevelopmentVersion=" + release.nextVersion : '') +
        " -DaltDeploymentRepository=central::default::http://localhost:8081/artifactory/libs-release" // this info comes from maven settings.xml (Jenkins/resources/configFiles/T47527_v2/settings.xml)
    } // withMaven will discover the generated Maven artifacts, JUnit reports and FindBugs reports
  }
}
