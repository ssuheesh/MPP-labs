package Tasks;

import business.Appointment;
import business.Receptionist;

import java.util.List;
import java.util.Scanner;


public class ReceptionistTask {
    static Scanner scanner = new Scanner(System.in);
    public static void mainTask() {
        String num = "";
        do {
            System.out.println("Enter the option " +
                    "\n View Schedule:1 " +
                    "\n Book Appointment:2 " +
                    "\n View Appointment:3 " +
                    "\n Update Appointment(CHECKIN/CHECKOUT/CANCEL):4" +
                    "\n View Appointment by patientId:5" +
                    "\n Exit:0");
            num = scanner.nextLine();

            switch (num) {
                case "1":
                    Receptionist.viewSchedule();
                    break;
                case "2":
                    Receptionist.bookAppointment(Receptionist.askAppointment());
                    break;
                case "3":
                    List<Appointment> appointmentList = Receptionist.viewAppointment();
                    if (!appointmentList.isEmpty()) {
                        appointmentList.forEach(System.out::println);
                    } else {
                        System.out.println("No appointments found");
                    }
                    break;
                case "4":
                    Receptionist.updateAppointment();
                    break;
                case "5":
                    List<Appointment> appointmentListByPatient = Receptionist.viewAppointmentByPatient();
                    if (!appointmentListByPatient.isEmpty()) {
                        appointmentListByPatient.forEach(System.out::println);
                    } else {
                        System.out.println("No appointments found");
                    }
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(true);
    }
}
