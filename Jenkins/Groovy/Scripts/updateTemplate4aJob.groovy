import com.cloudbees.hudson.plugins.modeling.ModelList
import com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.InstanceFromJobTemplate
import com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobPropertyImpl

//Create a a Instance from the Job Template "newJobTemplateName"
def newJobTemplateName = 'ZD38132-JobTemplate1'
def inst = new InstanceFromJobTemplate(ModelList.get().getItem(newJobTemplateName))

//Assign that template to a job 
def job2updateAssociatedTemplate = 'ZD38132-Job1-Template1'
Jenkins.instance.getItemByFullName(job2updateAssociatedTemplate)
  .addProperty(new JobPropertyImpl(inst))

inst.save() // apply template
