package reception.desk

class QueueController {

    def index() { 
		redirect(action: "lobbyList")
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[queueInstanceList: Queue.list(params), queueInstanceTotal: Queue.count()]
	}
	
	def lobbyList(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[queueInstanceList: Queue.list(params), queueInstanceTotal: Queue.count()]
	}
}
