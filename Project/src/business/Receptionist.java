package business;

import Enum.AppointmentStatus;
import Enum.Specialist;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Receptionist {

    public static boolean bookAppointment(Appointment appointment) {
        /*Schedule Appointment */
        if (appointment != null) {
            if (Appointment.bookAppointment(appointment)) {
                Optional<Appointment> insertedAppointment = Appointment.viewAllAppointment().stream().sorted(Comparator.comparing(Appointment::getAppointmentId).reversed()).findFirst();
                if (insertedAppointment.isPresent()) {
                    DoctorSchedule doctorSchedule = appointment.getDoctorSchedule();
                    doctorSchedule.setAppointment(insertedAppointment.get());
                    DoctorScheduleDAO doctorScheduleDAO = new DoctorScheduleDAO();
                    doctorScheduleDAO.updateDoctorSchedule(doctorSchedule);
                }
                System.out.println("Appointment booked");
                return true;
            }
        }
        return false;
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
            DoctorScheduleDAO doctorScheduleDAO = new DoctorScheduleDAO();
            Specialist finalSpecialist = specialist;

            System.out.println("Available schedule for " + specialist.toString() + " are here.");
            doctorScheduleDAO.retrieveAllDoctorSchedule().stream()
                     .filter(x -> x.getDoctor().getSpecialist().equals(finalSpecialist))
                    .filter(x -> !x.getAvailableDay().isBefore(LocalDate.now()))
                    //.filter(x -> x.getAppointment() == null)
                    .sorted(Comparator.comparing(DoctorSchedule::getAvailableDay)
                            .thenComparing(DoctorSchedule::getSlotNumber)
                            .thenComparing(DoctorSchedule::getSlotNumber))
                    .forEach(System.out::println);
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
        System.out.println("Enter the patient Id");
        str = scanner.nextLine();


        if (str.isBlank()) {
            System.out.println("Patient Id is blank");
            return null;
        } else {
            // here to get the patient and validate it
            System.out.println("Patient Id : " + str);

            PatientDAO patientDAO = new PatientDAO();

            Patient patient = patientDAO.getPatientById(str);
            if (patient != null) {
                appointment.setPatient(patient);
            } else {
                System.out.println("Patient is not found");
                return null;
            }
        }
        System.out.println("Enter the doctor schedule Id.");
        str = scanner.nextLine();
        if (str.isBlank()) {
            System.out.println("Doctor schedule Id is blank.");
            return null;
        } else {
            // here to get the doctor schedule and validate it
            System.out.println("Doctor schedule Id : " + str);
            DoctorScheduleDAO doctorScheduleDAO = new DoctorScheduleDAO();
            DoctorSchedule doctorSchedule = doctorScheduleDAO.getDoctorScheduleById(str);
            if (doctorSchedule != null) {
                if (doctorSchedule.isAvailable()) {
                    appointment.setDoctorSchedule(doctorSchedule);
                    appointment.setDate(doctorSchedule.getAvailableDay());
                    appointment.setSlotOfTheDay(doctorSchedule.getSlotNumber());
                } else {
                    System.out.println("The slot is not available.");
                    return null;
                }
            } else {
                System.out.println("Doctor schedule is not found.");
                return null;
            }
        }
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
        else {
            System.out.println("No appointments found");
        }
        scanner.close();

    }
}
