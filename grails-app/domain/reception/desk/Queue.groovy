package reception.desk

import java.io.Serializable;

class Queue implements Serializable  {

    static constraints = {
    }
	
	Date dateCreated
	Date lastUpdated
	Boolean isInLine
	Person person
}
