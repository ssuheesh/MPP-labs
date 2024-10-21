package business;

import java.time.LocalDate;

public class Appointment {
    public static enum AppointmentStatus {PENDING, SUCCESS, FINISHED, CANCEL};
    private LocalDate date;
    private int slotOfTheDay;
    private String visitReason;
    private AppointmentStatus status;
    private Patient patient;
    public Appointment(LocalDate date, int slotOfTheDay, String visitReason, AppointmentStatus status, String patientId, String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, Patient.GenderType gender) {
        this.date = date;
        this.slotOfTheDay = slotOfTheDay;
        this.visitReason = visitReason;
        patient = new Patient(patientId, patientFirstName, patientLastName, contactNumber, birthDate, gender);
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
}

