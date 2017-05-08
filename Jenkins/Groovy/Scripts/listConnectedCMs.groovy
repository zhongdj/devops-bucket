import com.cloudbees.opscenter.server.model.OperationsCenter

OperationsCenter.instance.getConnectedMasters().each {
    println "${it.name}"
    println " id: ${it.id}"
    println " idName: ${it.idName}"
    println " grantId: ${it.grantId}"
}
