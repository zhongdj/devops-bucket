#!/usr/bin/env groovy
/*
Tested on: Jenkins ver. 1.651.3.1 (CloudBees Jenkins Enterprise 16.06), Pipeline: 2.4, Docker Pipeline: 1.9
Variables type String: repository (i.e: ), container(i.e: ), privateRegistryURL(i.e: ${privateRegistryURL})
From Training - Fundamentals of Jenkins Pipeline and Docker
### 1.- General job definition
<flow-definition>
<actions/>
<description/>
<keepDependencies>false</keepDependencies>
<definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition">
    <sandbox>false</sandbox>
</definition>
<triggers/>
</flow-definition>
### 2.- Pipeline script
*/
node("cd") {
 git branch: 'pipeline', url: "${repository}"
 stage('test') {
     docker.image("golang").inside('-u 0:0') {
        sh 'ln -s $PWD /go/src/docker-flow'
        sh 'cd /go/src/docker-flow && go get -t && go test --cover -v'
        sh 'cd /go/src/docker-flow && go build -v -o docker-flow-proxy'
     }
 }
 stage('build') {
     docker.build("${privateRegistryURL}/${container}")
     docker.image("${privateRegistryURL}/${container}").push()
     archive '${container}'
 }
}
checkpoint 'deploy'
node('production') {
    stage('deploy') {
     try {
        sh "docker rm -f ${container}"
     } catch(e) { }
     docker.image("${privateRegistryURL}/${container}").run("--name ${container} -p8081:80 -p8082:8080")
    }
}
/*
### Prototype Job:
node("cd") {
    git branch: 'pipeline', url:
        'http://localhost:5001/gitserver/butler/training-books-ms.git'
    stage('test') {
        docker.image("golang").inside('-u 0:0') {
            sh 'ln -s $PWD /go/src/docker-flow'
            sh 'cd /go/src/docker-flow && go get -t && go test --cover -v'
            sh 'cd /go/src/docker-flow && go build -v -o docker-flow-proxy'
        }
    }
    stage('build') {
        docker.build('localhost:5000/docker-flow-proxy')
        docker.image('localhost:5000/docker-flow-proxy').push()
        archive 'docker-flow-proxy'
    }
}
checkpoint 'deploy'
node('production') {
        stage('deploy') {
            try {
                sh 'docker rm -f docker-flow-proxy'
            } catch (e) {}
            docker.image('localhost:5000/docker-flow-proxy').run('--name docker-flowproxy -p 9081:80 -p 9082 8080')
      }
}
*/
