package org.smw.db;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Database {

	private final DBI dbi;

	@Autowired
	public Database(DataSource ds) {
		dbi = new DBI(ds);
	}

	public Handle handle() {
		return dbi.open();
	}

}
