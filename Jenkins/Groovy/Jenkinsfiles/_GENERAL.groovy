#!/usr/bin/env groovy
// Email Notification Options to our Pipeline when it fails (pipeline 2.4)
node {
    // do something that fails
    //'SUCCESS' vs 'FAILURE'
    currentBuild.result = 'FAILURE'
    echo "RESULT: ${currentBuild.result}"
    step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'it.carlosrodlop@gmail.com', sendToIndividuals: true])
}
// ------------------------------------------
// withEnv (pipeline 2.4)
// Define system properties inside the array, default values will be overwritten like JAVA_HOME
// PATH+<X>, it is just human readable, it urns into adding values to the default path 
node {
  def maven = tool 'M3'
  withEnv(["JAVA_HOME" = tool 'JDK7' , "PATH+JAVA=${JAVA_HOME}/bin", "PATH+MAVEN=${maven}/bin"]) {
       echo "$JAVA_HOME , $PATH"
       sh "mvn ${config.mvnDirectory} ${config.mvnProperties}"
  }
}
// ------------------------------------------
// Parallel task (pipeline 2.4)
// Option 1
def tasks = [:]

tasks["task_1"] = {
  stage ("task_1"){    
    node('shared-agent-1.unicorn.beescloud.com') {  
        sh 'echo $NODE_NAME'
    }
  }
}
tasks["task_2"] = {
  stage ("task_2"){    
    node('shared-agent-2.unicorn.beescloud.com') {  
        sh 'echo $NODE_NAME'
    }
  }
}

parallel tasks
// Option 2
def testList = ["a", "b", "c", "d"]
def branches = [:] 

for (int i = 0; i < 4 ; i++) {
       int index=i, branch = i+1
       stage ("branch_${branch}"){ 
            branches["branch_${branch}"] = { 
                node ('label_example'){
                    sh "echo 'node: ${NODE_NAME},  index: ${index}, i: ${i}, testListVal: " + testList[index] + "'"
                }
            }
      }
}

parallel branches
