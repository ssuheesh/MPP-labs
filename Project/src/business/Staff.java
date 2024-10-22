package business;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Enum.Role;

public class Staff {
    static StaffDAO dao = new StaffDAO();
	private Integer staffId;
	private String name;
	private Role role; //Enum
	private LocalDate joinDate;
	private String contactNumber;
	
	public Staff(String name, Role role,LocalDate joinDate,String contactNumber) {
		this(null,name,role,joinDate,contactNumber);
	}
 

	public Staff(Integer staffId, String name, Role role, LocalDate joinDate, String contactNumber) {
		this.staffId=staffId;
		this.name=name;
		this.role=role;
		this.joinDate=joinDate;
		this.contactNumber=contactNumber; 
	}
 

	// Getter and Setter for staffId
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for role
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Getter and Setter for joinDate
    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    // Getter and Setter for contactNumber
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public static List<Staff> viewAllStaff() {
        return dao.viewAllStaff();
    }

    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", joinDate=" + joinDate +
                ", contactNumber='" + contactNumber + '\'' ;
    }
	
}
