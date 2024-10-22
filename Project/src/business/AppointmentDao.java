package business;

import Enum.AppointmentStatus;

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

public class AppointmentDao implements Dao {
    private String queryString;
    private String insertUpdateQueryString;
    private ResultSet unpackResultSet = null;
    private List<Appointment> appointments = null;
    private Appointment currentAppointment = null;

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setInsertUpdateQueryStringQueryString(String queryString) {
        this.insertUpdateQueryString = queryString;
    }


    public ResultSet getResultSet() {
        return unpackResultSet;
    }

    @Override
    public String getSql() {
        return queryString;
    }


    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        appointments = new ArrayList<>();

        while (rs.next()) {
            Integer appointmentId = rs.getInt("appointmentid");
            LocalDate date = LocalDate.parse(rs.getString("date"));
            Integer slotOfTheDay = rs.getInt("slotOfTheDay");
            String visitReason = rs.getString("visitReason");
            AppointmentStatus status = AppointmentStatus.valueOf(rs.getString("status"));
            //here to get patient
            String patientId = rs.getString("patient");
            Patient patient = new Patient(patientId);

            Appointment appointment = new Appointment(appointmentId, date, slotOfTheDay, visitReason, status,patient);
            appointments.add(appointment);

        }
    }

    @Override
    public List<?> getResults() {
        return List.of();
    }

    @Override
    public String getInsertSql() {
        return insertUpdateQueryString;
    }

    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {
        if (pstmt.toString().toUpperCase().startsWith("INSERT")) {
            pstmt.setObject(1, currentAppointment.getDate() != null ? currentAppointment.getDate() : null);
            pstmt.setInt(2, currentAppointment.getSlotOfTheDay() != null ? currentAppointment.getSlotOfTheDay() : null);
            pstmt.setString(3, currentAppointment.getVisitReason() != null ? currentAppointment.getVisitReason() : null);
            pstmt.setString(4, currentAppointment.getStatus().toString() != null ? currentAppointment.getStatus().toString() : null);
            pstmt.setString(5, currentAppointment.getPatient() != null && currentAppointment.getPatient().getPatientId() != null ? currentAppointment.getPatient().getPatientId() : null);
        }
        if (pstmt.toString().toUpperCase().startsWith("UPDATE")) {
            pstmt.setString(1, currentAppointment.getStatus().toString() != null ? currentAppointment.getStatus().toString() : null);
            pstmt.setInt(2, currentAppointment.getAppointmentId() != null ? currentAppointment.getAppointmentId() : null);
        }

    }

    public List<Appointment> viewAllAppointment() {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Connection con = null;
        List<Appointment> results = new ArrayList<>();


        try {
            con = dataAccess.getConnection();
            this.setQueryString("SELECT * from APPOINTMENT");
            dataAccess.read(this);
            results.addAll(appointments);

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

    public List<Appointment> viewAppointmentByPatient(String patientId) {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        List<Appointment> results = new ArrayList<>();


        try {
            this.setQueryString("SELECT * from APPOINTMENT WHERE patient = '" + patientId + "'");
            dataAccess.read(this);
            results = appointments;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }

        return results;
    }

    public Appointment viewAppointmentByAppointmentId(int appointmentId) {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Appointment results = null;

        try {
            this.setQueryString("SELECT * from APPOINTMENT WHERE appointmentid = '" + appointmentId + "'");
            dataAccess.read(this);
            results = appointments.getFirst();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }

        return results;
    }

    public boolean updateAppointment(Appointment appointment) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
            this.currentAppointment = appointment;
            this.setInsertUpdateQueryStringQueryString("UPDATE APPOINTMENT SET status = ? " +
                    " WHERE appointmentid = ? ");
            dataAccess.write(this);
            flag = true;

            System.out.println("Updated the status of AppointmentId: " + appointment.getAppointmentId()
                    + " to " + appointment.getStatus().toString());

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }
        return flag;
    }

    public boolean bookAppointment(Appointment appointment) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
            this.currentAppointment = appointment;
            this.setInsertUpdateQueryStringQueryString("INSERT INTO APPOINTMENT" +
                    "(date,slotOfTheDay,visitReason,status,patient)" +
                    " VALUES " +
                    "(?,?,?,?,?)");
            dataAccess.write(this);
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }
        return flag;
    }
}

