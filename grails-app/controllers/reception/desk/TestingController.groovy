package reception.desk

import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SecurityConfigType

class TestingController {
	def springSecurityService
	
    def index() {
		init()
		redirect(action: "showPersons")
	}
	
	def init() {
		//Stuff that can be run all the time
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			//password: springSecurityService.encodePassword('admin'),
			password: 'admin',
			enabled: true).save(failOnError: true)

			if (!adminUser.authorities.contains(adminRole)) {
				UserRole.create adminUser, adminRole
			}

		log.info "Created user admin/admin."
		
		//Load functions
		Function.findByName('advising') ?: new Function(name: 'advising', description: 'Advising for semester classes').save(failOnError: true)
		Function.findByName('permNHolds') ?: new Function(name: 'permNHolds', description: 'Permission/Holds Removed').save(failOnError: true)
		Function.findByName('changeMajor') ?: new Function(name: 'changeMajor', description: 'Change of Major').save(failOnError: true)
		Function.findByName('graduation') ?: new Function(name: 'graduation', description: 'Graduation Applications/Issues').save(failOnError: true)
		Function.findByName('dl') ?: new Function(name: 'dl', description: 'Distance Learning').save(failOnError: true)
		Function.findByName('petitions') ?: new Function(name: 'petitions', description: 'Petitions').save(failOnError: true)
		Function.findByName('buildingTour') ?: new Function(name: 'buildingTour', description: 'Building Tour').save(failOnError: true)
		Function.findByName('questionCollege') ?: new Function(name: 'questionCollege', description: 'General Information on our College').save(failOnError: true)
		Function.findByName('scolarships') ?: new Function(name: 'scolarships', description: 'Scholarships').save(failOnError: true)
		Function.findByName('programs') ?: new Function(name: 'programs', description: 'Dual Enrollment/Summer Programs').save(failOnError: true)
		Function.findByName('other') ?: new Function(name: 'other', description: 'Other').save(failOnError: true)
		
		def queueInstance = new Queue(isInLine: true, person: new Person(student:null, name:'student 1', email: 'student@fau.edu', zNumber:'z12345678').save(flush:true, failOnError:true), purpose: Function.last(), callNumber: CallNumber.getNext())
		if (!queueInstance.save(flush: true)) {
			//render(view: "create", model: [personInstance: personInstance])
			return
		}
		
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
			
			p = new Person(student:null, name:'admin', email: 'admin@admin.com', zNumber:'z12345678')
			p.save(flush:true, failOnError:true)
			
			def person = Person.get(1)
			//println person.
			log.info "Count of Person Table: " + p
			
			long DAY_IN_MS = 1000 * 60 * 60 * 24;
			//def queueInstance = new Queue(isInLine: true, person: new Person(student:null, name:'student 1', email: 'student@fau.edu', zNumber:'z12345678'), purpose: Function.last(), callNumber: CallNumber.getNext(), dateCreated: new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)))
			if (!queueInstance.save(flush: true)) {
				//render(view: "create", model: [personInstance: personInstance])
				return
			}
			if (!queueInstance.save(flush: true)) {
				//render(view: "create", model: [personInstance: personInstance])
				return
			}
			
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
