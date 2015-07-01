package mybase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBbase {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String PROTOCOL = "jdbc:derby:";
	private static final String DB_NAME = "data/stockdb";
	private static DBbase instance = null;
	private static Connection connection;
	private static Statement statement;
	
	private DBbase(){
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		statement = connect();
	}
	
	public static DBbase Instance(){
		if(instance == null){
			instance = new DBbase();
		}
		return instance;
	}
	

	
	public Statement connect(){
		try { 
			connection = null;
			Properties props = new Properties();
			connection=DriverManager.getConnection(PROTOCOL + DB_NAME+ ";create=true");
			System.out.println("create and connect to helloDB");
			connection.setAutoCommit(false);
			Statement s = connection.createStatement();
			return s;
		}
		catch (SQLException e) {

		}
		return null;
	}

	public static Connection getConnection() {
		return connection;
	}

	public static Statement getStatement() {
		return statement;
	}
	
}
