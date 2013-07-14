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

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class RoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(['ROLE_ADMIN'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [roleInstanceList: Role.list(params), roleInstanceTotal: Role.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [roleInstance: new Role(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def roleInstance = new Role(params)
        if (!roleInstance.save(flush: true)) {
            render(view: "create", model: [roleInstance: roleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), roleInstance.id])
        redirect(action: "show", id: roleInstance.id)
    }

    @Secured(['ROLE_ADMIN'])
    def show(Long id) {
        def roleInstance = Role.get(id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "list")
            return
        }

        [roleInstance: roleInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def roleInstance = Role.get(id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "list")
            return
        }

        [roleInstance: roleInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def update(Long id, Long version) {
        def roleInstance = Role.get(id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (roleInstance.version > version) {
                roleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'role.label', default: 'Role')] as Object[],
                          "Another user has updated this Role while you were editing")
                render(view: "edit", model: [roleInstance: roleInstance])
                return
            }
        }

        roleInstance.properties = params

        if (!roleInstance.save(flush: true)) {
            render(view: "edit", model: [roleInstance: roleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), roleInstance.id])
        redirect(action: "show", id: roleInstance.id)
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def roleInstance = Role.get(id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "list")
            return
        }

        try {
            roleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
            redirect(action: "show", id: id)
        }
    }
}
