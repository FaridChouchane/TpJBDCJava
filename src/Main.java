import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static final boolean PostgreSQL = true;

	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRES_URL = "jdbc:postgresql://127.0.0.1:5432/base";
	private static final String POSTGRES_USERNAME = "root";
	private static final String POSTGRES_PASSWORD = "root";

	private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306?useSSl=false";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		try {
			Connection db;
			if (Main.PostgreSQL) {
				Class.forName(POSTGRES_DRIVER);
				db = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USERNAME, POSTGRES_PASSWORD);
			} else {
				Class.forName(MYSQL_DRIVER);
				db = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
			}

			// connection effectuer, opération ici
			System.out.println("Hello, World!");

			db.close();
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
