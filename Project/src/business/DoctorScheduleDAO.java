package business;

import dataaccess.Dao;
import dataaccess.DataAccess;
import dataaccess.DataAccessFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DoctorScheduleDAO implements Dao {

    private DoctorSchedule doctorschedule;
    private List<DoctorSchedule> allDoctorSchedules;
    private String queryString;
    private String insertUpdateQueryString;


    public List<DoctorSchedule> getAllDoctorSchedules() {
        return allDoctorSchedules;
    }

    public DoctorScheduleDAO() {
    }

    public void setDoctorSchedule(DoctorSchedule p) {
        doctorschedule = p;
    }

    @Override
    public String getSql() {
        return queryString;
    }

    public void setSql(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        allDoctorSchedules = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // db 1, 10, 2024-10-22, "8,9,10,12,13"
        while (rs.next()) {
            allDoctorSchedules.add(
                    new DoctorSchedule(
                            rs.getString("id"),
                            LocalDate.parse(rs.getString("availableDay"), format),
                            rs.getInt("slotNumber")
                    )
            );
        }
    }

    @Override
    public List<?> getResults() {
        return allDoctorSchedules;
    }


    @Override
    public String getInsertSql() {
        return insertUpdateQueryString;
    }

    public void setInsertUpdateQueryStringQueryString(String queryString) {
        this.insertUpdateQueryString = queryString;
    }


    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {
        if (pstmt.toString().toUpperCase().startsWith("INSERT")) {
            pstmt.setString(1, doctorschedule.getId() != null ? doctorschedule.getId() : null);
            pstmt.setString(2, doctorschedule.getAvailableDay() != null ? doctorschedule.getAvailableDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
            pstmt.setInt(3, doctorschedule.getSlotNumber());
            pstmt.setInt(4, doctorschedule.getDoctor().getStaffId());
            pstmt.setInt(5, doctorschedule.getAppointment() != null ? doctorschedule.getAppointment().getAppointmentId() : -1);
        }
        if (pstmt.toString().toUpperCase().startsWith("UPDATE")) {
            pstmt.setString(1, doctorschedule.getAvailableDay() != null ? doctorschedule.getAvailableDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
            pstmt.setInt(2, doctorschedule.getSlotNumber());
            pstmt.setInt(3, doctorschedule.getDoctor().getStaffId());
            pstmt.setInt(4, doctorschedule.getAppointment() != null ? doctorschedule.getAppointment().getAppointmentId() : -1);
            pstmt.setString(5, doctorschedule.getId() != null ? doctorschedule.getId() : null);
        }
    }


    public boolean updateDoctorSchedule(DoctorSchedule dc) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();
        try {
            this.doctorschedule = dc;
            this.setInsertUpdateQueryStringQueryString("" +
                    "UPDATE DOCTORSCHEDULE SET availableDay = ?," +
                    " slotNumber = ?," +
                    " doctorId = ?, " +
                    " appointmentId = ? " +
                    " WHERE id = ? ");
            dataAccess.write(this);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
        }
        return flag;
    }

    public boolean createDoctorSchedule(DoctorSchedule dc) {
        boolean flag = false;
        DataAccess dataAccess = DataAccessFactory.getDataAccess();

        try {
            this.doctorschedule = dc;
            this.setInsertUpdateQueryStringQueryString("INSERT INTO DOCTORSCHEDULE" +
                    "(id, availableDay, slotNumber, doctorId, appointmentId)" +
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

    public DoctorSchedule getDoctorScheduleById(String id) {
        try {
            DataAccess dataAccess = DataAccessFactory.getDataAccess();
            this.setSql("SELECT * from DOCTORSCHEDULE");
            dataAccess.read(this);
            doctorschedule = allDoctorSchedules.stream().filter(dc -> dc.getId().equals(id)).findFirst().orElse(null);
            return doctorschedule;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}