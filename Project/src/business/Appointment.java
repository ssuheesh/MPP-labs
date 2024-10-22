package business;

import Enum.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;

public class Appointment {
    static AppointmentDao dao = new AppointmentDao();
    //public static enum AppointmentStatus {PENDING, SUCCESS, FINISHED, CANCEL};
    private int appointmentId;
    private LocalDate date;
    private int slotOfTheDay;
    private String visitReason;
    private AppointmentStatus status;
    private Patient patient;

    public Appointment() {

    }

    public Appointment(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointment(LocalDate date, int slotOfTheDay, String visitReason, AppointmentStatus status) {
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        this.status = status;
    }

    public Appointment(int appointmentId, LocalDate date, int slotOfTheDay, String visitReason, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        this.status = status;
    }

    public Appointment(int appointmentId, LocalDate date, int slotOfTheDay, String visitReason, AppointmentStatus status, String patientId, String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, Patient.GenderType gender) {
        this(appointmentId, date, slotOfTheDay, visitReason, status);
        patient = new Patient(patientId, patientFirstName, patientLastName, contactNumber, birthDate, gender);
    }

    public Appointment(Patient patient) {
        this.patient = patient;
    }

    public int getAppointmentId() {
        return appointmentId;
    }


    public LocalDate getDate() {
        return date;
    }

    public int getSlotOfTheDay() {
        return slotOfTheDay;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setSlotOfTheDay(int slotOfTheDay) {
        this.slotOfTheDay = slotOfTheDay;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static List<Appointment> viewAllAppointment() {

        return dao.viewAllAppointment();
    }

    public static List<Appointment> viewAppointmentByPatient(String patientId) {

        return dao.viewAppointmentByPatient(patientId);
    }
    public static Appointment viewAppointmentByAppointmentId(int appointmentId) {

        return dao.viewAppointmentByAppointmentId(appointmentId);
    }

    public static boolean updateAppointment(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
        return dao.updateAppointment(appointment);
    }


    public static boolean bookAppointment(Appointment appointment) {
        return dao.bookAppointment(appointment);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", date=" + date +
                ", slotOfTheDay=" + slotOfTheDay +
                ", visitReason='" + visitReason + '\'' +
                ", status=" + status +
                ", patient=" + patient +
                '}';
    }
}

