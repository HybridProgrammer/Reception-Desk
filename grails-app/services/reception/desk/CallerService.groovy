/*
 * Copyright 2013 Jason Heithoff
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
