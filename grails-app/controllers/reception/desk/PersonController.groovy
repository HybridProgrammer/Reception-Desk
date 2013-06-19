package reception.desk

class PersonController {
	
    def index() {
		redirect(action: "list")
	}
	
	def create() {
		[personInstance: new Person(params)]
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[personInstanceList: Person.list(params), personInstanceTotal: Person.count()]
	}
	
	def edit(Long id) {
		def personInstance = Person.get(id)
		if (!personInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Person'), id])
			redirect(action: "list")
			return
		}

		[personInstance: personInstance]
	}
	
	def update(Long id, Long version) {
		def personInstance = Person.get(id)
		if (!personInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (personInstance.version > version) {
				personInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'person.label', default: 'Person')] as Object[],
						  "Another user has updated this User while you were editing")
				render(view: "edit", model: [personInstance: personInstance])
				return
			}
		}

		personInstance.properties = params

		if (!personInstance.save(flush: true)) {
			render(view: "edit", model: [personInstance: personInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
		redirect(action: "show", id: personInstance.id)
	}
	
	def save() {
		def personInstance = new Person(params)
		if (!personInstance.save(flush: true)) {
			render(view: "create", model: [personInstance: personInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
		redirect(action: "show", id: personInstance.id)
	}
	
	def show(Long id) {
		def personInstance = Person.get(id)
		if (!personInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "index")
			return
		}

		[personInstance: personInstance]
	}
}
