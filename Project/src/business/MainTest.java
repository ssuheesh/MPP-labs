package business;

import Enum.AppointmentStatus;

import java.time.LocalDate;
import java.util.Scanner;

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
                break;
        }

        scanner.close();

/*
		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		Dao dao = new AdminDAO();
		Connection con = null;
		try {
			con = dataAccess.getConnection();
			dataAccess.createTables(dao);
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
*/
    }

    public static void ReceptionistTask() {
        System.out.println("Enter the option " +
                "\n View Schedule:1 " +
                "\n Book Appointment:2 " +
                "\n View Appointment:3 " +
                "\n Update Appointment:4");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                Receptionist.viewSchedule();
                break;
            case 2:
                Receptionist.bookAppointment(Receptionist.askAppointment());
                break;
            case 3:
                Receptionist.viewAppointment();
                break;
            case 4:
                Receptionist.updateAppointment();
                break;
            default:
                break;
        }
    }
}
