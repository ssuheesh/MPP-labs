package business;

import Enum.AppointmentStatus;
import Enum.Specialist;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public static List<Appointment> viewAppointmentByPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patientId : ");
        String patientId = scanner.nextLine();
        if (patientId.isEmpty() || patientId.isBlank()) return null;
        return Appointment.viewAppointmentByPatient(patientId);
    }

    public static Appointment viewAppointmentByAppointmentId(int appointmentId) {
        return Appointment.viewAppointmentByAppointmentId(appointmentId);
    }

    public static void viewSchedule() {

        Specialist specialist = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter specialist: \n" +
                "FAMILY PHYSICIAN : 1\n" +
                "CARDIOLOGIST : 2\n" +
                "GASTROENTEROLOGIST :3");
        String str = scanner.nextLine();
        switch (str) {
            case "1":
                specialist = Specialist.FAMILY_PHYSICIAN;
                break;
            case "2":
                specialist = Specialist.CARDIOLOGIST;
                break;
            case "3":
                specialist = Specialist.GASTROENTEROLOGIST;
                break;
            default:
                System.out.println("Specialist is not available");
                break;

        }

        if (specialist != null) {

            //get all doctors by specialist

            //get all schedules by doctor


        }

        scanner.close();
        //return List<Schedule>
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

        System.out.println("Enter the patient Id");
        str = scanner.nextLine();


        if(str.isBlank()) {
            System.out.println("Patient Id is blank");
            return null;
        }
        else {
            // here to get the patient and validate it
            System.out.println("Patient Id : " + str);
            Patient a = new Patient(str);
            appointment.setPatient(a);
        }
        scanner.close();
        return appointment;

    }

    public static void updateAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the appointment Id :");
        String str = scanner.nextLine();
        Appointment appointment = viewAppointmentByAppointmentId(Integer.parseInt(str));

        if (appointment != null) {
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
