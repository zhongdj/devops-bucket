import groovy.time.*
 
println "List of running jobs : "
use(TimeCategory)  {
  def delay = 10.days;
 
  // NO MODIFICATION
  Jenkins.instance.getAllItems(org.jenkinsci.plugins.workflow.job.WorkflowJob).each{
    job -> job.builds.findAll{it.isBuilding() && new Date(it.startTimeInMillis) < (new Date() - delay)}.each{
      build ->
      TimeDuration duration = TimeCategory.minus(new Date(), new Date(build.startTimeInMillis))
      print " * $job.fullName#$build.number started since $duration"
      build.doTerm()
      if(!build.isInProgress()) { println " .. Terminated" } else { println " .. wasn't terminated, might need to be killed." }
    }
  }
}
return;
