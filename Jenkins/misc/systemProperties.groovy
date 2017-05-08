//----------------------------------------------------
// Update Jenkins System properties. Please see https://wiki.jenkins-ci.org/display/JENKINS/Features+controlled+by+system+properties
// You don't need to restart the instance to take the effects in case you also execute the following groovy script on the Jenkins Script Console.
//----------------------------------------------------
// F.i. -Dhudson.Util.symlinkEscapeHatch=true to false

System.setProperty("hudson.Util.symlinkEscapeHatch","false")
System.setProperty("com.cloudbees.workflow.rest.external.ChangeSetExt.resolveCommitAuthors", "false")
