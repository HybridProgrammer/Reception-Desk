package reception.desk

import javax.persistence.OneToMany;

class PatronController {

    def index() {
		init()
		redirect(action: "guest")
	}
	
	def init() {
		try {
			def p = new Person(student:new Student(cellNumber: '', cellProvider: ''), name: 'Test', email: 'test@test.com', zNumber: 'z12345678')
			p.save(flush:true, failOnError:true)	
			
			def person = Person.get(1)
			//println person.
			log.info "Count of Person Table: " + p
		} catch (Exception e) {
			e.printStackTrace();
			return
		}
		
	}
	
	def guestFlow = {
		askPurpose {
			on("advising").to "getPatronInformation"		//Advising for semester classes
			on("permNHolds").to "enterWaitQueue"			//Permission/Holds Removed
			on("changeMajor").to "enterWaitQueue"			//Change of Major
			on("graduation").to "enterWaitQueue"			//Graduation Applications/Issues
			on("dl").to "enterWaitQueue"					//Distance Learning
			on("petitions").to "enterWaitQueue"				//Petitions
			on("buildingTour").to "enterWaitQueue"			//Building Tour
			on("questionCollege").to "enterWaitQueue"		//General Information on our College
			on("scolarships").to "enterWaitQueue"			//Scholarships
			on("programs").to "enterWaitQueue"				//Dual Enrollment/Summer Programs
			on("other").to "enterWaitQueue"					//Other
			on("viewPersons").to"displayPersons"
		}
		
		getPatronInformation {
			on("save") {
				log.info "Saving Patron Information to Database"
				
				log.info "value: " + params.name
				def p = new Person(student:new Student(cellNumber: '', cellProvider: ''), name: params.name, email: '', zNumber: '')
				p.save(flush:true, failOnError:true)
				
				def list = Person.list()
				log.info "Count of Person Table: " + list
				//!flow.person.validate() ? error() : success()
			}.to "enterWaitQueue"
			on("cancel").to "askPurpose"
		}
//		savePatronInformation {
//			action {
//				log.info "Saving Patron Information to Database"
//			}
//			on("success").to "enterWaitQueue"
//		}
		enterWaitQueue {
			action {
				log.info "Entering Wait Queue"	
			}
			on("success").to "askPurpose"
		}
		displayPersons {
			redirect(controller: "Person", action: "index")
		}
	}
}
