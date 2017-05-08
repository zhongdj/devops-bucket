new File(System.getenv()["JENKINS_HOME"],'Jenkins-Plugins-List.txt').withWriter('utf-8') { writer ->
  Jenkins.instance.pluginManager.plugins.each { pl -> writer.writeLine pl.shortName }
}
