databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373302616596-1") {
		addColumn(tableName: "user") {
			column(name: "test", type: "varchar(255)")
		}
	}
}
