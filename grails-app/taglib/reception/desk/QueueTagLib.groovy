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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class QueueTagLib {
	def waitTime = { attrs, body ->
		Queue queueInstance = attrs.queueInstance
		Calendar c1 = queueInstance.dateCreated.toCalendar();
		Calendar c2 = Calendar.getInstance();
		
		Long minutes = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 60000;
		Long waitInHours = minutes / 60;
		Long waitInMinutes = minutes - (waitInHours * 60);
		
		out << body() << String.format("%02d", waitInHours) + ":" + String.format("%02d", waitInMinutes);
	}

    def getPersonJSON = { attrs, body ->
        Queue queueInstance = attrs.queueInstance
        Person p = Person.get(queueInstance.person.id)

        out << body() << (p as JSON).toString();
    }

    def getPurposeJSON = { attrs, body ->
        Queue queueInstance = attrs.queueInstance
        Function f = Function.get(queueInstance.purpose.id)

        out << body() << (f as JSON).toString();
    }

    def workingWith = { attrs, body ->
        Queue queueInstance = attrs.queueInstance
        def text = ""

        if(queueInstance.owner != null) {
            text = queueInstance.owner.username.toString()
        }

        out << body() << text;
    }

    /**
     * return the major based on queueInstance
     */
    def major = { attrs, body ->
        Queue queueInstance = attrs.queueInstance
        Person p = queueInstance.person

        if(p.majorId == null){
            out << body() << ""
            return
        }
        Major major = Major.get(p.majorId)


        out << body() << major.shortName;
    }

}
