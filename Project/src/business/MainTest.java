package business;
import Enum.AppointmentStatus;
public class MainTest {

	public static void main(String[] args) {

		//Appointment a = new Appointment();
		//a.viewAppointmentByPatient("1").stream()
		//.forEach(System.out::println);


		//Patient p = new Patient("1");
		//System.out.println("Patient Appointment");
		//p.retrieveAppointmentList().stream().forEach(System.out::println);

		Appointment a = new Appointment(2);
		a.updateAppointment(AppointmentStatus.CANCEL);

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
