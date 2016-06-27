package org.smw.db;

import java.sql.SQLException;

import org.h2.tools.Script;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public final class DatabaseCreator {

	private DatabaseCreator() {
		// prevent instantiation
	}

	public static void createTestDatabase(DBI dbi) {
		try (Handle h = dbi.open()) {

			DbOperations db = h.attach(DbOperations.class);

			db.createPersonTable();
			db.createBookTable();
			db.createBookDetailTable();
			db.createBorrowTable();

			db.addPerson(101, "Daily", "John", "+61403456789", "jd@gmail.com");
			db.addPerson(102, "Jones", "Phil", "+61404332679", "pj@gmail.com");
			db.addPerson(103, "Grill", "Bill", "+61406756677", "bg@gmail.com");
			db.addPerson(104, "Clyde", "Bonnie", "+61886756667", "bc@gmail.com");

			db.addBook(300, "The Conservative Mind", "Russell Kirk", 9780895261717L);
			db.addBook(301, "The Road to Serfdom", "F. A. Hayek", 9780226320618L);
			db.addBook(302, "Meditations", "Marcus Aurelius", 9780486298238L);
			db.addBook(303, "I, Pencil", "Leonard Read", 9781572462090L);
			db.addBook(304, "Conscience and Its Enemies", "Robert P. George", 9781610170703L);
			db.addBook(305, "Econoclasts", "Brian Domitrovic", 9781610170246L);
			db.addBook(306, "Through the Looking-Glass", "Lewis Carroll", 9785457280250L);
			db.addBook(307, "Metamagical Themas", "Douglas Hofstadter", 9780140179965L);

			db.addBookDetail(500, 300,
					"Russell Kirk's The Conservative Mind is one of the greatest contributions to twentieth-century American conservatism.\nBrilliant in every respect, from its conception to its choice of significant figures representing the history of intellectual conservatism, The Conservative Mind launched the modern American Conservative Movement when it was first published in 1953 and has become an enduring classic of political thought.");

			db.addBookDetail(501, 304,
					"\"Many in elite circles yield to the temptation to believe that anyone who disagrees with them is a bigot or a religious fundamentalist. Reason and science, they confidently believe, are on their side. With this book, I aim to expose the emptiness of that belief.\"\nFrom the introduction: "
							+ "Assaults on religious liberty and traditional morality are growing fiercer. Here, at last, is the counterattack.\n"
							+ "Showcasing the taborroweds that have made him one of America's most acclaimed and influential thinkers, Robert P. George explodes the myth that the secular elite represents the voice of reason. In fact, George shows, it is on the elite side of the cultural divide where the prevailing views frequently are nothing but articles of faith. Conscience and Its Enemies reveals the bankruptcy of these too often smugly held orthodoxies while presenting powerfully reasoned arguments for classical virtues.");

			db.addBookDetail(502, 305,
					"The definitive history of supply-side economics the most consequential economic counterrevolution of the twentieth century and an incredibly timely work that reveals the foundations of America's prosperity at a time when those very foundations are under attack.");

			db.insertBorrow(101, 300);
			db.insertBorrow(101, 301);
			db.insertBorrow(102, 302);
			db.insertBorrow(102, 303);
			db.insertBorrow(102, 304);
			db.insertBorrow(103, 305);

			// export the database to a script in target
			Script.process(h.getConnection(), "target/script.sql", "SIMPLE", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
