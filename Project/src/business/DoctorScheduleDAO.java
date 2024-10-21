package business;

import dataaccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        return "SELECT * from ADMIN";
    }
    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        allDoctorSchedules = new ArrayList<>();
        while(rs.next()) {
//            allDoctorSchedules.add(new Admin(rs.getString("id"), rs.getString("name")));
        }
    }
    @Override
    public List<?> getResults() {
        return allDoctorSchedules;
    }
}