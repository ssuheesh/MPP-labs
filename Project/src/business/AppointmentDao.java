package business;

import Enum.AppointmentStatus;

import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao {
    private String queryString;
    private ResultSet unpackResultSet=null;

    public void setQueryString(String queryString) {
        this.queryString = queryString;
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
        this.unpackResultSet=rs;
    }

    @Override
    public List<?> getResults() {
        return List.of();
    }

    public List<Appointment> viewAllAppointment(){
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        Connection con = null;
        List<Appointment> results = new ArrayList<>();


        try {
            con = dataAccess.getConnection();
            this.setQueryString("SELECT * from APPOINTMENT");
            dataAccess.read(this);

            while (this.unpackResultSet.next()) {

                int appointmentid = unpackResultSet.getInt("appointmentid");
                LocalDate date = LocalDate.parse(unpackResultSet.getString("date"));
                int slotOfTheDay = unpackResultSet.getInt("slotOfTheDay");
                String visitReason = unpackResultSet.getString("visitReason");
                AppointmentStatus status = AppointmentStatus.valueOf(unpackResultSet.getString("status"));
                //patient
                Appointment a = new Appointment(appointmentid,date,slotOfTheDay,visitReason,status);

                results.add(a);
            }




        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            if(con != null) {
                try {
                    con.close();
                } catch(Exception e) {
                    //do nothing
                }
            }
        }

        return results;
    }

    public List<Appointment> viewAppointmentByPatient(String patientId){
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        List<Appointment> results = new ArrayList<>();


        try {
            this.setQueryString("SELECT * from APPOINTMENT WHERE patient = " + patientId);
            dataAccess.read(this);

            while (this.unpackResultSet.next()) {

                int appointmentId = unpackResultSet.getInt("appointmentId");
                LocalDate date = LocalDate.parse(unpackResultSet.getString("date"));
                int slotOfTheDay = unpackResultSet.getInt("slotOfTheDay");
                String visitReason = unpackResultSet.getString("visitReason");
                AppointmentStatus status = AppointmentStatus.valueOf(unpackResultSet.getString("status"));
                //patient
                Appointment a = new Appointment(appointmentId,date,slotOfTheDay,visitReason,status);

                results.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
        }

        return results;
    }

    public void updateAppointment(Appointment appointment)
    {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
            this.setQueryString("UPDATE APPOINTMENT SET status = '" + appointment.getStatus() +
                                "' WHERE appointmentid = " + appointment.getAppointmentId());
            dataAccess.write(this);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
        }
    }
    public void bookAppointment(Appointment appointment)
    {
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
              this.setQueryString("INSERT INTO APPOINTMENT" +
                    "(date,slotOfTheDay,visitReason,status,patient)" +
                    " VALUES " +
                    "('"+ appointment.getDate() + "'," +
                    appointment.getSlotOfTheDay() +"," +
                    "'" + appointment.getVisitReason() +"',"+
                    "'" + appointment.getStatus().toString() + "'," +
                    null + ")");
            dataAccess.write(this);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
        }
    }
}

