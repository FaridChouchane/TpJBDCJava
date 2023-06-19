import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
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
				Class.forName(MYSQL_DRIVER);
				db = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
			}
			Statement statement = db.createStatement();

			// créé la db si elle n'existe pas
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TPJDBCJAVA CHARACTER SET utf8;");
			statement.executeUpdate("use TPJDBCJAVA;");
			// Utilisateurs
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Utilisateurs (" +
					"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
					"number VARCHAR(50) NOT NULL," +
					"lastname VARCHAR(50) NOT NULL," +
					"firstname VARCHAR(50) NOT NULL," +
					"email VARCHAR(255) NOT NULL," +
					"login VARCHAR(50) NOT NULL," +
					"password VARCHAR(50) NOT NULL" +
					");");
			// Clients
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Clients (" +
					"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
					"number VARCHAR(50) NOT NULL," +
					"lastname VARCHAR(50) NOT NULL," +
					"firstname VARCHAR(50) NOT NULL," +
					"email VARCHAR(255) NOT NULL," +
					"adresse VARCHAR(255) NOT NULL" +
					");");
			// Fournisseurs
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Fournisseurs (" +
					"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
					"number VARCHAR(50) NOT NULL," +
					"lastname VARCHAR(50) NOT NULL," +
					"email VARCHAR(255) NOT NULL," +
					"adresse VARCHAR(255) NOT NULL" +
					");");
			// Articles
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Articles (" +
					"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
					"number VARCHAR(50) NOT NULL," +
					"sold INT NOT NULL," + // vendu, oui / non
					"name VARCHAR(50) NOT NULL," +
					"description VARCHAR(255) NOT NULL" +
					");");

			// statement.executeUpdate("INSERT INTO Utilisateurs VALUES (DEFAULT, 'Client
			// n°1', 'Lastname', 'Firstname', 'monsuper@email.com', 'monSuperLogin',
			// 'fakePassword')");
			// statement.executeUpdate("INSERT INTO Utilisateurs VALUES (DEFAULT, 'Client n°2', 'Alexis', 'isHere', 'alexis@m.z', 'admin', '')");
			// connection effectuer, opération ici
			ResultSet result = statement.executeQuery("SELECT * FROM Utilisateurs;");
			ResultSetMetaData meta = result.getMetaData();

			System.out.print(meta.getTableName(1) + ": ");
			int imax = meta.getColumnCount();
			for (int i = 0; i < imax; i++) {
				System.out.print(meta.getColumnName(i + 1) + ((i != imax - 1) ? ", " : "\n"));
			}

			while (result.next()) {
				String id = result.getString("id");
				String number = result.getString("number");
				String lastname = result.getString("lastname");
				String firstname = result.getString("firstname");
				String email = result.getString("email");
				String login = result.getString("login");
				String password = result.getString("password");
				System.out.println(id + ", " + number + ", " + lastname + ", " + firstname + ", " + email + ", " + login
						+ ", " + password);
			}
			System.out.println("Hello, World!");

			db.close();
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
