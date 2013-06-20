package reception.desk

import java.io.Serializable;

class Function implements Serializable {

    static constraints = {
    }
	
	String name
	String description
	
	
	def ToString = {
		return description
	}
}
