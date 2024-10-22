package business;

import dataaccess.Dao;

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
    public List<DoctorSchedule> getAllDoctorSchedules() {
        return allDoctorSchedules;
    }
    public DoctorScheduleDAO(){}
    public void setDoctorSchedule(DoctorSchedule p) {
        doctorschedule = p;
    }
    @Override
    public String getSql() {
        return "SELECT * from DOCTORSCHEDULE";
    }
    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        allDoctorSchedules = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        // db 1, 10, 2024-10-22, "8,9,10,12,13"
        while(rs.next()) {
            allDoctorSchedules.add(
                    new DoctorSchedule(
                            rs.getString("id"),
                            LocalDate.parse(rs.getString("availableDay"),format),
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
        return "";
    }

    @Override
    public void setParameters(PreparedStatement pstmt) throws SQLException {

    }
}