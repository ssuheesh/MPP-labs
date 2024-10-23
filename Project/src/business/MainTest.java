package business;

import Enum.AppointmentStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

import Enum.Role;
import Tasks.AdminTask;
import Tasks.ReceptionistTask;
import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

public class MainTest {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       /* try {
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            Dao dao = new AdminDAO();
            Connection con = null;
            con = dataAccess.getConnection();
            dataAccess.createTables(dao);
        } catch(SQLException e) {
            e.printStackTrace();
        }*/
        System.out.println("Enter the Role \n Doctor:1 \n Receptionist:2 \n Admin:3");
        String num = scanner.nextLine();

        switch (num) {
            case "1":
                break;
            case "2":
                ReceptionistTask.mainTask();
                break;
            default:
                AdminTask.mainTask();
                break;
        }

        scanner.close();

/*
		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Dao dao = new AdminDAO();
		PatientDAO patientDao = new PatientDAO();
		Connection con = null;
		try {
			con = dataAccess.getConnection();
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
*/
    }



}
