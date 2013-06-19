package reception.desk

import javax.persistence.OneToMany;

class PatronController {
	def springSecurityService
	

    def index() {
		init()
		redirect(action: "guest")
	}
	
	def init() {
				
	}
	
	def guestFlow = {
		askPurpose {
			on("advising"){
				[major: Major.list()]
			}.to "getPatronInformation"		//Advising for semester classes
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
				
				log.info "value: " + params
				//def p = new Person(student:new Student(cellNumber: '', cellProvider: ''), name: params.name, email: '', zNumber: '')
				//p.save(flush:true, failOnError:true)
				
				def personInstance = new Person(params)
				if (!personInstance.save(flush: true)) {
					//render(view: "create", model: [personInstance: personInstance])
					return
				}
		
				flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
				//redirect(action: "show", id: personInstance.id)
				
				def list = Person.list()
				log.info "Count of Person Table: " + list
				
				//Add user to wait queue
				def queueInstance = new Queue(isInLine: true, person: personInstance)
				if (!queueInstance.save(flush: true)) {
					//render(view: "create", model: [personInstance: personInstance])
					return
				}
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
