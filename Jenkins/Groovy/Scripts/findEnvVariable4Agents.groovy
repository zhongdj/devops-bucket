def instance = Jenkins.getInstance()
def globalNodeProperties = instance.getGlobalNodeProperties()
def envVarsNodePropertyList = globalNodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)
if ( envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0) {
	newEnvVarsNodeProperty = new hudson.slaves.EnvironmentVariablesNodeProperty()
	globalNodeProperties.add(newEnvVarsNodeProperty)
	envVars = newEnvVarsNodeProperty.getEnvVars()
} else {
	envVars = envVarsNodePropertyList.get(0).getEnvVars()
}

for (Map.Entry<String, String> entry : envVars)
{
    println(entry.getKey() + ">>>" + entry.getValue());
}
