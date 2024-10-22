package Tasks;

import business.Admin;
import business.Receptionist;
import business.Staff;

import java.util.Scanner;
import Enum.*;

public class AdminTask {
    static Scanner scanner = new Scanner(System.in);
    public static void mainTask() {
        System.out.println("Enter the option " +
                "\n Manage Staff Info: 1 " +
                "\n Manage Staff Schedule: 2 " +
                "\n View Appointments: 3 " +
                "\n View Patient Records: 4 "  );
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
            default:
                break;
        }
    }


    public static void adminStaffTask() {
        System.out.println("Enter the option " +
                "\n Manage Doctor Info: 1 " +
                "\n Manage Receptionist Info: 2 " +
                "\n Create Staff: 3 " +
                "\n Go Back: 0 "  );
        int num = scanner.nextInt();
        Role role;
        switch (num) {
            case 1:
                role = Role.DOCTOR;
                Admin.viewStaffByRole(role);
                mainTask();
                break;
            case 2:
                role = Role.RECEPTIONIST;
                Admin.viewStaffByRole(role);
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

    public static void createStaffTask(){
        System.out.println("Enter the option " +
                "\n Create Doctor: 1 " +
                "\n Create Receptionist: 2 " +
                "\n Go Back: 0 "  );
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

    }
}
