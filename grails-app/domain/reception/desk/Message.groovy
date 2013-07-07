package reception.desk

class Message {
	Date dateCreated
	String body
	
    static constraints = {
    }

    static mapping = {
        body type: "text"
    }
}
