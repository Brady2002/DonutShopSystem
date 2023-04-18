import java.util.ArrayList;

public class Tray {
    private int trayId;
    private int numDonuts;
    private int age;
    private int donutId;
    private String donutType;
    private String donutFlavor;
    private float price;

    public Tray(int trayId, int donutId, String donutType, String donutFlavor, int quantity, int age, ArrayList<Donut> donuts, float price) {
        this.trayId = trayId;
        this.numDonuts = quantity;
        this.age = age;
        this.donutId = donutId;
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.price = price;
    }

    public int getTrayId() {
        return trayId;
    }

    public int getDonutId(){
        return donutId;
    }

    public int getNumDonuts() {
        return numDonuts;
    }

    public String getDonutType() {
        return donutType;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }

    public int getAge() {
        return age;
    }

    public float getPrice() {
        return price;
    }

    public void decrementNumDonuts(int amount) {
        this.numDonuts = this.numDonuts - amount;
    }

    public void incrementAge() {
        this.age = this.age + 1;
    }

    public void printInfo() {
        System.out.println("Tray ID: " + trayId);
        System.out.println("Donut ID: " + donutId);
        System.out.println("Donut Type: " + donutType);
        System.out.println("Donut Flavor: " + donutFlavor);
        System.out.println("Number of Donuts: " + numDonuts);
        System.out.println("Age: " + age);
    }
}