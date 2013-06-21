package reception.desk

import java.io.Serializable;
import java.util.Date;

/**
 * The call number will be reset to zero at the start of each new day
 * current will reset to zero if it reaches Interger.max
 * nWraps keeps track of the number of times current wraps around. 
 * 
 * @author Jason Heithoff
 *
 */
class CallNumber implements Serializable {

    static constraints = {
    }

	
	Date dateCreated
	Date lastUpdated
	Integer current = 0
	Integer nWraps = 0
	
	
	/**
	 * Returns the next value for CallNumber based on date
	 * Increments current to current + 1. If it reaches Integer max we will reset to zero
	 */
	static getNext() {
		def cn = CallNumber.findByDateCreatedGreaterThanEquals(new Date().clearTime()) ?: new CallNumber().save(failOnError: true)
		
		if(cn.getCurrent() +1 > Integer.MAX_VALUE) {
			cn.setCurrent(0);
			cn.setnWraps(cn.getnWraps()+1); //We assume we won't go over Integer.MAX_VALUE ^ 2 visitors in a single day. 
		}
		
		cn.setCurrent(cn.getCurrent() +1)
		cn.save()
		return cn.getCurrent()
		
	}
}
