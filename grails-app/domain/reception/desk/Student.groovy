/*
 * Copyright 2013 Jason Heithoff
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package reception.desk

import java.io.Serializable;

class Student implements Serializable {

    static constraints = {
		cellNumber nullable: true
		cellProvider nullable: true
		nCredits nullable: true
    }
	
	static belongsTo = [person:Person]
	
	static mapping = {
		person cascade: 'all-delete-orphan'
	}
	
	String cellNumber
	String cellProvider
	String nCredits
	
}
