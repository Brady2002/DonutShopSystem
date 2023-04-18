import java.util.ArrayList;

public class Inventory {
    ArrayList<Tray> trays = new ArrayList<Tray>();
    int total_stale_donuts = 0;

    public int getStaleDonuts() {
        return this.total_stale_donuts;
    }

    public void addTray(Tray tray){
        for(int i = 0; i < trays.size(); i++) {
            if (trays.get(i) == null) {
                trays.set(i, tray);
                break;
            }
        }
    }

    public void removeTray(Tray tray){
        for(int i = 0; i < trays.size(); i++) {
            if(trays.get(i) == tray) {
                trays.set(i, tray);
                break;
            }
        }
    }

    public void printInfo() {
        for (int i = 0; i < trays.size(); i++) {
                trays.get(i).printInfo();
        }
    }
}
