import com.cloudbees.hudson.plugins.folder.*
import com.cloudbees.jenkins.plugins.foldersplus.*

//Parameters
def targetFolder="job/support-team/job/Bucket/job/jobs2delete/"
def dryMode= true //true, if you want to delete this

def folderItems
def itemCounter=0

// Getting all the items for   
Jenkins.instance.getAllItems(Folder.class).findAll{i ->
  if (i.url==targetFolder){
        folderItems = i.getItems()
    }
}  

if (!dryMode) {
    println "[INFO] Candidates to get deleted:"
} else {
    println "[INFO] The following items will get deleted:"
}  

for(item in folderItems){
  if (item instanceof hudson.model.AbstractProject){
    println "[INFO] Item name: " + item.name + 
    itemCounter ++;
    if (dryMode) {
      item.delete()
    }  
  }
}

if (itemCounter > 0) println "Total: " + itemCounter
