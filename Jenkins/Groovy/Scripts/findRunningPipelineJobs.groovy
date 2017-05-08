import groovy.time.*

println "List of running jobs : "
use(TimeCategory)  {
  Jenkins.instance.getAllItems(org.jenkinsci.plugins.workflow.job.WorkflowJob).each{
    job -> job.builds.findAll{it.isBuilding() && new Date(it.startTimeInMillis) < (new Date() - 1.day) }.each{
      build -> 
      TimeDuration duration = TimeCategory.minus(new Date(), new Date(build.startTimeInMillis))
      println "* $job.fullName#$build.number started since $duration"
    }
  }
}
return;
