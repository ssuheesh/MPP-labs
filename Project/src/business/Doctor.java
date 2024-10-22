package business;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Enum.Role;
import Enum.Specialist;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

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

	public List<DoctorSchedule> getDoctorScheduleList() {
		return doctorScheduleList;
	}
	public List<DoctorSchedule> getDoctorScheduleById(int doctorId) {
		DataAccess dataAccess = DataAccessFactory.getDataAccess();
		DoctorScheduleDAO doctorScheduleDAO=new DoctorScheduleDAO();
		this.doctorScheduleList.addAll(doctorScheduleDAO.getDoctorScheduleByDoctorId(String.valueOf(doctorId)));
		return this.doctorScheduleList;
	}
}
