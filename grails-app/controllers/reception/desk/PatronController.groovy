package reception.desk

import javax.persistence.OneToMany;
import grails.converters.JSON

class PatronController {
	def springSecurityService
    def jmsService
	

    def index() {
		redirect(action: "showMenu")
	}
	
	def showMenu(Integer max) {
		params.max = Math.min(max ?: 25, 100)
        params.sort = "description"
		[functionInstanceList: Function.list(params), functionInstanceTotal: Function.count()]
	}
	
	def guestFlow = {
		askPurpose {
			action {
				log.info "params: " + params
				log.info "flow: " + flow
				log.info "flash: " + flash
				log.info "session: " + session
				
				if(params.button != null) {
					def f = Function.findByDescription(params.button)
					flow.purpose = f
					"${f.name}"()
				}
			}
			on("success").to "displayMenu"	
			on("advising"){
				[major: Major.list()]
			}.to "getPatronInformation"		//Advising for semester classes
            on("preProf"){
                [major: Major.list()]
            }.to "getPatronInformation"     //Pre-Professional Engineering
            on("tutoring"){
                [major: Major.list()]
            }.to "getPatronInformation"     //Tutoring
			on("permNHolds"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Permission/Holds Removed
			on("changeMajor"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Change of Major
			on("graduation"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Graduation Applications/Issues
			on("dl"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Distance Learning
			on("petitions"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Petitions
			on("buildingTour"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Building Tour
			on("questionCollege"){
                [major: Major.list()]
            }.to "getPatronInformation"		//General Information on our College
			on("scolarships"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Scholarships
			on("programs"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Dual Enrollment/Summer Programs
			on("other"){
                [major: Major.list()]
            }.to "getPatronInformation"		//Other
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
				def queueInstance = new Queue(isInLine: true, person: personInstance, purpose: flow.purpose, callNumber: CallNumber.getNext())
				if (!queueInstance.save(flush: true)) {
					//render(view: "create", model: [personInstance: personInstance])
					return
				}

                //notify the browser of a new patron
                def jsonQueue = queueInstance as JSON
                jmsService.send(queue:'msg.enqueue', jsonQueue.toString())

                [queueInstance: queueInstance]
				//long DAY_IN_MS = 1000 * 60 * 60 * 24;
				//queueInstance.setDateCreated(new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)))
			}.to "showCallNumber"
			on("cancel").to "askPurpose"
		}
//		savePatronInformation {
//			action {
//				log.info "Saving Patron Information to Database"
//			}
//			on("success").to "enterWaitQueue"
//		}
		showCallNumber {
			on("success").to "askPurpose"
            on("return").to "askPurpose"
		}
		displayPersons {
			redirect(controller: "Person", action: "index")
		}
		displayMenu {
			redirect(action: 'showMenu')
		}
	}
}
