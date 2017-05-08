Jenkins.instance.addNode(new hudson.slaves.DumbSlave(
    'Cyclone-System-VM1', 
    'Cyclone-System-VM1', 
    '/root/jenkins', '10', 
    hudson.model.Node.Mode.NORMAL, '', 
    new com.cloudbees.jenkins.plugins.sshslaves.SSHLauncher(
        '10.141.61.170',
        new com.cloudbees.jenkins.plugins.sshslaves.SSHConnectionDetails(
        '1ef8cf65-7495-4660-bd6d-f09eaac5c5c4',
        22,'','','','',true)), new hudson.slaves.RetentionStrategy.Always()))
