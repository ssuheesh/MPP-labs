package business;

public class MainTest {

	public static void main(String[] args) {
		StaffDAO dao= new StaffDAO();
		dao.getAllStaff();
		
		for(Staff s:StaffDBAccess.getAllStaff())
		StaffDBAccess.printStaffDetails(s); 

	}

}
