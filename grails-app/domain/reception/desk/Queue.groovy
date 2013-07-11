package reception.desk

import java.io.Serializable;

class Queue implements Serializable  {

    static constraints = {
        goToRoom nullable: true
        timeCalled nullable: true
        owner nullable: true
        additionalInformation nullable: true
    }

    static mapping = {
        additionalInformation type: "text"
    }
	
	Date dateCreated
	Date lastUpdated
    Date timeCalled
	Boolean isInLine
	Integer callNumber
    String goToRoom
    String additionalInformation
    User owner
	
	Person person
	Function purpose
	
	def getOutOfLine() {
		isInLine = false
		this.save()
	}
	
	def getInLine() {
		isInLine = true
        goToRoom = null
        owner = null
        timeCalled = null
		this.save()
	}
}
