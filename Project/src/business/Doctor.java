package business;

import java.time.LocalDate;
import java.util.List;

import Enum.Role;
import Enum.Specialist;

public class Doctor extends Staff{
	public Doctor(String name, Role role, LocalDate joinDate, String contactNumber,Specialist specialist) {
		super(name, role, joinDate, contactNumber);
		this.specialist=specialist;
	} 

	public Doctor(int staffId, String name, Role role, LocalDate joinDate, String contactNumber, Specialist specialist) {
		super(staffId,name, role, joinDate, contactNumber);
		this.specialist=specialist;
	}

	private Specialist specialist; //Enum
	private List<DoctorSchedule> doctorScheduleList;
	// Getter and Setter for specialist
    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
}
