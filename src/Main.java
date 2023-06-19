import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	// pas d'utilisation de postgresql donc false
	/**
	 * ici le booléen sert à selectionné quelles informations
	 * utiliser pour la connection (PostgreSQl, ou Mysql)
	 */
	private static final boolean POSTGRE_SQL = false;

	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRES_URL = "jdbc:postgresql://127.0.0.1:5432/base";
	private static final String POSTGRES_USERNAME = "root";
	private static final String POSTGRES_PASSWORD = "root";

	// private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306?useSSl=false";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		try {
			Connection db;
			if (Main.POSTGRE_SQL) {
				Class.forName(POSTGRES_DRIVER);
				db = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USERNAME, POSTGRES_PASSWORD);
			} else {
				Class.forName("com.mysql.cj.jdbc.Driver");
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
