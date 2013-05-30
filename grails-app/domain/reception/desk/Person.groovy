package reception.desk

import java.io.Serializable;

class Person implements Serializable {

    static constraints = {
		firstName nullable: true
		lastName nullable: true
		zNumber nullable: true
		email nullable: true
		student nullable: true
    }
	
	Student student
	
	String firstName
	String lastName 
	String zNumber
	String email 
}
