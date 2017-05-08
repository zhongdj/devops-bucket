### General

#### Where is my JRE installed

1. Find java
```sh
> whereis java
java: /usr/bin/java /etc/java /usr/lib64/java /usr/share/java /usr/share/man/man1/java.1
```
2. Find symbolic link for Java 1
```sh
> cd /usr/bin/; ls -ltr java
lrwxrwxrwx 1 root root 22 Jan 8 07:01 java -> /etc/alternatives/java
```
3. Find symbolic link for Java 2
```sh
> cd /etc/alternatives/; ls -ltr java
lrwxrwxrwx 1 root root 30 Jan 8 07:01 java -> /usr/java/jdk1.7.0_25/bin/java
-bash-4.1$
```

#### Update JRE version installed in your OS

```sh
> sudo update-alternatives --config java
There are 2 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                     Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-8-oracle/jre/bin/java   5         auto mode
  1            /usr/lib/jvm/java-7-oracle/jre/bin/java   2         manual mode
* 2            /usr/lib/jvm/java-8-oracle/jre/bin/java   5         manual mode

Press <enter> to keep the current choice[*], or type selection number:
```

#### Check for JAVA JDK install on your OS
```sh
> dpkg --list | grep -i jdk
ii  oracle-java7-installer                        7u80+7u60arm-0~webupd8~1                 all          Oracle Java(TM) Development Kit (JDK) 7
ii  oracle-java8-installer                        8u101+8u101arm-1~webupd8~2               all          Oracle Java(TM) Development Kit (JDK) 8

```

### keytool

Ref: [KeyStore Tutorial][]

### Keystore description

It could be a `keystore.jks` or `cacerts`
```sh
> keytool -list -v -keystore cacerts > /tmp/certificates.log ; atom /tmp/certificates.log
```

### Check if an cert alias is included into your Keystore

Not sure of the alias:
```sh
> keytool -list -v -keystore cacerts | grep slac
NNombre de Alias: slack
Propietario: CN=*.slack.com, O="Slack Technologies, Inc", L=San Francisco, ST=California, C=US
  DNSName: *.slack.com
  DNSName: slack.com

...
```
then
```sh
> keytool -list -v -keystore cacerts -alias slack
Nombre de Alias: slack
Fecha de Creación: 17-nov-2016
Tipo de Entrada: trustedCertEntry

Propietario: CN=*.slack.com, O="Slack Technologies, Inc", L=San Francisco, ST=California, C=US
Emisor: CN=GeoTrust SSL CA - G3, O=GeoTrust Inc., C=US
...
```

### To test connection by using a Keystore

```sh
> jrunscript -Djavax.net.ssl.trustStore=/Library/Java/JavaVirtualMachines/jdk1.8.0_74.jdk/Contents/Home/jre/lib/security/cacerts -Djavax.net.ssl.trustStorePassword=changeit -e "println(new java.net.URL(\"https://bitbucket.beescloud.com\").openConnection().getResponseCode())"

Java.lang.RuntimeException: javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

Caused by: javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

... 

Caused by: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

... 
	
Caused by: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

```


[KeyStore Tutorial]:https://www.javacodegeeks.com/2014/07/java-keystore-tutorial.html
