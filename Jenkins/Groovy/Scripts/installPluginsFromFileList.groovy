new File("myPlugin.txt").readLines.each(it ->
InstallPluginCommand icp = new InstallPluginCommand();
icp.name = it;
icp.run();
}
