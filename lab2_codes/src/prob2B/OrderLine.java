package prob2B;

public class OrderLine {
    private int orderLineNum;
    private int quantity;
    private double price;
    private Order order;

    OrderLine(int orderLineNum, int quantity, double price) {
        this.orderLineNum = orderLineNum;
        this.quantity = quantity;
        this.price = price;
    }
    public int getOrderLineNum() {
        return orderLineNum;
    }
    public void setOrderLineNum(int orderLineNum) {
        this.orderLineNum = orderLineNum;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    public Order getOrder() {
        return order;
    }

    public String toString() {
        return "OrderLine [orderLineNum=" + orderLineNum + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
