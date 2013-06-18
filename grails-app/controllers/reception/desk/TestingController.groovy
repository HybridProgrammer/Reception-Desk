package reception.desk

import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SecurityConfigType

class TestingController {
	
    def index() {
		redirect(action: "showPersons")
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
