def label = "<LABEL_TO_SEARCH_FOR>"
Jenkins.instance.allItems.grep { it -> 
    it.class.name == 'com.cloudbees.opscenter.server.model.SharedSlave' 
}.findAll { it -> 
     it.getLabelString().contains(label) 
}.collect { it -> it.name } 
