#!/usr/bin/env groovy
/*
Tested on: CloudBees Jenkins Enterprise 2.32.2.6-rolling, Pipeline: 2.4
Job: https://master-1.unicorn.beescloud.com/job/support-team/job/Pipeline/job/ZD-46441
Note: The content is overwritten for each build
*/
def fileContent=""

@NonCPS
def readFileFunc (file){
    def fileContent=""
    def readfile = readFile file: file
    readfile.eachLine { line ->
        fileContent+=line+"\n"
    }
    return fileContent
}

node {
    def stratTime = new Date()
    // Creating a File on Root
    def file = 'buildTime.txt'
    // Creating a File on Root
    def exist = fileExists file
    writeFile file: file, text: 'stratTime='+stratTime
    if (!exist){
       echo "Creating File for the first time..."
    } else {
       echo "Reading File..."
       fileContent = readFileFunc (file)
       println fileContent
    }
    sh 'ls -ltR'
}
