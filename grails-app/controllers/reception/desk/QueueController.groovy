package reception.desk

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import java.util.Date

class QueueController {
    //static scaffold = true;

	def jmsService

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    def index() { 
		redirect(action: "lobbyList")
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit(Long id) {
        def queueInstance = Queue.get(id)
        if (!queueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "list")
            return
        }

        def personInstance = Person.get(queueInstance.person.id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
            redirect(action: "list")
            return
        }


        [queueInstance: queueInstance, personInstance: personInstance, major: Major.list(), purpose: Function.list()]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
	def list(Integer max) {
		def query = queryCurrentDayInLine(max)

        def userInstance = springSecurityService.getCurrentUser()

        [queueInstanceList: query.list(params), queueInstanceTotal: query.count(), userInstance: userInstance]
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

    @Secured(['IS_AUTHENTICATED_FULLY'])
	def call(Long id) {
		removeFromLine(id)
        def userInstance = springSecurityService.getCurrentUser()
		
		def queueInstance = Queue.get(id)
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			redirect(action: "list")
			return
		}
        queueInstance.setTimeCalled( new Date())
        queueInstance.setGoToRoom(((User)userInstance).getRoom())
        queueInstance.setOwner(((User)userInstance))

        def save = queueInstance.save()

		def jsonQueue = queueInstance as JSON
		jmsService.send(queue:'msg.call', jsonQueue.toString())
        log.info(jsonQueue)

        def personInstance = Person.get(queueInstance.person.id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "index")
            return
        }

        [queueInstance: queueInstance, personInstance: personInstance, major: Major.get(personInstance.majorId)]
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
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

    @Secured(['IS_AUTHENTICATED_FULLY'])
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

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update(Long id, Long version) {
        def personInstance
        def queueInstance = Queue.get(id)
        if (!queueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (queueInstance.version > version) {
                queueInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'queue.label', default: 'Queue')] as Object[],
                        "Another user has updated this User while you were editing")

                personInstance = Person.get(queueInstance.person.id)
                if (!personInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
                    redirect(action: "list")
                    return
                }

                render(view: "edit", model: [queueInstance: queueInstance, personInstance: personInstance, major: Major.list(), purpose: Function.list()])
                return
            }
        }

        queueInstance.properties = params.queue
        queueInstance.purpose = Function.get(params.purposeId)
        personInstance = Person.get(queueInstance.person.id)
        personInstance.properties = params.person


        if (!queueInstance.save(flush: true)) {
            personInstance = Person.get(queueInstance.person.id)
            if (!personInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
                redirect(action: "list")
                return
            }

            render(view: "edit", model: [queueInstance: queueInstance, personInstance: personInstance, major: Major.list(), purpose: Function.list()])
            return
        }

        if (!personInstance.save(flush: true)) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), id])
                render(view: "edit", model: [queueInstance: queueInstance, personInstance: personInstance, major: Major.list(), purpose: Function.list()])
                return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])
        redirect(action: "list")
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
	def removeFromLine(Long id) {
		def queueInstance = Queue.get(id)
		
		if (!queueInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Entry'), id])
			//redirect(action: "list")
			return
		}
		
		queueInstance.getOutOfLine();
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show(Long id) {
        def queueInstance = Queue.get(id)
        if (!queueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "index")
            return
        }

        def personInstance = Person.get(queueInstance.person.id)
        if (!personInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), id])
            redirect(action: "index")
            return
        }

        [queueInstance: queueInstance, personInstance: personInstance, major: Major.get(personInstance.majorId)]
    }
}
