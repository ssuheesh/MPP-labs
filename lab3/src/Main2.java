import prob2.Apartment;
import prob2.Building;
import prob2.Landlord;

public class Main2 {
    public static void main(String[] args) {
        Landlord landlord = new Landlord("Sukhbat");
        Building b1 = new Building(2000.0);
        Building b2 = new Building(3000.0);
        Building b3 = new Building(9000.0);

        Apartment a1 = new Apartment(1200);
        Apartment a2 = new Apartment(2500);
        Apartment a3 = new Apartment(3200);
        Apartment a4 = new Apartment(7800);
        Apartment a5 = new Apartment(2100);
        Apartment a6 = new Apartment(1400);
        Apartment a7 = new Apartment(4500);

        landlord.addBuilding(b1);
        landlord.addBuilding(b2);
        landlord.addBuilding(b3);

        b1.addApartment(a1);
        b1.addApartment(a2);
        b2.addApartment(a3);
        b2.addApartment(a4);
        b3.addApartment(a5);
        b3.addApartment(a6);
        b3.addApartment(a7);

        System.out.println("LandLord Total Profit: " + landlord.calculateProfit());
    }
}