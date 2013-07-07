package reception.desk

import java.io.Serializable;

class Queue implements Serializable  {

    static constraints = {
        goToRoom nullable: true
        timeCalled nullable: true
    }
	
	Date dateCreated
	Date lastUpdated
    Date timeCalled
	Boolean isInLine
	Integer callNumber
    String goToRoom
	
	Person person
	Function purpose
	
	def getOutOfLine() {
		isInLine = false
		this.save()
	}
	
	def getInLine() {
		isInLine = true
		this.save()
	}
}
