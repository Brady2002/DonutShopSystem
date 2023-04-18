public class Donut {
    private int donutId;
    private float price;
    private String donutType;
    private String donutFlavor;

    public Donut(int donutId, String donutType, String donutFlavor, float price) {

        this.donutId = donutId;
        this.price = price;
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
    }

    public int getDonutId() {
        return donutId;
    }

    public float getPrice() {
        return price;
    }

    public String getDonutType() {
        return donutType;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }

    public void printInfo() {
        System.out.println("\tID: " + donutId + ".) " + donutType + " " + donutFlavor + " - $" + price);
    }

    public void setDonutId(int donutId) {
        this.donutId = donutId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDonutType(String donutType) {
        this.donutType = donutType;
    }

    public void setDonutFlavor(String donutFlavor) {
        this.donutFlavor = donutFlavor;
    }
}