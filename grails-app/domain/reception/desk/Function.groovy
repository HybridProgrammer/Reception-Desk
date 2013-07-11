package reception.desk

import java.io.Serializable;

class Function implements Serializable {

    static constraints = {
        shortDescription nullable: true
    }
	
	String name
	String description
    String shortDescription
	
	
	def ToString = {
		return description
	}
}
