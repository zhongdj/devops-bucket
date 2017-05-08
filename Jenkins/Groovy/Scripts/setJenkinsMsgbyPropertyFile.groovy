Properties prop = new Properties()
propertiesFile = null
def msg = ""
def jenkins = null 

try {
	propertiesFile = new File("test.properties")

	propertiesFile.withInputStream {
    	prop.load(it)
        msg = prop."MSG1"
        println("Message to send to CM: " + msg)
        jenkins = Jenkins.instance
        jenkins.setSystemMessage(msg)
        jenkins.save()
    }
  
} catch (IOException ex) {
	println("ERROR :" + ex.printStackTrace())
}// Note: Groovy closes files for you
