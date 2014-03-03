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

import grails.converters.JSON
import java.text.SimpleDateFormat
import java.text.DateFormat
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import grails.plugins.springsecurity.Secured

class BackupController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    def index() {
        render(view: 'restore');
    }

    @Secured(['ROLE_ADMIN'])
    def export() {


        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String filename = "backup_" + dt.format(new Date()) + ".dat";

        response.setHeader "Content-disposition", "attachment; filename="+filename
        response.contentType = 'text/csv'
        response.outputStream << dumpDbToJsonString()
        response.outputStream.flush()

        //render (contentType: 'text/json', encoding: "UTF-8", text: (data as JSON).toString())

    }

    @Secured(['ROLE_ADMIN'])
    private def dumpDbToJsonString() {
        def data = [:]

        data['CallNumber'] = CallNumber.findAll()
        data['Function'] = Function.findAll()
        data['Major'] = Major.findAll()
        data['Person'] = Person.findAll()
        data['Queue'] = Queue.findAll()
        data['Role'] = Role.findAll()
        data['Student'] = Student.findAll()
        data['User'] = User.findAll()
        data['UserRole'] = UserRole.findAll()

        return (data as JSON).toString()

    }

    @Secured(['ROLE_ADMIN'])
    def restore() {

    }

    @Secured(['ROLE_ADMIN'])
    def executeImport() {
        BufferedReader br = null
        StringBuilder sb = new StringBuilder();

        if(request instanceof MultipartHttpServletRequest)
        {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
            CommonsMultipartFile f = (CommonsMultipartFile) mpr.getFile("backup");
            if(!f.empty) {
                flash.message = 'success'
                br = new BufferedReader(new InputStreamReader(f.getInputStream()));
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                restoreDatabase(sb.toString())
                render(contentType: "text/json", text: dumpDbToJsonString())
                return
            }
            else
                flash.message = 'file cannot be empty'
        }

        render(view: 'restore')
    }


    @Secured(['ROLE_ADMIN'])
    private def restoreDatabase(String backup) {
        def data = JSON.parse(backup);


        for (table in data) {
            def obj = null;
            switch(table.key) {
                case 'CallNumber':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = CallNumber.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new CallNumber(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((CallNumber)row).properties
                            obj.save()
                        }
                    }
                    break;
                case 'Function':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Function.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Function(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((Function)row).properties
                            obj.save()
                        }
                    }
                    break;
                case 'Major':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Major.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Major(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((Major)row).properties
                            obj.save()
                        }
                    }
                    break;
                case 'Person':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Person.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Person(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((Person)row).properties
                            obj.save()
                        }
                    }
                    break;

                /*case 'Role':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Role.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Role(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((Role)row).properties
                            obj.save()
                        }
                    }
                    break;*/
                case 'Student':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Student.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Student(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((Student)row).properties
                            obj.save()
                        }
                    }
                    break;
                case 'User':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = User.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new User(row)
                            obj.save()
                        }
                        else {
                            //obj.properties =  ((User)row).properties
//                            "class": "reception.desk.User",
//                            "id": 5,
//                            "accountExpired": false,
//                            "accountLocked": false,
//                            "enabled": true,
//                            "password": "ce921865d73231ad113ef624dfdd4232bd4680ce71acfe865685d55145de82a8",
//                            "passwordExpired": false,
//                            "refreshRate": 0,
//                            "room": "102F",
//                            "roomLastUpdated": null,
//                            "username": "tsorgente"
                            obj.password = ((User)row).password
                            obj.accountExpired = ((User)row).accountExpired
                            obj.accountLocked = ((User)row).accountLocked
                            obj.enabled = ((User)row).enabled
                            obj.passwordExpired = ((User)row).passwordExpired
                            obj.refreshRate = ((User)row).refreshRate
                            obj.room = ((User)row).room
                            obj.roomLastUpdated = ((User)row).roomLastUpdated
                            obj.username = ((User)row).username
                            obj.save()
                        }
                    }
                    break;
                /*case 'UrerRole':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = UserRole.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new UserRole(row)
                            obj.save()
                        }
                        else {
                            obj.properties =  ((UserRole)row).properties
                            obj.save()
                        }
                    }
                    break;*/
            }
        }

        for (table in data) {
            def obj = null;
            switch(table.key) {
                case 'Queue':
                    for (row in table.value) {
                        //Check if value exists
                        if((obj = Queue.get(row['id'])) == null) {
                            //Must create a new object
                            obj = new Queue()
                            obj.properties = ((Queue)row).properties
                            obj.purpose = Function.get(row['purpose']['id'])
                            obj.person = Person.get(row['person']['id'])
                            obj.dateCreated = DateFormat.getDateInstance().parse(row['dateCreated'])
                            obj.lastUpdated = DateFormat.getDateInstance().parse(row['lastUpdated'])
                            obj.isInLine = row['isInLine']
                            if(row['owner'] != null) obj.owner = User.get(row['owner']['id'])
                            if(row['goToRoom'] != null) obj.goToRoom = row['goToRoom']
                            if(row['timeCalled'] != null) obj.timeCalled = row['timeCalled']
                            if(row['additionalInformation'] != null) obj.additionalInformation = row['additionalInformation']
                            obj.save(failOnError: true)
                        }
                        else {
                            obj.properties =  ((Queue)row).properties
                            obj.save()
                        }
                    }
                    break;
            }
        }

        //Order: User, UserRole, Queue
        /*User.withTransaction { tx ->
            tx.properties = data['User']['id'][0].properties

        }*/

        //Check if value exists
        /*if((obj = User.get(data['User']['id'])) == null) {
            //Must create a new object
            obj = new User(row)
            obj.save()
        }
        else {
            obj.properties =  ((User)row).properties
            obj.save()
        }*/

    }
}
