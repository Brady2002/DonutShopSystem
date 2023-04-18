public class Order {
    private int orderNum;
    private int itemNum;
    private String donutType;
    private String donutFlavor;
    private int quantity;
    private float price;
    private String status;

    public Order(int orderNum, int itemNum, String donutType, String donutFlavor, int quantity, float price, String status) {
        this.orderNum = orderNum;
        this.itemNum = itemNum;
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public int getOrderNum() {
        return orderNum;
    }
    
    public int getItemNum() {
        return itemNum;
    }

    public String getDonutType() {
        return donutType;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
    public void setPrice(float price){
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void printInfo() {
        System.out.println("\tOrderNum: " + orderNum);
        System.out.println("\titemNum: " + itemNum);
        System.out.println("\tdonutType: " + donutType);
        System.out.println("\tdonutFlavor: " + donutFlavor);
        System.out.println("\tquantity: " + quantity);
        System.out.println("\tprice: " + price);
        System.out.println("\tstatus: " + status);
        System.out.println("");
    }
}