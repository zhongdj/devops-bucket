#!/usr/bin/env groovy
/*
Tested on: Jenkins ver. 1.651.3.1 (CloudBees Jenkins Enterprise 16.06), Pipeline: 2.1, Active Choices Plugin 1.5.2 
*/
node {
    /*Boolean*/
    def status = input id: 'InputOK', message: 'Are you OK man?', ok: 'SUBMIT', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: true, description: 'checked=TRUE', name: 'Are you OK']]
    if (status){
        println "I feel GOOD!"
    }else{
        println "I feel miserable"
    }
    /*String*/
    def path = input id: 'DeployLocation', message: 'Where you would like to store the file', ok: 'SUBMIT', parameters: [[$class: 'StringParameterDefinition', defaultValue: '', description: 'Path to store the file', name: 'Path']]
    //not null and not empty
    if (path?.trim()) {
        // println "Storing file on " + path
        println "Storing file on ${path}"
    }
    /*String from Groovy*/
    def groovy = input id: 'Message_to_QA', message: 'Message to QA', ok: 'SUBMIT', parameters: [[$class: 'ChoiceParameter', choiceType: 'PT_MULTI_SELECT', description: 'Select a City', filterable: false, name: '', randomName: 'choice-parameter-26085183879651', script: [$class: 'GroovyScript', fallbackScript: [classpath: [], sandbox: false, script: '''return [
\'error\'
]'''], script: [classpath: [], sandbox: false, script: '''return [
\'Peru\',
\'Costa Rica\',
\'Mexico\'
]''']]]]
   def array = groovy.split(',')
   println "Selected values:"
   for (i = 0; i <array.length; i++) {
     println "${array[i]}"
   }
   /* This way does not work for pipelines
   array.each {
     println "${it}"
   }*/
} 
