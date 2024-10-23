package business;

import java.time.LocalDate;

public class PatientHistory {
    static PatientHistoryDAO dao = new PatientHistoryDAO();
    private String historyID;
    private LocalDate visitDate;
    private String syndrome;
    private String prescription;
    private Patient patient;
    public PatientHistory(LocalDate visitDate, String syndrome, String prescription, String patientId, String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, Patient.GenderType gender) {
        this.visitDate = visitDate;
        this.syndrome = syndrome;
        this.prescription = prescription;
//        patient = new Patient(patientId, patientFirstName, patientLastName, contactNumber, birthDate, gender);
    }
    public PatientHistory(LocalDate visitDate, String syndrome, String prescription) {
        this.visitDate = visitDate;
        this.syndrome = syndrome;
        this.prescription = prescription;
    }
    public LocalDate getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
    public String getSyndrome() {
        return syndrome;
    }
    public void setSyndrome(String syndrome) {
        this.syndrome = syndrome;
    }
    public String getPrescription() {
        return prescription;
    }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
    public static boolean addPatientHistory(PatientHistory history, Patient patient) {
        return dao.addHistory(history, patient);
    }
}

