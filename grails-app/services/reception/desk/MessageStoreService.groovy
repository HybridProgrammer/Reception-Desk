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

import grails.plugin.jms.*
import java.util.Map
import grails.converters.JSON

class MessageStoreService {
	def jmsService
	
		static transactional = true
		static exposes = ['jms']
	
		@grails.plugin.jms.Queue(name='msg.new')
		def createMessage(msg) {
			//sleep(2000) // slow it down

			def messageInstance = new Message(body:msg)
			if (messageInstance.save(flush: true)) {
				log.info "Saved message: id = ${messageInstance.id}"

				// publish an event
				jmsService.send(topic:'msgevent', [id:messageInstance.id, body:messageInstance.body])
			} else {
				log.warn 'Could not save message'
			}

			// explicitly return null to prevent unwanted replyTo queue attempt
			return null
		}

    @grails.plugin.jms.Queue(name='msg.call')
    def callMessage(msg) {

        def messageInstance = new Message(body:msg)
        def o = JSON.parse(messageInstance.body)
        def personJSON = (Person.get(o.person.id) as JSON).toString()
        def purposeJSON = (Function.get(o.purpose.id) as JSON).toString()
        if (messageInstance.save(flush: true)) {
            log.info "Saved message: id = ${messageInstance.id}"

            // publish an event
            jmsService.send(topic:'msgevent', [id:messageInstance.id, type:"msg.call", queue:messageInstance.body, person:personJSON, purpose:purposeJSON])
        } else {
            log.warn 'Could not save message'
        }

        // explicitly return null to prevent unwanted replyTo queue attempt
        return null
    }

    @grails.plugin.jms.Queue(name='msg.inLine')
    def inLineMessage(msg) {

        def messageInstance = new Message(body:msg)
        def o = JSON.parse(messageInstance.body)
        def personJSON = (Person.get(o.person.id) as JSON).toString()
        def purposeJSON = (Function.get(o.purpose.id) as JSON).toString()
        if (messageInstance.save(flush: true)) {
            log.info "Saved message: id = ${messageInstance.id}"

            // publish an event
            jmsService.send(topic:'msgevent', [id:messageInstance.id, type:"msg.inLine", queue:messageInstance.body, person:personJSON, purpose:purposeJSON])
        } else {
            log.warn 'Could not save message'
        }

        // explicitly return null to prevent unwanted replyTo queue attempt
        return null
    }

    @grails.plugin.jms.Queue(name='msg.enqueue')
    def enqueueMessage(msg) {

        def messageInstance = new Message(body:msg)
        def o = JSON.parse(messageInstance.body)
        def personJSON = (Person.get(o.person.id) as JSON).toString()
        def purposeJSON = (Function.get(o.purpose.id) as JSON).toString()
        if (messageInstance.save(flush: true)) {
            log.info "Saved message: id = ${messageInstance.id}"

            // publish an event
            jmsService.send(topic:'msgevent', [id:messageInstance.id, type:"msg.enqueue", queue:messageInstance.body, person:personJSON, purpose:purposeJSON])
        } else {
            log.warn 'Could not save message'
        }

        // explicitly return null to prevent unwanted replyTo queue attempt
        return null
    }
	
		@grails.plugin.jms.Queue(name='msg.update')
		def updateMessage(msg) {
			def messageInstance
			if(msg instanceof Map) {
				messageInstance = Message.get(msg.id)
				if(messageInstance) {
					messageInstance.body = msg.body
					if (!messageInstance.hasErrors() && messageInstance.save(flush: true)) {
						log.info "Saved message: id = ${messageInstance.id}"
					} else {
						log.warn 'Could not update message'
					}
				} else {
					log.warn "No message instance for ID ${msg.id}"
				}
			} else {
				log.warn "Could not determine what to do with ${msg}"
			}
	
			// explicitly return null to prevent unwanted replyTo queue attempt
			return null
		}
    
}
