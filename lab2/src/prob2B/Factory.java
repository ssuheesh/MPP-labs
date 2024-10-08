package prob2B;

import java.time.LocalDate;
import java.util.*;

public class Factory {

    public static Order createOrder(String orderNum, LocalDate orderDate, int orderLineNum, int orderLineQty, double orderLinePrice) {
        OrderLine orderLine = new OrderLine(orderLineNum, orderLineQty, orderLinePrice);
        Order order = new Order(orderNum, orderDate);
        order.addOrderLine(orderLine);
        orderLine.setOrder(order);
        return order;
    }

    public static void addOrderLine(Order order, int orderLineNum, int orderLineQty, double orderLinePrice) {
        OrderLine orderLine = new OrderLine(orderLineNum, orderLineQty, orderLinePrice);
        order.addOrderLine(orderLine);
        orderLine.setOrder(order);
    }
}
