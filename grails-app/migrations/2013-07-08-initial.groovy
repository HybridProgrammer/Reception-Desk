databaseChangeLog = {

	changeSet(author: "jason (generated)", id: "1373302280948-1") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-2") {
		createTable(tableName: "display_client") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "display_clienPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-3") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-4") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-5") {
		createTable(tableName: "message") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "messagePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "body", type: "longvarchar") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-6") {
		createTable(tableName: "messageq") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "messageqPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "message", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-7") {
		createTable(tableName: "messageq_display_client") {
			column(name: "messageq_display_clients_id", type: "bigint")

			column(name: "display_client_id", type: "bigint")
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-8") {
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

			column(name: "student_id", type: "bigint")

			column(name: "z_number", type: "varchar(255)")
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-9") {
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

			column(name: "go_to_room", type: "varchar(255)")

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

			column(name: "time_called", type: "timestamp")
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-10") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-11") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-12") {
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

	changeSet(author: "jason (generated)", id: "1373302280948-13") {
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

			column(name: "passwd", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(defaultValue: "120000", name: "refresh_rate", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "room", type: "varchar(255)")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-14") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-15") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-23") {
		createIndex(indexName: "authority_uniq_1373302280832", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-24") {
		createIndex(indexName: "username_uniq_1373302280843", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "jason (generated)", id: "1373302280948-16") {
		addForeignKeyConstraint(baseColumnNames: "display_client_id", baseTableName: "messageq_display_client", constraintName: "FK6F76497DC8F9C0C9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "display_client", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-17") {
		addForeignKeyConstraint(baseColumnNames: "messageq_display_clients_id", baseTableName: "messageq_display_client", constraintName: "FK6F76497D101B7384", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "messageq", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-18") {
		addForeignKeyConstraint(baseColumnNames: "student_id", baseTableName: "person", constraintName: "FKC4E39B555DA9D624", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "student", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-19") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "queue", constraintName: "FK66F191110E0F210", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-20") {
		addForeignKeyConstraint(baseColumnNames: "purpose_id", baseTableName: "queue", constraintName: "FK66F19117C0AE2AA", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "function", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-21") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46ABABA77F0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "jason (generated)", id: "1373302280948-22") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A5FE53BD0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

    include file: '2013-07-08-add-test-to-user.groovy'
    include file: '2013-07-08-drop-test-to-user.groovy'
    include file: '2013-07-09-add-owner-field-to-queue.groovy'
    include file: '2013-07-11-add-shortDescription-to-Function.groovy'
}
