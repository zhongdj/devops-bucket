String label = 'ds-1'  
Jenkins j = Jenkins.getInstance()

println "Slave01 is offline: " + j.getNode(label)?.getComputer().isOffline().toString()
