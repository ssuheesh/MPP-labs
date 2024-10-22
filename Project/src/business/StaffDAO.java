package business;

import dataaccess.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import Enum.Role;
import Enum.Specialist;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

public class StaffDAO implements Dao {
	private String queryString;
	private String insertUpdateQueryString;
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

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public void setInsertUpdateQueryStringQueryString(String queryString) {
		this.insertUpdateQueryString = queryString;
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

		
			if (role == Role.DOCTOR && specialistStr != null && !specialistStr.isEmpty()) {
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

	@Override
	public String getInsertSql() {
		return this.insertUpdateQueryString;
	}

	@Override
	public void setParameters(PreparedStatement pstmt) throws SQLException {
		if (pstmt.toString().toUpperCase().startsWith("INSERT")) {
//			pstmt.setInt(1, staff.getStaffId() != null ? staff.getStaffId(): null);
			pstmt.setString(1, staff.getName() != null ? staff.getName() : null);
			pstmt.setString(2,staff.getRole() != null ? staff.getRole().toString() : null);
			pstmt.setString(3, staff.getJoinDate() != null ? staff.getJoinDate().toString() : null);
			pstmt.setString(4, staff.getContactNumber() != null  ? staff.getContactNumber() : null);
			if(staff instanceof Doctor) {
			pstmt.setString(5, ((Doctor) staff).getSpecialist() != null ? ((Doctor) staff).getSpecialist().toString() : null);
		}}
	}
	public Staff getStaffByStaffId(int staffId) {
		Optional<Staff> staff= viewAllStaff().stream().filter(x->x.getStaffId()==staffId).findFirst();
        return staff.orElse(null);
	}
	public List<Staff> viewAllStaff() {
		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Connection con = null;
		List<Staff> results = new ArrayList<>();


		try {
			con = dataAccess.getConnection();
			this.setQueryString("SELECT * from Staff");
			dataAccess.read(this);
			results.addAll(allStaff);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					//do nothing
				}
			}
		}

		return results;
	}

	public boolean createStaff(Staff staff) {
		boolean flag = false;
		DataAccess dataAccess = DataAccessFactory.getDataAccess();

		try {
			this.staff = staff;

			if(staff instanceof Doctor) {

				this.setInsertUpdateQueryStringQueryString("INSERT INTO STAFF" +
						"(name,role,joinDate,contactNumber,specialist)" +
						" VALUES " +
						"(?,?,?,?,?)");
			}
			else if(staff instanceof Staff) {
				this.setInsertUpdateQueryStringQueryString("INSERT INTO STAFF" +
						"(name,role,joinDate,contactNumber)" +
						" VALUES " +
						"(?,?,?,?)");
			}
			dataAccess.write(this);
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
		}
		return flag;
	}
}
