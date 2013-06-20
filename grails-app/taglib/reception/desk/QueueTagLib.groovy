package reception.desk

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

}
