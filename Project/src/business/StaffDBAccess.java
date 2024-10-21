package business;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Enum.Role;
import Enum.Specialist;

public class StaffDBAccess {

/*
	public static void main(String[] args) {
		Staff s = new Doctor();
		s.setName("Dr. Alex");
		s.setRole(Role.DOCTOR);
		s.setContactNumber("123-456-7890");
		s.setJoinDate(LocalDate.now());

		if (s instanceof Doctor) {
			Doctor doctor = (Doctor) s; // Downcasting
			doctor.setSpecialist(Specialist.FAMILY_PHYSICIAN);
			InsertStaff(doctor);
		} else {
			InsertStaff(s);
		}

	}
*/
	
	
		public static void main(String[] args)
		{
			List<Staff> staffList = getAllStaff();
	        for (Staff staff : staffList) {
	            printStaffDetails(staff);
	        }
		}
	public static void InsertStaff(Staff s) {

		
		Properties properties = new Properties();

		// Load properties file
		try (InputStream input = StaffDBAccess.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String url = properties.getProperty("db.url");

		String insertSQL = "INSERT INTO Staff( name, role, contactNumber, joinDate, specialist) VALUES( ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

			// Set values for placeholders
			//pstmt.setInt(1, s.getStaffId()); // Uncomment and ensure staffId is set
			pstmt.setString(1, s.getName());
			pstmt.setString(2, s.getRole().toString());
			pstmt.setString(3, s.getContactNumber());
			pstmt.setObject(4, s.getJoinDate());

			if (s instanceof Doctor) {
				Doctor doctor = (Doctor) s; // Downcasting
				pstmt.setString(5, doctor.getSpecialist().toString());
			}

			pstmt.executeUpdate();
			System.out.println("Data inserted successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        Properties properties = new Properties();

        // Load properties file
        try (InputStream input = StaffDBAccess.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return staffList;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return staffList;
        }

        String url = properties.getProperty("db.url");
        String selectSQL = "SELECT * FROM Staff";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int staffId = rs.getInt("staffId");
                String name = rs.getString("name");
                Role role = Role.valueOf(rs.getString("role"));
                String contactNumber = rs.getString("contactNumber");
                LocalDate joinDate =LocalDate.parse(rs.getString("joinDate"));

                String specialist = rs.getString("specialist");

                Staff staff;
                if (specialist != null && !specialist.isEmpty()) {
                	Specialist s =Specialist.valueOf(specialist);
                    Doctor doctor = new Doctor(name,role,joinDate,contactNumber,s);
                    staff = doctor;
                } else {
                    staff = new Staff(name,role,joinDate,contactNumber);
                }
                staff.setStaffId(staffId);
                staff.setName(name);
                staff.setRole(role);
                staff.setContactNumber(contactNumber);
                staff.setJoinDate(joinDate);

                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
	}
	public static void printStaffDetails(Staff staff) {
        System.out.println("Staff ID: " + staff.getStaffId());
        System.out.println("Name: " + staff.getName());
        System.out.println("Role: " + staff.getRole());
        System.out.println("Contact Number: " + staff.getContactNumber());
        System.out.println("Join Date: " + staff.getJoinDate());
        if (staff instanceof Doctor) {
            Doctor doctor = (Doctor) staff;
            System.out.println("Specialist: " + doctor.getSpecialist());
        }
        System.out.println("------");
	}
}
