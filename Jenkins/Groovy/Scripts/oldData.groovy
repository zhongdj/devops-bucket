def d = [:]
d.putAll(Jenkins.instance.getAdministrativeMonitor("OldData").data)
d.each { obj, v ->
println obj.fullDisplayName?:obj.displayName?:obj.name
println v
println '------------'
}
return
