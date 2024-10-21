package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.SQLException;
import java.util.List;

import dataaccess.Dao;

public class MainTest {

	public static void main(String[] args) {

		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Dao dao = new StaffDAO();

		try {
			dataAccess.read(dao);
			List<Staff> results = (List<Staff>) dao.getResults();
			results.forEach(System.out::println); // Prints the results
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
