package business;
import Enum.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Admin {
    static AdminDAO dao = new AdminDAO();
    private String id;
    private String name;
    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean createAdmin() {
        return dao.createAdmin(this);
    }

    public boolean updateAdmin() {
        return dao.updateAdmin(this);
    }

    public static List<Staff> viewStaffByRole(Role role){
        return Staff.viewAllStaff().stream()
                .filter(staff -> staff.getRole() == role)
                .collect(Collectors.toList());
    }

    public static Staff createStaffByRole(Role role) {
        Scanner sc = new Scanner(System.in);

        StaffDAO staffDAO = new StaffDAO();

        LocalDate date = LocalDate.now();
        System.out.println("Enter staff name: ");
        String name = sc.nextLine();
        System.out.println("Enter contact number: ");
        String contactNumber = sc.nextLine();
        if (role == Role.DOCTOR) {
            System.out.println("Enter specialty: 1-FAMILY_PHYSICIAN 2-CARDIOLOGIST 3-GASTROENTEROLOGIST ");
            Integer specialty = sc.nextInt();
            Specialist s = Specialist.FAMILY_PHYSICIAN;
            if (specialty == 1) {
                s = Specialist.FAMILY_PHYSICIAN;
            } else if (specialty == 2) {
                s = Specialist.CARDIOLOGIST;
            } else if (specialty == 3) {
                s = Specialist.GASTROENTEROLOGIST;
            }
            Doctor d = new Doctor(name, role, date, contactNumber, s);
            staffDAO.createStaff(d);
            return d;
        } else {
            Staff c = new Staff(name, role, date, contactNumber);
            staffDAO.createStaff(c);
            return c;
        }
    }

    public static DoctorSchedule addDoctorSchedule(Doctor doctor) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DoctorScheduleDAO dsDao = new DoctorScheduleDAO();
        Scanner sc = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        System.out.println("Enter availableDay: ");
        LocalDate availableDay = LocalDate.parse(sc.nextLine(), format);
        System.out.println("Enter slotNumber: ");
        Integer slotNumber = Integer.parseInt(sc.nextLine());
        DoctorSchedule ds = new DoctorSchedule(availableDay, slotNumber);
        ds.setDoctor(doctor);
        dsDao.createDoctorSchedule(ds);
        doctor.addDoctorSchedule(ds);
        return ds;
    }

}
