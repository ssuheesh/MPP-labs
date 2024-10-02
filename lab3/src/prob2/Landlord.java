package prob2;

import java.util.ArrayList;
import java.util.List;

public class Landlord {
    private String name;
    private List<Building> buildingList;
    public Landlord(String name) {
        this.name = name;
        buildingList = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addBuilding(Building building) {
        buildingList.add(building);
    }
    public List<Building> getBuildingList() {
        return buildingList;
    }
    public double calculateProfit() {
        double profit = 0;
        for (Building building : buildingList) {
            profit += building.calculateProfit();
        }
        return profit;
    }
}
