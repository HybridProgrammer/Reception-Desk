package reception.desk

import java.io.Serializable;

class Student implements Serializable {

    static constraints = {
		cellNumber nullable: true
		cellProvider nullable: true
    }
	
	static belongsTo = [person:Person]
	
	static mapping = {
		person cascade: 'all-delete-orphan'
	}
	
	String cellNumber
	String cellProvider
	
}
