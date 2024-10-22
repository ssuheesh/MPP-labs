package business;

import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements Dao {
    private List<Patient> patients;
    private Patient currentPatient;
    @Override
    public String getSql() {
        return "SELECT * FROM PATIENT";
    }

    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        patients = new ArrayList<>();

        while (rs.next()) {
            String patientId = rs.getString("patientId");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String contactNumber = rs.getString("contactNumber");
            String address = rs.getString("address");
            LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
            Patient.GenderType gender = Patient.GenderType.valueOf(rs.getString("gender").toUpperCase());

            Patient patient = new Patient(patientId, firstName, lastName, contactNumber, birthDate, gender, address);
            patients.add(patient);
        }
    }

    @Override
    public List<Patient> getResults() {
        return patients;
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO PATIENT (patientId, firstName, lastName, contactNumber, address, birthDate, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, currentPatient.getPatientId());
        pstmt.setString(2, currentPatient.getPatientFirstName());
        pstmt.setString(3, currentPatient.getPatientLastName());
        pstmt.setString(4, currentPatient.getContactNumber());
        pstmt.setString(5, currentPatient.getAddress());
        pstmt.setDate(6, java.sql.Date.valueOf(currentPatient.getBirthDate()));
        pstmt.setString(7, currentPatient.getGender().name());
    }

public void addPatient(Patient patient) throws SQLException {
    this.currentPatient = patient;
    DataAccess dataAccess = DataAccessFactory.getDataAccess();
    dataAccess.write(this);
}
}
