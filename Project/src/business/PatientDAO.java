package business;

import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements Dao {
    private List<Patient> patients;
    private Patient currentPatient;
    private String queryString;
    private String insertUpdateQueryString;
    private ResultSet unpackResultSet = null;
    @Override
    public String getSql() {
        return "SELECT * FROM PATIENT";
    }

    public String getQueryString() {
        return queryString;
    }

    public String getInsertUpdateQueryString() {
        return insertUpdateQueryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public void setInsertUpdateQueryString(String insertUpdateQueryString) {
        this.insertUpdateQueryString = insertUpdateQueryString;
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
        return insertUpdateQueryString;
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
        try{
            this.currentPatient = patient;
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            this.setInsertUpdateQueryString("INSERT INTO PATIENT (patientId, firstName, lastName, contactNumber, address, birthDate, gender) VALUES (?, ?, ?, ?, ?, ?, ?)");
            dataAccess.write(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Patient> viewAllPatient() {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Connection con = null;
        List<Patient> results = new ArrayList<>();

        try {
            con = dataAccess.getConnection();
            this.setQueryString("SELECT * from PATIENT");
            dataAccess.read(this);
            results.addAll(patients);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    //do nothing
                }
            }
        }

        return results;
    }
    public Patient getPatientById(int patientId) {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Patient results = null;

        try {
            this.setQueryString("SELECT * from PATIENT WHERE patientId = " + patientId);
            dataAccess.read(this);
            results = patients.getFirst();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return results;
    }

}
