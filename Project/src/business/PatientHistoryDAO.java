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
import java.util.UUID;

public class PatientHistoryDAO implements Dao {
    private List<PatientHistory> patientHistoryList;
    private PatientHistory currentPatientHistory;
    private String queryString;
    private String insertUpdateQueryString;
    @Override
    public String getSql() {
        return queryString;
    }

    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        patientHistoryList = new ArrayList<>();
        while (rs.next()) {
            String historyId = rs.getString("historyId");
            String patientId = rs.getString("patientId");
            String visitDateString = rs.getString("visitDate");
            String syndrome = rs.getString("syndrome");
            String prescription = rs.getString("prescription");

            LocalDate visitDate = (visitDateString != null) ? LocalDate.parse(visitDateString) : null;

            PatientHistory history = new PatientHistory(visitDate, syndrome, prescription);
            history.setHistoryID(historyId);
            patientHistoryList.add(history);
        }
    }

    @Override
    public List<PatientHistory> getResults() {
        return patientHistoryList;
    }

    @Override
    public String getInsertSql() {
        return insertUpdateQueryString;
    }

    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, currentPatientHistory.getHistoryID());
        pstmt.setString(2, currentPatientHistory.getPatient().getPatientId());
        pstmt.setString(3, currentPatientHistory.getVisitDate().toString()); // Assuming visitDate is in LocalDate format
        pstmt.setString(4, currentPatientHistory.getSyndrome());
        pstmt.setString(5, currentPatientHistory.getPrescription());
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public void setInsertUpdateQueryString(String insertUpdateQueryString) {
        this.insertUpdateQueryString = insertUpdateQueryString;
    }

    public boolean addHistory(PatientHistory history, Patient patient) {
        boolean flag = false;
        try{
            history.setPatient(patient);
            patient.addPatientHistoryList(history);
            this.currentPatientHistory = history;
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            String uniqueHistoryId = UUID.randomUUID().toString();
            currentPatientHistory.setHistoryID(uniqueHistoryId);
            currentPatientHistory.setPatient(patient);
            this.setInsertUpdateQueryString("INSERT INTO PATIENT_HISTORY (historyId, patientId, visitDate, syndrome, prescription) VALUES (?, ?, ?, ?, ?)");
            dataAccess.write(this);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return flag;
    }
    public List<PatientHistory> getHistoryByPatientId(String patientId) {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        List<PatientHistory> results = new ArrayList<>();

        try {
            this.setQueryString("SELECT * FROM PATIENT_HISTORY WHERE patientId = '" + patientId + "'");
            dataAccess.read(this);
            if (patientHistoryList != null && !patientHistoryList.isEmpty())
                results = patientHistoryList;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return results;
    }
}
