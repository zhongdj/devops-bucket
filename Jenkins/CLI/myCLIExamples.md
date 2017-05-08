### Help

```sh
#java -jar jenkins-cli.jar [-s JENKINS_URL] [-i PRIVATE_KEY] command [options...] [arguments...]
java -jar jenkins-cli.jar -s http://localhost:8182/ help --username harryh --password harryh
```

### Create groups
http://localhost:8182/cli/command/create-group

```sh
#java -jar jenkins-cli.jar -s JENKINS_SERVER/ create-group CONTAINER GROUP --username USERNAME --password PASSWORD
java -jar jenkins-cli.jar -s http://localhost:8182/ create-group root groupByCli --username harryh --password harryh
```

### Crete a new view 

```sh
# Creates a new view by reading stdin as a XML configuration. --> Enter xml by < file.xml
java -jar jenkins-cli.jar -s http://localhost:8080/ create-view new_view < view.xml
```

### Create a job 

Job/Create is needed. As a prerequisite for the below example, to create folder4 item, folder3 needs to be created beforehand 

```sh
java -jar jenkins-cli.jar -s http://127.0.0.1:8080/ create-job folder3/folder4 --username admin --password admin  < 006-folder-creator.xml
```

### Update parameter

```sh
java -jar /Users/carlosrodlop/Downloads/jenkins-cli.jar -s http://localhost:8080/ -noKeyAuth set-build-parameter PARAMETER updateValue
java -jar /Users/carlosrodlop/Downloads/jenkins-cli.jar -s http://localhost:8080/ -noKeyAuth set-build-parameter PARAMETER updateValue --username admin --password admin
java -jar /Users/carlosrodlop/Downloads/jenkins-cli.jar -s http://localhost:8080/job/freestyle_test/5/ -noKeyAuth set-build-parameter PARAMETER updateValue --username admin --password admin
java -jar /Users/carlosrodlop/Downloads/jenkins-cli.jar -s http://localhost:8080/ -noKeyAuth set-build-parameter PARAMETER2 updateValue2 --username admin --password admin
```

### Build a job

```sh
#java -jar jenkins-cli.jar -s $JENKINS_URL build $JOBNAME --username $USERID --password $PASS
java -jar jenkins-cli.jar -s http://labs.cje.linux.crl:8183/ build backup_ZD35395_ex --username admin --password admin
```

#### Build a paremetrized job

```sh
java -jar jenkins-cli.jar -s http://localhost:8080/ build zd36582 --username admin --password admin -p PARAMATER1=new1 -p PARAMATER2=new2
```
