databaseChangeLog = {

	changeSet(author: "jheithof (generated)", id: "1371839225487-1") {
		createTable(tableName: "call_number") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "call_numberPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "current", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "n_wraps", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-2") {
		createTable(tableName: "function") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "functionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-3") {
		createTable(tableName: "major") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "majorPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "department", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "display_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "short_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-4") {
		createTable(tableName: "person") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "personPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)")

			column(name: "major_id", type: "bigint")

			column(name: "name", type: "varchar(255)")

			column(name: "room", type: "varchar(255)")

			column(name: "student_id", type: "bigint")

			column(name: "z_number", type: "varchar(255)")
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-5") {
		createTable(tableName: "queue") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "queuePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "call_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "is_in_line", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "purpose_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-6") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-7") {
		createTable(tableName: "stats") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "statsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-8") {
		createTable(tableName: "student") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "studentPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cell_number", type: "varchar(255)")

			column(name: "cell_provider", type: "varchar(255)")

			column(name: "n_credits", type: "varchar(255)")
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-9") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-10") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-11") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-17") {
		createIndex(indexName: "authority_uniq_1371839225185", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-18") {
		createIndex(indexName: "username_uniq_1371839225206", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-12") {
		addForeignKeyConstraint(baseColumnNames: "student_id", baseTableName: "person", constraintName: "FKC4E39B555DA9D624", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "student", referencesUniqueColumn: "false")
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-13") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "queue", constraintName: "FK66F191110E0F210", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-14") {
		addForeignKeyConstraint(baseColumnNames: "purpose_id", baseTableName: "queue", constraintName: "FK66F19117C0AE2AA", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "function", referencesUniqueColumn: "false")
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-15") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46ABABA77F0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "jheithof (generated)", id: "1371839225487-16") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A5FE53BD0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
