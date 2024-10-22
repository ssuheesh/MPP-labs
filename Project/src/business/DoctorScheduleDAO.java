package business;

import dataaccess.Dao;

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
        while(rs.next()) {
            allDoctorSchedules.add(
                    new DoctorSchedule(
                            LocalDate.parse(rs.getString("availableDay"),format),
                            Arrays.stream(rs.getString("availableHours").split(","))
                                    .map(x->Integer.parseInt(x.trim()))
                                    .collect(Collectors.toList())
                    )
            );
        }
    }
    @Override
    public List<?> getResults() {
        return allDoctorSchedules;
    }
}