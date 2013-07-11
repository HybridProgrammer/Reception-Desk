databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373552352622-1") {
		addColumn(tableName: "function") {
			column(name: "short_description", type: "varchar(255)")
		}
	}
}
