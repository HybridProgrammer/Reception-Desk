package reception.desk

class PersonController {
	
    def index() {
		def people = Person.list()	
		
		render {
			for (p in people) {
			   div(id: p.id, p.firstName)
			}
		 }
	}
}
