databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373555876962-1") {
		addColumn(tableName: "user") {
			column(name: "room_last_updated", type: "timestamp")
		}
	}
}
