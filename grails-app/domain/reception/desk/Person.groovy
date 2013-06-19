package reception.desk

import java.io.Serializable;

class Person implements Serializable {

    static constraints = {
		name nullable: true
		zNumber nullable: true
		email nullable: true
		student nullable: true
		room nullable: true
    }
	
	Student student
	
	String name
	String zNumber
	String email
	String room 
}
