package business;

import dataaccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StaffDAO implements Dao {

	private Staff staff;
	private List<Staff> allStaff;

	public List<Staff> getAllStaff() {
		return allStaff;
	}

	public StaffDAO(){}

	 public void setStaff(Staff p) {
	        staff = p;
	    }

	@Override
	public String getSql() {
		return "SELECT * from Staff";
	}

	@Override
	public void unpackResultSet(ResultSet rs) throws SQLException {
		allStaff = new ArrayList<>();
		while (rs.next()) {
		//.add(new Staff(rs.getString("id"), rs.getString("name")));
		}
	}

	@Override
	public List<?> getResults() {
		return allStaff;
	}
}
