package business;

import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class patientHistoryDAO implements Dao {
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

    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public void setInsertUpdateQueryString(String insertUpdateQueryString) {
        this.insertUpdateQueryString = insertUpdateQueryString;
    }

    public void addHistory(PatientHistory history) throws SQLException {
        try{
            this.currentPatientHistory = history;
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            this.setInsertUpdateQueryString("INSERT INTO PATIENT_HISTORY (historyId, patientId, visitDate, syndrome, prescription) VALUES (?, ?, ?, ?, ?)");
            dataAccess.write(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
