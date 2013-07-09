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

}
