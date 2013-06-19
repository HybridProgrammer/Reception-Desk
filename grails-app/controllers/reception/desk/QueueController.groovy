package reception.desk

class QueueController {

    def index() { 
		redirect(action: "list")
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[queueInstanceList: Queue.list(params), queueInstanceTotal: Queue.count()]
	}
}
