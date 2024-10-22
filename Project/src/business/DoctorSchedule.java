package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorSchedule {
    private LocalDate availableDay;
    private final List<Integer> availableHours;


    public DoctorSchedule() {
        availableHours = new ArrayList<>();
        for(int i=8; i<18; i++) {
            availableHours.add(i);
        }
    }

    public DoctorSchedule(LocalDate availableDay) {
        this();
        this.availableDay = availableDay;
    }
    public DoctorSchedule(LocalDate availableDay, List<Integer> availableHours) {
        this.availableDay = availableDay;
        this.availableHours = availableHours;
    }
    public LocalDate getAvailableDay() {
        return availableDay;
    }

    public List<Integer> getAvailableHours() {
        return availableHours;
    }

    public void addAvailableHour(int hour) {
        availableHours.add(hour);
    }

    public void removeAvailableHour(int hour) {
        availableHours.remove(hour);
    }

    public boolean isAvailable(int hour) {
        return availableHours.contains(hour);
    }
}
