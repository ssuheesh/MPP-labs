package business;

import dataaccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import Enum.Role;
import Enum.Specialist;

public class StaffDAO implements Dao {

	private Staff staff;
	private List<Staff> allStaff;

	public List<Staff> getAllStaff() {
		return allStaff;
	}

	public StaffDAO() {
	}

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
			
			int staffId = rs.getInt("staffId");
			String name = rs.getString("name");
			Role role = Role.valueOf(rs.getString("role"));
			String contactNumber = rs.getString("contactNumber");
			LocalDate joinDate = LocalDate.parse(rs.getString("joinDate"));
			String specialistStr = rs.getString("specialist");

		
			if (specialistStr != null && !specialistStr.isEmpty()) {
				Specialist specialist = Specialist.valueOf(specialistStr);
				Doctor doctor = new Doctor(staffId,name, role, joinDate, contactNumber, specialist);
				staff = doctor;
			} else {
				staff = new Staff(staffId,name, role, joinDate, contactNumber);
			} 

			allStaff.add(staff);
		}
	}

	@Override
	public List<?> getResults() {
		return allStaff;
	}
}
