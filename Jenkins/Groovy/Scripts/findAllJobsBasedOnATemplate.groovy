import com.cloudbees.hudson.plugins.modeling.ModelList
import com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.InstanceFromJobTemplate
import com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobPropertyImpl

def jobCounter = 0
def templateJobCounter = 0
def templateName = "ZD38132-JobTemplate1"
Jenkins.instance.items.findAll { job ->
  jobCounter++
  // check if the job is a instance of a template
  def action = job.getAction(com.cloudbees.hudson.plugins.modeling.impl.entity.LinkToTemplateAction.class)
  if (action != null) {
    println "Job `${job.name}` using the `${action.instance.model.name}` template"
    if (action.instance.model.name == templateName) {
        templateJobCounter++
    }
  }
}
println ""
println "- Total of jobs: ${jobCounter}"
println "- Jobs using the `${templateName}` template: ${templateJobCounter}"
