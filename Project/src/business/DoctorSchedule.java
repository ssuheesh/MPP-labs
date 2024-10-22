package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorSchedule {
    private String id;
    private Doctor doctor;
    private LocalDate availableDay;
    private Integer slotNumber;
    private Appointment appointment;
    // this doctor on 20thOctober has free time 8,9,10,13,16
    // when appointment is set 20thOctober has free time 9,10,13,16
    public DoctorSchedule(String id) {
        this.id = id;
    }

    public DoctorSchedule(String id, LocalDate availableDay) {
        this(id);
        this.availableDay = availableDay;
    }
    public DoctorSchedule(String id, LocalDate availableDay, Integer slotNumber) {
        this.id = id;
        this.availableDay = availableDay;
        this.slotNumber = slotNumber;
    }
    public LocalDate getAvailableDay() {
        return availableDay;
    }

    public boolean isAvailable() {
        return this.appointment == null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public void setDoctor(Integer staffId){

    }
}
