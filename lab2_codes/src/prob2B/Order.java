package prob2B;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private String orderNum;
    private LocalDate orderDate;
    List<OrderLine> orderLineList;

    Order(String orderNum, LocalDate orderDate) {
        this.orderNum = orderNum;
        this.orderDate = orderDate;
        orderLineList = new ArrayList<>();
    }

    public String getOrderNum() {
        return orderNum;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }
    public void addOrderLine(OrderLine orderLine) {
        orderLineList.add(orderLine);
    }

    public String toString() {
        String str = "Order [orderNum=" + orderNum + ", orderDate=" + orderDate + "]";
        str += "\n";
        for(OrderLine orderLine : orderLineList) {
            str += orderLine + "\n";
        }
        return str;
    }
}
