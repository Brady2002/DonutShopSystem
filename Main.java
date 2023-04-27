import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Calendar;

public class Main{
    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        int d = cal.get(Calendar.DAY_OF_YEAR);

        Inventory inventory = new Inventory();
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int loop = 1;

        ArrayList<Donut> menu_donuts = new ArrayList<Donut>();
        ArrayList<Tray> inventory_donuts = new ArrayList<Tray>();
        ArrayList<Order> order_donuts = new ArrayList<Order>();

        int dailyOrderNum = 0;
        int maxDailyOrders = 200;

        try {
            Scanner scanner = new Scanner(new File("menu.csv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Donut donut = new Donut(Integer.parseInt(parts[0]), parts[1], parts[2], Float.parseFloat(parts[3]));
                menu_donuts.add(donut);
            }
            scanner.close();
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            Scanner scanner = new Scanner(new File("inventory.csv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Tray tray = new Tray(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), menu_donuts, Float.parseFloat(parts[6]));
                inventory_donuts.add(tray);
            }
            scanner.close();
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

        while (loop == 1) {
            System.out.println("\nWelcome to the Socorro Doughnut Factory!\nWhat access level are you?\n\t1. Customer\n\t2. Employee\n\t3. Admin");
            int choice = input.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Would you like to:\n\t1. View the menu\n\t2. Back");
                    int choice2 = input.nextInt();
                    switch(choice2) {
                        case 1:
                            System.out.println("\nMenu:");
                            for (int i = 0; i < menu_donuts.size(); i++) {
                                menu_donuts.get(i).printInfo();
                            }
                            System.out.println("Would you like to place an order?\n\t1.) Yes\n\t2.) No");
                            int custchoice = input.nextInt();

                            switch(custchoice) {
                                case 1:
                                    int tmpOrderNum = rand.nextInt(999);
                                    System.out.println("Select your donut type by the donut Id number!");
                                    int tmpDonutId = input.nextInt();
                                    int index = -1;
                                    for (int i = 0; i < inventory_donuts.size(); i++) {
                                        if(inventory_donuts.get(i).getDonutId() == tmpDonutId)
                                        {
                                            index = i;
                                            break;
                                        }
                                    }
                                    if(index == -1)
                                    {
                                        System.out.println("Sorry, we don't have any donuts with that ID available!");
                                        break;
                                    }
                                    System.out.println("How many would you like to buy?\n");
                                    int tmpDonutPurch = input.nextInt();
                                    if (tmpDonutPurch + dailyOrderNum >= maxDailyOrders) {
                                        System.out.println("Sorry, we can't fulfill any more orders today!");
                                        break;
                                    }
                                    else {
                                        for (int i = 0; i < inventory_donuts.size(); i++) {
                                            if(inventory_donuts.get(i).getDonutId() == tmpDonutId)
                                            {
                                                if(inventory_donuts.get(i).getNumDonuts() >= tmpDonutPurch) {
                                                    inventory_donuts.get(i).decrementNumDonuts(tmpDonutPurch);
                                                    break;
                                                }
                                                else {
                                                    int totalDonutsNeeded = 0;
                                                    for (int j = 0; j < inventory_donuts.size(); j++) {
                                                        if(inventory_donuts.get(j).getDonutId() == tmpDonutId) {
                                                            totalDonutsNeeded += inventory_donuts.get(j).getNumDonuts();
                                                        }
                                                    }
                                                    if(totalDonutsNeeded < tmpDonutPurch) {
                                                        System.out.println("Sorry, we don't have enough donuts to fulfill your order!");
                                                        break;
                                                    }
                                                    else {
                                                        int left = inventory_donuts.get(i).getNumDonuts();
                                                        inventory_donuts.get(i).decrementNumDonuts(left);
                                                        tmpDonutPurch = tmpDonutPurch - left;
                                                    }
                                                }
                                            }
                                        }

                                        dailyOrderNum += tmpDonutPurch;
                                    
                                        try {
                                                order_donuts.add(new Order(tmpOrderNum, tmpDonutId, inventory_donuts.get(index).getDonutType(), inventory_donuts.get(index).getDonutFlavor(), tmpDonutPurch, inventory_donuts.get(index).getPrice() * tmpDonutPurch, "Submitted"));
                                                FileWriter order = new FileWriter("order.csv", true);
                                                order.write(tmpOrderNum + "," + tmpDonutId + "," + menu_donuts.get(index).getDonutType() + "," + menu_donuts.get(index).getDonutFlavor() +  "," + tmpDonutPurch + "," + menu_donuts.get(index).getPrice() * tmpDonutPurch + "," + "Submitted" + "\n");
                                                order.close();
                                                System.out.println("\n\tOrder Placed!\n");
                                            } 
                                            catch (IOException e) {
                                                System.out.println("An error occurred.");
                                                e.printStackTrace();
                                            }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the employee password to continue:");
                    String password = input.next();
                    if(password.equals("1")) {
                        System.out.println("\n--------Welcome, employee!--------");
                        System.out.println("\n\t1.) View the menu\n\t2.) View the inventory\n\t3.) View the orders\n\t4.) Back");
                        int choice3 = input.nextInt();
                        switch(choice3) {
                            case 1:
                                System.out.println("\nMenu:");
                                for (int i = 0; i < menu_donuts.size(); i++) {
                                    menu_donuts.get(i).printInfo();
                                }
                                break;
                            case 2:
                                System.out.println("\nCurrent Inventory:");
                                if (inventory_donuts.size() == 0)
                                {
                                    System.out.println("\n\tNo Current Invetory");
                                }
                                else
                                {
                                    for (int i = 0; i < inventory_donuts.size(); i++) {
                                        System.out.println("------------------------");
                                        inventory_donuts.get(i).printInfo();
                                    }
                                }
                                System.out.println("\nWhat operation would you like to perform?");
                                System.out.println("\t1.) Add Tray\n\t2.) Back");
                                int choice4 = input.nextInt();
                                switch(choice4) {
                                    case 1:
                                        System.out.println("Enter the donut ID: ");
                                        int dId = input.nextInt();
                                        int index = 0;
                                        for (int i = 0; i < menu_donuts.size(); i++) {
                                            if(menu_donuts.get(i).getDonutId() == dId)
                                            {
                                                index = i;
                                            }
                                        }
                                        Random idNum = new Random();
                                        int trayNum = idNum.nextInt(999);
                                        Tray tray = new Tray(trayNum, dId, menu_donuts.get(index).getDonutType(), menu_donuts.get(index).getDonutFlavor(), 20, 0, menu_donuts, menu_donuts.get(index).getPrice());
                                        inventory.addTray(tray);
                                        inventory_donuts.add(tray);
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("Invalid input. Please try again.");
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Orders: ");
                                System.out.println(order_donuts.size());
                                for(int i = 0; i < order_donuts.size(); i++) {
                                    order_donuts.get(i).printInfo();
                                }
                                System.out.println("\nWould you like to update the status of the order?\n\t1.) Yes\n\t2.) No");
                                int tmpch = input.nextInt();
                                switch(tmpch) {
                                    case 1:
                                        System.out.println("Enter the order ID of the order you would like to update:");
                                        int tmpc = input.nextInt();
                                        for(int i = 0; i < order_donuts.size(); i++) {
                                            if(order_donuts.get(i).getOrderNum() == tmpc){
                                                System.out.println("Enter the new status of the order (Submitted, Processed, Completed): ");
                                                String tmps = input.next();
                                                order_donuts.get(i).setStatus(tmps);
                                            }
                                        }
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("Invalid input. Please try again.");
                                        break;
                                }
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Invalid input. Please try again.");
                                break;
                        }
                    }
                    else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the admin password to continue:");
                    String password2 = input.next();
                    if(password2.equals("2")) {
                        System.out.println("\n--------Welcome, Admin!--------\n");
                        System.out.println("\t1.) Update the menu \n\t2.) View reports \n\t3.) Change Max Daily Orders \n\t4.) Back \n\t5.) Exit Program and End Day");
                        int choice5 = input.nextInt();
                        switch(choice5) {
                            case 1:
                                System.out.println("Select:\n\t1.) Add to Menu\n\t2.) Edit Menu\n\t3.) Remove Menu\n\t4.) Back");
                                int choice6 = input.nextInt();
                                switch(choice6) {
                                    case 1:
                                        System.out.println("\nEnter the donut ID: ");
                                        int donutId = input.nextInt();
                                        System.out.println("\nEnter the donut Type: ");
                                        String donutType = input.next();
                                        System.out.println("\nEnter the donut Flavor: ");
                                        String donutFlavor = input.next();
                                        System.out.println("\nEnter the donut price: ");
                                        float donutPrice = input.nextFloat();
                                        Donut donut = new Donut(donutId, donutType, donutFlavor, donutPrice);
                                        menu_donuts.add(donut);
                                        break;
                                    case 2:
                                        for (int j = 0; j < menu_donuts.size(); j++) {
                                            menu_donuts.get(j).printInfo();
                                        }
                                        System.out.println("Which Donut (ID) would you like to edit?");
                                        int tmp = input.nextInt();
                                        boolean exists = false;
                                        for (int j = 0; j < menu_donuts.size(); j++) {
                                            if (menu_donuts.get(j).getDonutId() == tmp) {
                                                exists = true;
                                            } else {
                                                exists = false;
                                            }
                                        }
                                        if (!(exists)) {
                                            System.out.println("Error: Donut ID not found");
                                            break;
                                        }
                                        System.out.println("\nEnter the donut Type: ");
                                        String donutType2 = input.next();
                                        System.out.println("\nEnter the donut Flavor: ");
                                        String donutFlavor2 = input.next();
                                        System.out.println("\nEnter the donut price: ");
                                        float donutPrice2 = input.nextFloat();
                                        for (int j = 0; j < menu_donuts.size(); j++) {
                                            if (menu_donuts.get(j).getDonutId() == tmp) {
                                                menu_donuts.get(j).setPrice(donutPrice2);
                                                menu_donuts.get(j).setDonutType(donutType2);
                                                menu_donuts.get(j).setDonutFlavor(donutFlavor2);
                                            }
                                        }
                                        System.out.println("Donut edited");
                                        break;
                                    case 3:
                                        for (int j = 0; j < menu_donuts.size(); j++) {
                                            menu_donuts.get(j).printInfo();
                                        }
                                        System.out.println("\nEnter the donut ID to be removed from the menu: ");
                                        int dId3 = input.nextInt();
                                        for (int j = 0; j < menu_donuts.size(); j++) {
                                            if (menu_donuts.get(j).getDonutId() == dId3) {
                                                menu_donuts.remove(j);
                                                System.out.println("Donut removed");
                                            } else {
                                                System.out.println("Error: Donut ID not found");
                                            }
                                        }
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        System.out.println("\nInvalid input. Please try again.");
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("\nWould you like to generate a sales summary report or a stale donut report?\n\t1.) Sales Summary\n\t2.) Stale Donut");
                                int choice7 = input.nextInt();
                                System.out.println("Would you like to have a weekly, montly, or yearly report?\n\t 1) Weekly\n\t 2) Monthly\n\t 3) Yearly");
                                int choice8 = input.nextInt();
                                switch (choice7) {
                                    case 1:
                                        switch (choice8) {
                                            case 1:
                                                Report.weeklyReport(d, "order");
                                                break;
                                            case 2:
                                                Report.monthlyReport(d, "order");
                                                break;
                                            case 3:
                                                Report.yearlyReport(d, "order");
                                                break;
                                        }
                                        break;
                                    case 2:
                                        switch (choice8) {
                                            case 1:
                                                Report.weeklyReport(d, "stale");
                                                break;
                                            case 2:
                                                Report.monthlyReport(d, "stale");
                                                break;
                                            case 3:
                                                Report.yearlyReport(d, "stale");
                                                break;
                                        }
                                        break;
                                    default:
                                        System.out.println("\nInvalid input. Please try again.");
                                        break; 
                                }
                                break;
                            case 3:
                                System.out.println("Set new value for max daily orders: ");
                                int max = input.nextInt();
                                maxDailyOrders = max;
                                break;
                            case 4:
                                break;
                            case 5:
                                System.out.println("\nSaving info and ending day...");
                                loop = 0;
                                break;
                            default:
                                System.out.println("\nInvalid input. Please try again.");
                                break;
                        }
                    }
                    else
                    {
                        System.out.println("\nIncorrect password. Please try again.");
                        break;
                    }
                    break;
                default:
                    System.out.println("\nInvalid input. Please try again.");
                    break;
            }
        }
        input.close();
        try {
            FileWriter save = new FileWriter("menu.csv");
            for (int i = 0; i < menu_donuts.size(); i++) {
                save.write(menu_donuts.get(i).getDonutId() + "," + menu_donuts.get(i).getDonutType() + "," + menu_donuts.get(i).getDonutFlavor() + "," + menu_donuts.get(i).getPrice() + "\n");
            }
            save.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try {
            FileWriter save = new FileWriter("inventory.csv");
            for (int i = 0; i < inventory_donuts.size(); i++) {
                inventory_donuts.get(i).incrementAge();
                if (inventory_donuts.get(i).getAge() >= 2) {
                    inventory.total_stale_donuts += inventory_donuts.get(i).getNumDonuts();
                    inventory_donuts.remove(i);
                }
                if (inventory_donuts.size() != 0)
                {
                    save.write(inventory_donuts.get(i).getTrayId() + "," + inventory_donuts.get(i).getDonutId() + "," + inventory_donuts.get(i).getDonutType() + "," + inventory_donuts.get(i).getDonutFlavor() + "," + inventory_donuts.get(i).getNumDonuts() + "," + (inventory_donuts.get(i).getAge()) + "," + (inventory_donuts.get(i).getPrice()) + "\n");
                }
            }
            save.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Stale donut report
        Report.Report_make(d, "stale");
        Report.updateStaleReport(d);
        Report.updateStaleReportAfter(d, inventory.getStaleDonuts());

        // Daily sales report
        Report.Report_make(d, "order");
        Report.updateOrderReport(d);
        int quantity_total = 0;
        float price_total = 0;
        for (int y = 0; y < order_donuts.size(); y++) {
            if (order_donuts.get(y).getStatus().equals("Completed")) {
                quantity_total += order_donuts.get(y).getQuantity();
                price_total += order_donuts.get(y).getPrice();
                Report.updateOrderReportAfter(d, order_donuts.get(y).getDonutType(), order_donuts.get(y).getPrice(), order_donuts.get(y).getQuantity(), quantity_total, price_total);
            }
        }

        try {
            new FileWriter("order.csv", false).close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}