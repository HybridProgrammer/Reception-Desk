package reception.desk

class MessageQ {

    static constraints = {
    }
	
	static hasMany = [displayClients: DisplayClient]
	
	String message
	
}
