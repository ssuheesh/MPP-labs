package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dataaccess.Dao;

public class MainTest {

	public static void main(String[] args) {

		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Dao dao = new AdminDAO();
		Connection con = null;
		try {
			dataAccess.createTables(dao);
			con = dataAccess.getConnection();
			dataAccess.read(dao);
			dataAccess.read(dao);
			List<Admin> results = (List<Admin>) dao.getResults();
			results.forEach(System.out::println); // Prints the results
		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					//do nothing
				}
			}
        }

	}
}
