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
