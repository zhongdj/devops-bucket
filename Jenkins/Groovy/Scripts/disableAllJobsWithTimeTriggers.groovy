import hudson.triggers.TimerTrigger


def jenkins= Jenkins.instance
// TRUE for disabling jobs / FALSE for listing jobs that match the selecting criteria
def DRY=false

jenkins.getAllItems(AbstractProject.class).findAll{j ->
	j.getTriggers().each { trDes, tr ->
    if (trDes.class.canonicalName == 'hudson.triggers.TimerTrigger.DescriptorImpl')
	   println "[INFO] Item name: " + j.name	
       if (DRY){
        	j.disabled=true
        	j.save()
    	}
    	
    }
}
