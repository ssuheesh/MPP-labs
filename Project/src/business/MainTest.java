package business;

import Enum.AppointmentStatus;

import java.time.LocalDate;

public class MainTest {

	public static void main(String[] args) {



		//Patient p = new Patient("1");
		//System.out.println("Patient Appointment");
		//p.retrieveAppointmentList().stream().forEach(System.out::println);

		Appointment a = new Appointment(1);
		a.updateAppointment(AppointmentStatus.CHECKOUT);

		Appointment b = new Appointment(
				LocalDate.of(2024, 12, 6), // date
				2, // slotOfTheDay
				"Routine Checkup", // visitReason
				AppointmentStatus.BOOKED // status
		);
		 b.bookAppointment();

		 b.viewAppointmentByPatient("1").stream()
				 .forEach(System.out::println);

		b.viewAllAppointment().stream()
				.forEach(System.out::println);

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
}
