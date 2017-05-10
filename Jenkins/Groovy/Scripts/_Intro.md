#### Find Items at Root level vs Folder level

```java
def jenkins = Jenkins.instance
//Do not use 'job' between folders
def pItem2Find = "FolderX/FolderY"
 
//Printing jobs a ROOT Level
jenkins.items.findAll{job ->
  println "[INFO]: Root: "+ job.name
}
 
//Printing jobs at Folder Level
jenkins.getItemByFullName(pItem2Find).findAll{job ->
  println "[INFO]: Target Folder name: "+ job.name
  job.items.findAll{jobChild ->
     println "[INFO]: $pItem2Find:" + jobChild.name
  }
}
```

#### Find Items using a Plugin

Find all projects using the GHPRB trigger and print their name and branches in Script Console:

```java
Jenkins.instance.getAllItems(AbstractProject.class).each { p ->
  p.getTriggers().values().each { t ->
    if (t.class.name.endsWith('GhprbTrigger')) {
      println p.fullName
      t.whiteListTargetBranches.each { println "    " + it.branch }
    }
  }
}
return
```

####  Get descriptor Class by type

```java
def descriptor = Jenkins.instance.getDescriptorByType(org.jenkinsci.plugins.ghprb.GhprbTrigger.DescriptorImpl.class)
```

#### Crendentials

##### List Crendentials

```java
com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(com.cloudbees.plugins.credentials.common.IdCredentials.class,Jenkins.instance,hudson.security.ACL.SYSTEM)
```

##### List Crendentials provider

```java
println(ExtensionList.lookup(com.cloudbees.plugins.credentials.CredentialsProvider.class))
```

TcpSlaveAgentListener.CLI_HOST_NAME="<cjoc-ip-address>"
