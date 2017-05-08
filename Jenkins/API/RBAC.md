### RBAC

###### Grant permissions to a role

Jenkins permission http://javadoc.jenkins-ci.org/hudson/model/Item.html

```sh
# curl -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/roles/MYROLE/grantPermissions?permissions=permission1,permission2" [-H "CRUB"]
>curl -X POST "http://harryh:39c41b887c9b25c8671b05068fd2f193@localhost:8182/roles/myrole/grantPermissions?permissions=hudson.model.Item.Discover,hudson.model.Hudson.Administer"
```

###### Create to a group

```sh
# curl -v -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/groups/createGroup?name=GROUP" [-H "CRUB"]
curl -v -X POST "http://admin:c02c43585c95162355739dbbd8c361c7@localhost:8182/groups/createGroup?name=myTestGroup" -H ".crumb:78fb0c8e445f376ae57aecd2be7d4e1b"
```

###### addMember to a group

```sh
# curl -v -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/groups/GROUP/addMember?name=USERUID" [-H "CRUB"]
curl -v -X POST "http://admin:c02c43585c95162355739dbbd8c361c7@localhost:8182/groups/myTestGroup/addMember?name=testUser" -H ".crumb:78fb0c8e445f376ae57aecd2be7d4e1b"
```

###### Grant a role to a group

```sh
# curl -X POST "http(s)://<USERNAME>:<TOKEN>@<JENKINS_SERVER>/groups/MYGROUPNAME/grantRole?role=ROLEID&offset=NUMBER&inherited=BOOLEAN" [-H "CRUB"]
curl -X POST "http://harryh:39c41b887c9b25c8671b05068fd2f193@localhost:8182/groups/groupByCli/grantRole?role=myrole&offset=0&inherited=true"
```
