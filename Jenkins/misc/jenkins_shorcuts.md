- Restart: `$JENKINS_URL/restart`
- UserContent (mini HTTP server to serve images, stylesheets, and other static resources that you can use from various description fields inside Jenkins.) `$JENKINS_URL/userContent/`

##### Coding
- Script console `$JENKINS_URL/script`
- Variables that are available to Shell & Bash scripts: `$Jenkins_URL/env-vars.html`
- Variables for Pipeline: `$Jenkins_URL/pipeline-syntax/globals`

##### Permissions
- Logged User Configuration `$JENKINS_URL/me/configure`
- Request Header `$Jenkins_URL/whoAmI`
- RBAC, logged User Configuration: `$Jenkins_URL/roles/whoAmI`

##### Slaves
- Launch JNLP $Slave_name `$Jenkins_URL/computer/$Slave_name`
- slave.jar Download: `http(s)://JENKINS_SERVER/jnlpJars/slave.jar`
- List of slaves that provide that given $label (as well as list of projects that use $label in their configuration): `$Jenkins_URL/label/$label`

##### CJOC - CM connectivity
- CJOC - CM connection logs: `http(s)://JENKINS_SERVER/job/CLIENT_MASTER/log`
- CJE - CJOC connection logs: `http(s)://JENKINS_SERVER/operations-center/log`

##### HA
Delete JGroups
```
rm -rf $JENKINS_HOME/jgroups
```
