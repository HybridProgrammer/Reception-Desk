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

import grails.converters.JSON
import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SecurityConfigType

class TestingController {
    /**
     * Dependency injection for the springSecurityService.
     */
	def springSecurityService

    /**
     * Dependency injection for the JMS.
     */
    def jmsService
	
    def index() {
		init()
		redirect(action: "showPersons")
	}
	
	def init() {

		 try{
		
		    def queueInstance = new Queue(isInLine: true, person: new Person(student:null, name:'student 1', email: 'student@fau.edu', zNumber:'z12345678').save(flush:true, failOnError:true), purpose: Function.last(), callNumber: CallNumber.getNext())
		    if (!queueInstance.save(flush: true)) {
			    //render(view: "create", model: [personInstance: personInstance])
			    return
		    }

             //notify the browser of a new patron
             def jsonQueue = queueInstance as JSON
             jmsService.send(queue:'msg.enqueue', jsonQueue.toString())
		

			
		} catch (Exception e) {
			e.printStackTrace();
			return
		}
	}
	
	def showPersons() {
		def people = Person.list()
		
		render {
			for (p in people) {
			   div(id: p.id, p.name)
			}
		 }
	}
	
	def showMajors() {
		def major = Major.list()
		
		render {
			for (m in major) {
			   div(id: m.id, m.displayName)
			}
		 }
	}
	
	def login() {
		
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def secure = {
		render "Secure access only"
		
	}
}
