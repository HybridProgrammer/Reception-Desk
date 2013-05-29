package reception.desk

class PatronController {

    def index() {
		redirect(action: "guest")
	}
	
	def guestFlow = {
		askPurpose {
			on("advising").to "getPatronInformation"		//Advising for semester classes
			on("permNHolds").to "enterWaitQueue"			//Permission/Holds Removed
			on("changeMajor").to "enterWaitQueue"			//Change of Major
			on("graduation").to "enterWaitQueue"			//Graduation Applications/Issues
			on("dl").to "enterWaitQueue"					//Distance Learning
			on("petitions").to "enterWaitQueue"				//Petitions
			on("buildingTour").to "enterWaitQueue"			//Building Tour
			on("questionCollege").to "enterWaitQueue"		//General Information on our College
			on("scolarships").to "enterWaitQueue"			//Scholarships
			on("programs").to "enterWaitQueue"				//Dual Enrollment/Summer Programs
			on("other").to "enterWaitQueue"					//Other
		}
		
		getPatronInformation {
			on("save").to "enterWaitQueue"
			on("cancel").to "askPurpose"
		}
		enterWaitQueue {}
	}
}
