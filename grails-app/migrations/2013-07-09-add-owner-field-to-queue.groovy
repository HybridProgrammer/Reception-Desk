databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373379276610-1") {
		addColumn(tableName: "queue") {
			column(name: "owner_id", type: "bigint")
		}
	}

	changeSet(author: "jason (generated)", id: "1373379276610-2") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "queue", constraintName: "FK66F1911CBCBEBE8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
