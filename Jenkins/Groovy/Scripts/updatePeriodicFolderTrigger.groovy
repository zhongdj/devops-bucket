import com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger
import jenkins.model.Jenkins
import jenkins.branch.OrganizationFolder
import com.cloudbees.jenkins.plugins.bitbucket.BitbucketSCMSource


def jenkins = Jenkins.instance


jenkins.getAllItems(OrganizationFolder.class).each { folder ->

   println "[INFO] : folder name :" + folder.name
  
   def bitbucketTeam=false

   //if one of the SCM sources is a BitbucketSCMSource, we asume is a Bitbucket Team
   folder.getSCMSources().findAll{scmSource ->
      if (scmSource instanceof BitbucketSCMSource){
        bitbucketTeam=true
      }
    }

  if (bitbucketTeam){
    println "[INFO] : Updating ${folder.name}... " 
    folder.getTriggers().find {triggerEntry ->
        def key = triggerEntry.key
        if (key instanceof PeriodicFolderTrigger.DescriptorImpl){
          println "[INFO] : Current interval : " + triggerEntry.value.getInterval()
          def newInterval = new PeriodicFolderTrigger("28d")
          folder.addTrigger(newInterval)
          folder.save()
          println "[INFO] : New interval : " + newInterval.getInterval()
        }
     }
  }  
}
