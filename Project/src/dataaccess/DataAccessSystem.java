package dataaccess;

import java.sql.*;


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

		private static final String dburl = "jdbc:sqlite:my.db";
		private static Connection conn = null;
		public Connection getConnection() throws SQLException {
			if (conn == null) {
				// create a connection to the database
				conn = DriverManager.getConnection(dburl);
				System.out.println("Connection to SQLite has been established.");
			}
			return conn;
		}
	}
}
