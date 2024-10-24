package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {
    public Patient(String patientId) {
        this.patientId=patientId;
    }
    static PatientDAO dao = new PatientDAO();
    public static enum GenderType {FEMALE, MALE};
    private String patientId;
    private String patientFirstName;
    private String patientLastName;
    private String contactNumber;
    private String address;
    private LocalDate birthDate;
    private GenderType gender;
    private List<PatientHistory> patientHistoryList;
    private List<Appointment> appointmentList;
    public Patient(String patientId, String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, GenderType gender, String address) {
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.contactNumber = contactNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.patientHistoryList = new ArrayList<>();
        this.appointmentList = new ArrayList<>();
        this.address = address;
    }
    public Patient(String patientFirstName, String patientLastName, String contactNumber, LocalDate birthDate, GenderType gender, String address) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.contactNumber = contactNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.patientHistoryList = new ArrayList<>();
        this.appointmentList = new ArrayList<>();
        this.address = address;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }
    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public GenderType getGender() {
        return gender;
    }
    public void setGender(GenderType gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<PatientHistory> retrievePatientHistoryList() {
        return patientHistoryList;
    }
    public void addPatientHistoryList(PatientHistory patientHistory) {
        patientHistoryList.add(patientHistory);
    }
    public List<Appointment> retrieveAppointmentList() {
        appointmentList = Appointment.viewAppointmentByPatient(this.getPatientId())
                .stream().filter(x -> x.getDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
        return appointmentList;
    }

    public void addAppointmentList(Appointment appointment) {
        appointmentList.add(appointment);
    }
    public static boolean addPatient(Patient patient) {
        return dao.addPatient(patient);
    }
    public static List<Patient> viewAllPatients() {
        return dao.viewAllPatient();
    }
    public static Patient getPatientById(String patientId) {
        return dao.getPatientById(patientId);
    }
    public static boolean updatePatient(Patient patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public String toString() {
        return
                "patientId='" + patientId + '\'' +
                ", firstName='" + patientFirstName + '\'' +
                ", lastName='" + patientLastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                "\n, patientHistoryList=" + patientHistoryList +
                "\n, appointmentList=" + appointmentList;
    }
}

