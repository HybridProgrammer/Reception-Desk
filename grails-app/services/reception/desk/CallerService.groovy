package reception.desk


class CallerService {
	
	static atmosphere = [mapping:'/atmosphere/caller']

    def serviceMethod() {

    }
	
	def onRequest = { event ->
		// We should only have GET requests here
		log.info "onRequest, method: ${event.request.method}"
	  
		// Mark this connection as suspended.
		event.suspend()
	 }
	
	
	def onStateChange = { event ->
		if (event.message) {
		  log.info "onStateChange, message: ${event.message}"
	   
		  if (event.isSuspended()) {
			event.resource.response.writer.with {
			  write "parent.callback('${event.message}');"
			  flush()
			 }
			event.resume()
		  }
		}
	  }
	
	
}
