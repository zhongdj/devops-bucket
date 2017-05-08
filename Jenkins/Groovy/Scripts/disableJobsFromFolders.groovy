import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*
import com.cloudbees.jenkins.plugins.foldersplus.*

// targetFolder URL
def targetFolder="job/FolderX/job/FolderY/"
def folderItems
// TRUE for enabling jobs 
def DRY=true

// Getting all the items from targetFolder
Jenkins.instance.getAllItems(Folder.class).findAll{i ->
  if (i.url==targetFolder){
        folderItems = i.getItems()
    }
}

// Filter the item by a particular class: hudson.model.AbstractProject
for(item in folderItems){
  if (item instanceof hudson.model.AbstractProject){
    if (DRY){
        item.disabled=false
        item.save()
    }
    println "[INFO] Item name: " + item.name
  }
}
