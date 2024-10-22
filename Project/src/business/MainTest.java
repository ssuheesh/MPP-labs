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

public class MainTest {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the Role \n Doctor:1 \n Receptionist:2 \n Admin:3");
        int num = scanner.nextInt();

        switch (num) {
            case 1:
                break;
            case 2:
                ReceptionistTask();
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

    public static void ReceptionistTask() {
        System.out.println("Enter the option " +
                "\n View Schedule:1 " +
                "\n Book Appointment:2 " +
                "\n View Appointment:3 " +
                "\n Update Appointment:4" +
                "\n View Appointment by patientId:5");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                Receptionist.viewSchedule();
                break;
            case 2:
                Receptionist.bookAppointment(Receptionist.askAppointment());
                break;
            case 3:
                Receptionist.viewAppointment().forEach(System.out::println);
                break;
            case 4:
                Receptionist.updateAppointment();
                break;
            case 5:
                Receptionist.viewAppointmentByPatient().forEach(System.out::println);
            default:
                break;
        }
    }

}
