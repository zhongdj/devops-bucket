jobs = Jenkins.instance.getAllItems(AbstractProject.class)
jobs.each { j ->
  println 'JOB: ' + j.fullName
  numbuilds = j.builds.size()
  if (numbuilds == 0) {
    println ' -> no build'
    return
  }
  j.builds.each{
    if(it.timestampString == '46 yr'){
      it.timestamp = it.getStartTimeInMillis()
      it.save()
    }
  }
}
