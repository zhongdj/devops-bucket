### JOBS

###### Call to REST API including the .crumb in the same call

```sh
# curl --user $USER:$APITOKEN -H $(curl --user $USER:$APITOKEN [-H "CRUB"] $SERVER/crumbIssuer/api/xml?xpath=concat\(//crumbRequestField,%22:%22,//crumb\))  $SERVER/job/hello-world-flow/build?token=codebase&cause=push
```

###### Build a job

```sh
# curl -v -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/job/JOB_NAME/build" [-H "CRUB"]
curl -v -X POST "http://admin:522c579740238f0791d49d205df47323@labs.cje.linux.crl:8183/job/pipeline_TEST/build"
```

###### Build a job WITH parameters (including hiding)

```sh
# curl -v -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/job/JOB_NAME/buildWithParameters?PARAM=value" [-H "CRUB"]
curl -v -X POST "http://admin:ba42ef7552848f33ddf6a54a688a736e@localhost:8080/job/test_HidenParameters2/buildWithParameters?HIDEN_1=updateHiden1"
```

###### create Item

```sh
# wget --verbose --auth-no-challenge --http-user=admin --header='Content-type: application/xml; charset=ISO-8859-1' --http-password=<APITOKEN> -O /dev/null --post-file=job_config-example.xml "http://<JENKINS_SERVER>/createItem?name=<NEWITEM_NAME>&token=<APITOKEN>"
> wget --verbose --auth-no-challenge --http-user=admin --header='Content-type: application/xml; charset=ISO-8859-1' --http-password=3f5491e6d8416340642da20556d758ab -O /dev/null --post-file=job_config-example.xml "http://cje1.bundle.crl:8080/createItem?name=myJobFromConsole&token=3f5491e6d8416340642da20556d758ab"
--2017-03-29 20:48:50--  http://cje1.bundle.crl:8080/createItem?name=myJobFromConsole&token=3f5491e6d8416340642da20556d758ab
Resolving cje1.bundle.crl... 127.0.0.1
Connecting to cje1.bundle.crl|127.0.0.1|:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 0
Saving to: ‘/dev/null’

/dev/null                             [ <=>                                                          ]       0  --.-KB/s    in 0s

2017-03-29 20:48:50 (0.00 B/s) - ‘/dev/null’ saved [0/0]
```

###### Stop jobs

```sh
# curl -v -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/job/JOB_NAME/BUILD_NUMBER/stop" [-H "CRUB"]
curl -v -X POST "http://admin:72dfec6bf3f93f77142f24bfd5dcbf03@localhost:8080/job/test_pipeline/3/stop"
```

###### Get Last build details

```sh
# curl "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/<PATH-TO-JOB>lastBuild/api/json? [-H "CRUB"]
> curl "https://admin:6a4e3641686f678c21c351222307bde9@master-1.unicorn.beescloud.com/job/SUPPORT-TEAM/job/USERS/job/carlosR/job/BUCKET/job/ZD-47039/lastBuild/api/json"
{"_class":"hudson.model.FreeStyleBuild","actions":[{"_class":"hudson.model.ParametersAction","parameters":[{"_class":"hudson.model.StringParameterValue","name":"HASH","value":"125121212"}]},{"_class":"hudson.model.CauseAction","causes":[{"_class":"hudson.model.Cause$UserIdCause","shortDescription":"Started by user admin","userId":"admin","userName":"admin"}]},{"_class":"com.cloudbees.plugins.deployer.DeployNowRunAction","oneClickDeployPossible":false,"oneClickDeployReady":false,"oneClickDeployValid":false},{"_class":"jenkins.metrics.impl.TimeInQueueAction"},{},{"_class":"hudson.plugins.disk_usage.BuildDiskUsageAction"},{}],"artifacts":[],"building":false,"description":null,"displayName":"#2","duration":768,"estimatedDuration":814,"executor":null,"fullDisplayName":"SUPPORT-TEAM » USERS » carlosR » BUCKET » ZD-47039 #2","id":"2","keepLog":false,"number":2,"queueId":2125,"result":"SUCCESS","timestamp":1490357817130,"url":"https://master-1.unicorn.beescloud.com/job/SUPPORT-TEAM/job/USERS/job/carlosR/job/BUCKET/job/ZD-47039/2/","builtOn":"ip-10-0-0-97","changeSet":{"_class":"hudson.scm.EmptyChangeLogSet","items":[],"kind":null},"culprits":[]}
```

######  Get the URL of the downstream job (name, color, nextBuild) details in one json file

```sh
# curl "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/<PATH-TO-PARENTJOB>/api/json?tree=downstreamProjects\[name,color,url,nextBuildNumber\]" [-H "CRUB"]
> curl "http://admin:54234152ebed7ba2e47a083cc1fc6602@localhost:8182/job/downstream/job/parent/api/json?tree=downstreamProjects\[name,color,url,nextBuildNumber\]"
{"downstreamProjects":[{"name":"children1","url":"http://localhost:8182/job/downstream/job/children1/","color":"blue","nextBuildNumber":5},{"name":"children2","url":"http://localhost:8182/job/downstream/job/children2/","color":"blue","nextBuildNumber":3}]}
```
