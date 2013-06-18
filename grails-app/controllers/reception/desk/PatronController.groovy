package reception.desk

import javax.persistence.OneToMany;

class PatronController {
	def springSecurityService
	

    def index() {
		init()
		redirect(action: "guest")
	}
	
	def init() {
		//Stuff that can be run all the time
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			password: springSecurityService.encodePassword('admin'),
			enabled: true).save(failOnError: true)

			if (!adminUser.authorities.contains(adminRole)) {
				UserRole.create adminUser, adminRole
			}

		log.info "Created user admin/admin."
		
		//Init can only be called once
		Stats statInit = Stats.find { name == 'init' }
		if(statInit != null) {
			log.info "init has already executed. Nothing to do."
			return
		}
		
		def stat = new Stats(name: 'init', value: 1)
		stat.save()
		try {
			//Database Data
			//Check that Major table is empty
			log.info "Number of Majors: " + Major.count()
			if(Major.count() == 0) {
				def m = new Major(displayName: 'Civil Engineering', shortName: 'CIV', department: 'CEGE').save()
				m = new Major(displayName: 'Geomatics Engineering', shortName: 'GEO', department: 'CEGE').save()
			}
			
			//Test Data
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
