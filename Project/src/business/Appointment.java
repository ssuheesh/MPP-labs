package business;

import Enum.AppointmentStatus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Appointment {
    static AppointmentDao dao = new AppointmentDao();
    //public static enum AppointmentStatus {PENDING, SUCCESS, FINISHED, CANCEL};
    private Integer appointmentId;
    private LocalDate date;
    private Integer slotOfTheDay;
    private String visitReason;
    private AppointmentStatus status;
    private Patient patient;

    public Appointment() {

    }

    public Appointment(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointment(LocalDate date, Integer slotOfTheDay, String visitReason, AppointmentStatus status) {
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        this.status = status;
    }

    public Appointment(Integer appointmentId, LocalDate date, Integer slotOfTheDay, String visitReason, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        this.status = status;

    }
    public Appointment(Integer appointmentId, LocalDate date, Integer slotOfTheDay, String visitReason, AppointmentStatus status,Patient patient) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        this.status = status;
        this.patient = patient;
    }


    public Appointment(Integer appointmentId, LocalDate date, Integer slotOfTheDay, String visitReason, AppointmentStatus status, String patientId, String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, Patient.GenderType gender, String address) {
        this(appointmentId, date, slotOfTheDay, visitReason, status);
        patient = new Patient(patientId, patientFirstName, patientLastName, contactNumber, birthDate, gender, address);
    }

    public Appointment(Patient patient) {
        this.patient = patient;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }


    public LocalDate getDate() {
        return date;
    }

    public Integer getSlotOfTheDay() {
        return slotOfTheDay;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setSlotOfTheDay(Integer slotOfTheDay) {
        this.slotOfTheDay = slotOfTheDay;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public static List<Appointment> viewAllAppointment() {

        return dao.viewAllAppointment().stream()
                .filter(x -> !x.getDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());
    }

    public static List<Appointment> viewAppointmentByPatient(String patientId) {

        return dao.viewAppointmentByPatient(patientId).stream()
                .filter(x -> !x.getDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());
    }

    public static Appointment viewAppointmentByAppointmentId(Integer appointmentId) {

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
                ", patient=" + patient.getPatientId() +
                "}";
    }
}

