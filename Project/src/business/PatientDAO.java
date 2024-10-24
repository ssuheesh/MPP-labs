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
import java.util.UUID;

public class PatientDAO implements Dao {
    private List<Patient> patients;
    private Patient currentPatient;
    private String queryString;
    private String insertUpdateQueryString;

    @Override
    public String getSql() {
        return queryString;
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
            String birthDateString = rs.getString("birthDate");
            LocalDate birthDate = (birthDateString != null) ? LocalDate.parse(birthDateString) : null;
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
        if (pstmt.toString().toUpperCase().startsWith("INSERT")) {
            pstmt.setString(1, currentPatient.getPatientId());
            pstmt.setString(2, currentPatient.getPatientFirstName());
            pstmt.setString(3, currentPatient.getPatientLastName());
            pstmt.setString(4, currentPatient.getContactNumber());
            pstmt.setString(5, currentPatient.getAddress());
            String formattedDate = currentPatient.getBirthDate().toString();
            pstmt.setString(6, formattedDate);
            pstmt.setString(7, currentPatient.getGender().name());
        } else if (pstmt.toString().toUpperCase().startsWith("UPDATE")) {
            pstmt.setString(1, currentPatient.getPatientFirstName());
            pstmt.setString(2, currentPatient.getPatientLastName());
            pstmt.setString(3, currentPatient.getContactNumber());
            pstmt.setString(4, currentPatient.getAddress());
            pstmt.setString(5, currentPatient.getBirthDate().toString());
            pstmt.setString(6, currentPatient.getGender().name());
            pstmt.setString(7, currentPatient.getPatientId());
        }

    }

    public boolean addPatient(Patient patient) {
        boolean flag = false;
        try {
            this.currentPatient = patient;
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            String uniquePatientId = UUID.randomUUID().toString();
            currentPatient.setPatientId(uniquePatientId);
            this.setInsertUpdateQueryString("INSERT INTO PATIENT (patientId, firstName, lastName, contactNumber, address, birthDate, gender) VALUES (?, ?, ?, ?, ?, ?, ?)");
            dataAccess.write(this);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public List<Patient> viewAllPatient() {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Connection con = null;
        List<Patient> results = new ArrayList<>();

        try {
            con = dataAccess.getConnection();
            this.setQueryString("SELECT * from PATIENT");
            dataAccess.read(this);
            for (Patient patient : patients) {
                List<PatientHistory> histories = PatientHistory.getPatientHistoryById(patient.getPatientId());
                for(PatientHistory history : histories) {
                    patient.addPatientHistoryList(history);
                }
                List<Appointment> appointments = Appointment.viewAppointmentByPatient(patient.getPatientId());
                for(Appointment appointment : appointments) {
                    patient.addAppointmentList(appointment);
                }
            }
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

    public Patient getPatientById(String patientId) {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Patient results = null;

        try {
            this.setQueryString("SELECT * FROM PATIENT WHERE patientId = '" + patientId + "'");
            dataAccess.read(this);
            if (patients != null && !patients.isEmpty())
                results = patients.getFirst();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return results;
    }

    public boolean updatePatient(Patient patient) {
        boolean flag = false;
        try {
            this.currentPatient = patient;
            DataAccess dataAccess = DataAccessFactory.getDataAccess();

            this.setInsertUpdateQueryString("UPDATE PATIENT SET firstName = ?, lastName = ?, contactNumber = ?, address = ?, birthDate = ?, gender = ? WHERE patientId = ?");

            dataAccess.write(this);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
