package business;

import Enum.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Receptionist {

    public static boolean bookAppointment(Appointment appointment) {
        /*Schedule Appointment */
        return Appointment.bookAppointment(appointment);
    }

    public static List<Appointment> viewAppointment() {
        return Appointment.viewAllAppointment();
    }

    public static List<Appointment> viewAppointmentByPatient(String patientId) {
        return Appointment.viewAppointmentByPatient(patientId);
    }

    public static Appointment viewAppointmentByAppointmentId(int appointmentId) {
        return Appointment.viewAppointmentByAppointmentId(appointmentId);
    }

    public static void viewSchedule() {
        //return List<Schedule>
    }

    public static boolean takeAppointment(Appointment appointment) {
        //to integrate booked Appointment and Schedule
        return false;
    }

    public static boolean updateAppointment(Appointment appointment, AppointmentStatus status) {
        /*Check In/Out appointment*/
        return Appointment.updateAppointment(appointment, status);
    }

    public static Appointment askAppointment() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        Appointment appointment = new Appointment();

        System.out.println("Enter the date YYYY-MM-DD :");
        str = scanner.nextLine();
        appointment.setDate(LocalDate.parse(str));

        System.out.println("Enter the slot of the day:");
        str = scanner.nextLine();
        appointment.setSlotOfTheDay(Integer.parseInt(str));

        System.out.println("Enter the visit reason");
        str = scanner.nextLine();
        appointment.setVisitReason(str);

        appointment.setStatus(AppointmentStatus.BOOKED);

        scanner.close();

        return appointment;

    }

    public static void updateAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the appointment Id :");
        String str = scanner.nextLine();
        Appointment appointment = viewAppointmentByAppointmentId(Integer.parseInt(str));

        if(appointment != null) {
            System.out.println("Enter the appointment status to be updated :"
                    + "\n CHECKIN : 1"
                    + "\n CHECKOUT : 2"
                    + "\n CANCEL : 3");
            str = scanner.nextLine();
            switch (str) {
                case "1":
                    updateAppointment(appointment, AppointmentStatus.CHECKIN);
                    break;
                case "2":
                    updateAppointment(appointment, AppointmentStatus.CHECKOUT);
                    break;
                case "3":
                    updateAppointment(appointment, AppointmentStatus.CANCEL);
                    break;
                default:
                    System.out.println("Invalid appointment status");
                    break;
            }
        }
        scanner.close();

    }
}
