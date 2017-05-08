Directorios Importantes

JBOSS_HOME\standalone\deployments --> Despliegue de aplicaciones (.WAR)
JBOSS_HOME\standalone\configuration --> Descriptores de despliegue (standalone.xml)
JBOSS_HOME\modules --> Modulos (.jar, etc...)


Archivos  Importantes
JBOSS_HOME\bin\standalone.conf.bat --> Configuración remoto

Despliegue en "caliente" sobre JBOSS_HOME\standalone\deployments
1. Borras el antiguo deploy 
2. Installl de maven te genera .war y carpeta de proyecto --> copias las carpeta de proyecto <proyecto> --> Le añades la ".war" --> <proyecto>.war
3. Creas un archivo plano del tipo <proyecto>.war.dodeploy 
