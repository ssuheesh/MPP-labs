package dataaccess;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DataAccessSystem implements DataAccess {
	//package level access
	DataAccessSystem() {}
	public void read(Dao dao) throws SQLException {
		String query = dao.getSql();
		Connection con = null;
		try {
			con = (new ConnectManager()).getConnection();
			
			Statement stmt = con.createStatement();
			
			System.out.println("the query: "+ query);
			ResultSet rs = stmt.executeQuery(query);
			dao.unpackResultSet(rs);
		} finally {
			if(con != null) {
				try {
				 con.close();
				} catch(Exception e) {
					//do nothing
				}
			}
		}
	}
	
	public void write(Dao dao) throws SQLException {
		//same idea
	}
	
	
	
	public static class ConnectManager {

		private static Connection conn = null;

	    public Connection getConnection() throws SQLException {
	        if (conn == null) {
	            // Load properties file
	            Properties properties = new Properties();
	            try (InputStream input = ConnectManager.class.getClassLoader().getResourceAsStream("config.properties")) {
	                if (input == null) {
	                    System.out.println("Sorry, unable to find config.properties");
	                    return null;
	                }
	                properties.load(input);
	            } catch (IOException e) {
	                e.printStackTrace();
	                return null;
	            }

	            String dburl = properties.getProperty("db.url");
	            if (dburl != null) {
	                // Create a connection to the database
	                conn = DriverManager.getConnection(dburl);
	                System.out.println("Connection to SQLite has been established.");
	            } else {
	                System.out.println("Database URL not found in config.properties");
	            }
	        }
	        return conn;
	    
		}
	}
}