package reception.desk

import grails.converters.JSON

class DisplayClientController {

    def index() { }
	
	def initialize() {
		def displayClient = new DisplayClient()
		displayClient.save(flush:true, failOnError:true)
		
		render displayClient as JSON
	}
}
