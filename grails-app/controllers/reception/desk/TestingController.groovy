package reception.desk

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
}
