package reception.desk

class FunctionController {

    static scaffold = true

    def index() {
		redirect(action: "list")
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 25, 100)
		[functionInstanceList: Function.list(params), functionInstanceTotal: Function.count()]
	}
}
