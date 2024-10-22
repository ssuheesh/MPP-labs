package Tasks;

import business.*;

import java.util.List;
import java.util.Scanner;

import Enum.*;

public class AdminTask {
    static Scanner scanner = new Scanner(System.in);

    public static void mainTask() {
        System.out.println("Enter the option " +
                "\n Manage Staff Info: 1 " +
                "\n Manage Staff Schedule: 2 " +
                "\n View Appointments: 3 " +
                "\n View Patient Records: 4 " +
                "\n Exit: 9");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                adminStaffTask();
                break;
            case 2:
                adminScheduleTask();
                break;
            case 3:
                Receptionist.viewAppointment().forEach(System.out::println);
                break;
            case 4:
                Receptionist.viewAppointment().forEach(System.out::println);
                break;
            case 9:
                System.exit(0);
            default:
                break;
        }
        adminStaffTask();
    }


    public static void adminStaffTask() {
        System.out.println("Enter the option " +
                "\n Manage Doctor Info: 1 " +
                "\n Manage Receptionist Info: 2 " +
                "\n Create Staff: 3 " +
                "\n Go Back: 0 ");
        int num = scanner.nextInt();
        Role role;
        switch (num) {
            case 1:
                role = Role.DOCTOR;
                Admin.viewStaffByRole(role).forEach(System.out::println);
                mainTask();
                break;
            case 2:
                role = Role.RECEPTIONIST;
                Admin.viewStaffByRole(role).forEach(System.out::println);
                mainTask();
                break;
            case 3:
                createStaffTask();
                mainTask();
                break;
            default:
                mainTask();
                return;
        }
    }

    public static void createStaffTask() {
        System.out.println("Enter the option " +
                "\n Create Doctor: 1 " +
                "\n Create Receptionist: 2 " +
                "\n Go Back: 0 ");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                Staff s = Admin.createStaffByRole(Role.DOCTOR);
                System.out.println(s);
                return;
            case 2:
                Staff s2 = Admin.createStaffByRole(Role.RECEPTIONIST);
                System.out.println(s2);
                return;
            default:
                mainTask();
                return;
        }
    }

    public static void adminScheduleTask() {
        System.out.println("------------------------");
        System.out.println("Doctors: ");
        List<Staff> doctors = Admin.viewStaffByRole(Role.DOCTOR);
        if(!doctors.isEmpty())
            doctors.forEach(System.out::println);
        else
            System.out.println("No doctors registered.");
        System.out.println("------------------------");
        System.out.println("Insert doctor id to view or edit schedules from above, ");
        System.out.println("or insert 0 to go back: ");
        String str = scanner.nextLine();
        if(str.equals("")) str = scanner.nextLine();
        int id = Integer.parseInt(str);
        if (id == 0) {
            mainTask();
            return;
        }
        Doctor d = (Doctor) doctors.stream().filter(doc -> doc.getStaffId() == id).findFirst().orElse(null);
        if (d == null) {
            System.out.println("Doctor not found. TRY AGAIN.");
            adminScheduleTask();
        }
        else {
            System.out.println("Doctor id: " + d.getStaffId());
            if(d.getDoctorScheduleList().size() > 0)
                d.getDoctorScheduleList().forEach(System.out::println);
            else
                System.out.println("Doctor does not have any schedules.");
            System.out.println("Add doctor schedule to doctor or press 0 to go back: ");
            str = scanner.nextLine();
            if (str.equals("0")) {
                adminScheduleTask();
                return;
            } else {
                DoctorSchedule ds = Admin.addDoctorSchedule(d);
                System.out.println("Added doctorSchedule: " + ds);
            }
        }
    }

}
