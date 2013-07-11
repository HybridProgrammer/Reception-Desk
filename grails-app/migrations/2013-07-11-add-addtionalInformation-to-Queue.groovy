databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373569616604-1") {
		addColumn(tableName: "queue") {
			column(name: "additional_information", type: "longvarchar")
		}
	}
}
