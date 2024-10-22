package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dataaccess.Dao;

public class MainTest {

	public static void main(String[] args) {

		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Dao dao = new AdminDAO();
		PatientDAO patientDao = new PatientDAO();
		Connection con = null;
		try {
			dataAccess.createTables(dao);
			dataAccess.createTables(patientDao);
			con = dataAccess.getConnection();

			//Read Admin data
			dataAccess.read(dao);
			List<Admin> results = (List<Admin>) dao.getResults();
			results.forEach(System.out::println); // Prints the results

			//Add new patient
			String uniquePatientId = "P" + System.currentTimeMillis();
			Patient newPatient = new Patient(uniquePatientId, "Test", "Test", "1234567890", LocalDate.of(1990, 1, 1), Patient.GenderType.FEMALE, "123 Main St");
			patientDao.addPatient(newPatient);

			//Read patient data
			dataAccess.read(patientDao);
			List<Patient> patientResults = patientDao.getResults();
			patientResults.stream().forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
//			if(con != null) {
//				try {
//					con.close();
//				} catch(Exception e) {
//					//do nothing
//				}
//			}
        }

	}
}
