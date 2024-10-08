import prob2B.Factory;
import prob2B.Order;
import prob2B.OrderLine;

import java.time.LocalDate;
import java.time.Month;
import java.util.GregorianCalendar;

public class Main2B {
    public static void main(String[] args) {
        Order order1 = Factory.createOrder("0001", LocalDate.of(2024, Month.OCTOBER, 1), 1, 2, 100.0);
        Factory.addOrderLine(order1, 2, 3, 150.0);
        Factory.addOrderLine(order1, 3, 1, 50.0);
        System.out.println(order1.toString());
    }
}
