import reception.desk.User
import reception.desk.Role
import reception.desk.UserRole
import reception.desk.Person
import reception.desk.Student
import reception.desk.Stats
import reception.desk.Major

class BootStrap {
	def springSecurityService
	
    def init = { servletContext ->
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
		} catch (Exception e) {
			e.printStackTrace();
			return
		}
    }
    def destroy = {
    }
}
