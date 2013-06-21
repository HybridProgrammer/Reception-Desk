package reception.desk

class QueueController {

    def index() { 
		redirect(action: "lobbyList")
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[queueInstanceList: Queue.list(params), queueInstanceTotal: Queue.count()]
	}
	
	/**
	 * The Queue list only shows entries from the current day
	 * @param max
	 * @return
	 */
	def lobbyList(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def query = Queue.findByDateCreatedGreaterThanEquals(new Date().clearTime())
		query = Queue.where {
			dateCreated >= new Date().clearTime()
		}
		[queueInstanceList: query.list(params), queueInstanceTotal: query.count()]
	}
}
