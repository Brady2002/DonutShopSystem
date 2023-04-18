import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.io.BufferedReader;

public class Report {

    // To be called each day (int date)
    static void Report_make (int date, String report_type) {
        // if the report exists, overwrite it
        Path path = Paths.get(date + "_" + report_type + ".csv");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            
        }

        if (report_type.equals("order")) {
            try {
                // Create a new report
                FileWriter report = new FileWriter(date + "_" + report_type + ".csv", true);
                // Create a headline for types of data stored, in csv format
                report.write("Date" + "," + "Type" + "," + "Price" + "," + "Quantity" + "," + "Quantity Total" + "," + "Price Total" + "\n");
                report.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (report_type.equals("stale")) {
            try {
                // Create a new report
                FileWriter report = new FileWriter(date + "_" + report_type + ".csv", true);
                // Create a headline for types of data stored, in csv format
                report.write("Stale donuts today" + "\n");
                report.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    // To be called whenever an order is fulfilled and deleted (int date, String type, float price, int quantity, int quantity_total, float price_total)
    static void updateOrderReport (int date) {
        try {
            // Open file with todays index
            FileWriter report = new FileWriter(date + "_order" + ".csv");
            // If the file exists, append to it
            report.write("date" + "," + "type" + "," + "price" + "," + "quantity" + "," + "quantity_total" + "," + "price_total" + "\n");
            report.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // To be called whenever an order is fulfilled and deleted (int date, String type, float price, int quantity, int quantity_total, float price_total)
    static void updateOrderReportAfter (int date, String type, float price, int quantity, int quantity_total, float price_total) {
        try {
            // Open file with todays index
            FileWriter report = new FileWriter(date + "_order" + ".csv", true);
            // If the file exists, append to it
            report.write(date + "," + type + "," + price + "," + quantity + "," + quantity_total + "," + price_total + "\n");
            report.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    // To be called at the end of the day to store the stale donuts (int date, int stale_donuts)
    static void updateStaleReport(int date) {
        try {
            // Create a new report
            FileWriter report = new FileWriter(date + "_stale" + ".csv");
            // Create a headline for types of data stored, in csv format
            report.write("Stale donuts today" + "\n");
            report.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // To be called at the end of the day to store the stale donuts (int date, int stale_donuts)
    static void updateStaleReportAfter(int date, int stale_donuts_total) {
        try {
            // Create a new report
            FileWriter report = new FileWriter(date + "_stale" + ".csv", true);
            // Create a headline for types of data stored, in csv format
            report.write(stale_donuts_total + "\n");
            report.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Make weekly report (int date)
    static void weeklyReport(int date, String report_type) {
        int index = 0;
        // Change below to account for negative i
        while (index < 7) {
            String fileName = date + "_" + report_type + ".csv";
            File file = new File(fileName);
            int tmp = date;
            
            if (!file.exists() || file.isDirectory()) {
                System.out.println("File " + fileName + " does not exist or is not a regular file");
                if (tmp-- == 0) {
                    date = 365; 
                } else {
                    date--;
                }
                index++;
                continue;
            }

            System.out.println("-------------");
            System.out.println("Day:" + date);
            System.out.println("-------------");

            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] array = line.split(",", -1);
                    for (String column : array) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("Error reading file " + fileName + ": " + e.getMessage());
            }

            index++;
            if (tmp-- == 0) {
                date = 365; 
            } else {
                date--;
            }
        }
    }


    // Make monthly report
    static void monthlyReport(int date, String report_type) {
        int index = 0;
        // Change below to account for negative i
        while (index < 30) {
            String fileName = date + "_" + report_type + ".csv";
            File file = new File(fileName);
            int tmp = date;
            
            if (!file.exists() || file.isDirectory()) {
                System.out.println("File " + fileName + " does not exist or is not a regular file");
                if (tmp-- == 0) {
                    date = 365; 
                } else {
                    date--;
                }
                index++;
                continue;
            }

            System.out.println("-------------");
            System.out.println("Day:" + date);
            System.out.println("-------------");
            
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] array = line.split(",", -1);
                    for (String column : array) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("Error reading file " + fileName + ": " + e.getMessage());
            }

            index++;
            if (tmp-- == 0) {
                date = 365; 
            } else {
                date--;
            }
        }
    }
    
    // Make yearly report
    static void yearlyReport(int date, String report_type) {
    int index = 0;
    // Change below to account for negative i
    while (index < 365) {
        String fileName = date + "_" + report_type + ".csv";
        File file = new File(fileName);
        int tmp = date;
        
        if (!file.exists() || file.isDirectory()) {
            System.out.println("File " + fileName + " does not exist or is not a regular file");
            if (tmp-- == 0) {
                date = 365; 
            } else {
                date--;
            }
            index++;
            continue;
        }

        System.out.println("-------------");
        System.out.println("Day:" + date);
        System.out.println("-------------");
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                for (String column : array) {
                    System.out.print(column + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName + ": " + e.getMessage());
        }

        index++;
        if (tmp-- == 0) {
            date = 365; 
        } else {
            date--;
        }
    }
}

}