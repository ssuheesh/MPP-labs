package prob2;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private double maintananceCost;
    private List<Apartment> apartmentList;

    public Building(double maintananceCost) {
        this.maintananceCost = maintananceCost;
        this.apartmentList = new ArrayList<>();
    }

    public void addApartment(Apartment apartment) {
        this.apartmentList.add(apartment);
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public double getMaintananceCost() {
        return maintananceCost;
    }

    public void setMaintananceCost(double maintananceCost) {
        this.maintananceCost = maintananceCost;
    }

    public double calculateProfit() {
        double totalRent = 0;
        for (Apartment apartment : apartmentList) {
            totalRent += apartment.getRent();
        }
        return totalRent - maintananceCost;
    }

}
