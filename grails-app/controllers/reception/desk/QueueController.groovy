package reception.desk

import grails.converters.JSON
import java.util.Date

class QueueController {
	def jmsService

    def index() { 
		redirect(action: "lobbyList")
	}
	
	def list(Integer max) {
		def query = queryCurrentDayInLine(max)

        [queueInstanceList: query.list(params), queueInstanceTotal: query.count()]
	}
	
	def listAll(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		render(view: "list", model: [queueInstanceList: Queue.list(params), queueInstanceTotal: Queue.count()])
	}
	
	/**
	 * The Queue list only shows entries from the current day
	 * @param max
	 * @return
	 */
	def lobbyList(Integer max) {
        max = 200
		def query = queryCurrentDayInLine(max)

        [queueInstanceList: query.list(params), queueInstanceTotal: query.count()]
	}
	
	def queryCurrentDay(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def query = Queue.findByDateCreatedGreaterThanEquals(new Date().clearTime())
		query = Queue.where {
			dateCreated >= new Date().clearTime()
		}
		[queueInstanceList: query.list(params), queueInstanceTotal: query.count()]
	}
	
	def queryCurrentDayInLine(Integer max) {
		params.max = Math.min(max ?: 10, 200)
		def query = Queue.findByDateCreatedGreaterThanEquals(new Date().clearTime())
		query = Queue.where {
			dateCreated >= new Date().clearTime() && (isInLine == true || (hour(timeCalled) == (new Date().getHours()) && minute(timeCalled) >= (new Date().getMinutes() - 5)))
		}

        return query
		//[queueInstanceList: query.list(params), queueInstanceTotal: query.count()]
	}
	
	def call(Long id) {
		removeFromLine(id)
		
		def queueInstance = Queue.get(id)
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			redirect(action: "list")
			return
		}
        queueInstance.setTimeCalled( new Date())
        queueInstance.setGoToRoom("T-EE102A")
        def save = queueInstance.save()

		def jsonQueue = queueInstance as JSON
		jmsService.send(queue:'msg.call', jsonQueue.toString())
        log.info(jsonQueue)
		
		[queueInstance: queueInstance]
	}
	
	def delete(Long id) {
		def queueInstance = Queue.get(id)
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			redirect(action: "list")
			return
		}
		
		queueInstance.getOutOfLine();

		render(view: "show", model: [queueInstance: queueInstance])
	}
	
	def inLine(Long id) {
		def queueInstance = Queue.get(id)
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			redirect(action: "list")
			return
		}
		
		queueInstance.getInLine()

		render(view: "show", model: [queueInstance: queueInstance])
	}
	
	def removeFromLine(Long id) {
		def queueInstance = Queue.get(id)
		
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			//redirect(action: "list")
			return
		}
		
		queueInstance.getOutOfLine();
	}

    def show(Long id) {
        def queueInstance = Queue.get(id)
        if (!queueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "index")
            return
        }

        [queueInstance: queueInstance]
    }
}
