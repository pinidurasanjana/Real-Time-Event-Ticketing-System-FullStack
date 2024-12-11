import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Ticket Management System");
        System.out.println("Do you want to configure the system? (yes/no)");
        String response = scanner.nextLine();

        int totalTickets = 0;
        int maxTicketCapacity = 0;
        double ticketReleaseRate = 0;
        double customerRetrievalRate = 0;

        if (Objects.equals(response, "yes")) {
            Configuration configuration = Configuration.loadJson("configuration.json");
            if (configuration != null) {
                totalTickets = configuration.getTotalTickets();
                maxTicketCapacity = configuration.getMaxTicketCapacity();
                ticketReleaseRate = configuration.getTicketReleaseRate();
                customerRetrievalRate = configuration.getCustomerRetrievalRate();
                System.out.println(totalTickets);
            } else {
                System.out.println("Error loading configuration from JSON.");
            }
        }
        else {
            totalTickets = validInput(scanner, "Enter total number of tickets: ");
            ticketReleaseRate = validRate(scanner, "Enter tickets release rate: ");
            customerRetrievalRate = validRate(scanner, "Enter customer retrieval rate: ");
            maxTicketCapacity = validInput(scanner, "Enter maximum ticket capacity: ");

            Configuration configuration1 = new Configuration(totalTickets, maxTicketCapacity, ticketReleaseRate, customerRetrievalRate);
            String jsonFilePath = "configuration.json";
            configuration1.saveToJson(jsonFilePath);
            String textFilePath = "configuration.txt";
            configuration1.saveToTextFile(textFilePath);
        }

        TicketPool ticketPool = new TicketPool(maxTicketCapacity);
        Vendor[] vendors = new Vendor[10];
        for (int k = 0; k < vendors.length; k++) {
            vendors[k] = new Vendor(ticketPool, totalTickets, ticketReleaseRate);
            Thread vendorThread = new Thread(vendors[k], "Vendor- " + (k+1));
            vendorThread.start();
        }
        Customer[] customers = new Customer[10];
        for (int k = 0; k < customers.length; k++) {
            customers[k] = new Customer(ticketPool, customerRetrievalRate, 5);
            Thread customerThread = new Thread(customers[k], "Customer- " + (k+1));
            customerThread.start();
        }
    }
    public static int validInput(Scanner scanner, String prompt){
        int value;
        System.out.println(prompt);
        while (true){
            if (scanner.hasNextInt()){
                value = scanner.nextInt();
                if (value> 0){
                    return value;
                }else {
                    System.out.println("Please input positive value!!");
                }
            }else {
                System.out.println("Input is invalid!! Input valid integer.");
                scanner.next();
            }
        }
    }

    public static double validRate(Scanner scanner, String prompt){
        int rate;
        System.out.println(prompt);
        while (true){
            if (scanner.hasNextInt()){
                rate = scanner.nextInt();
                if (rate> 0){
                    return rate;
                }else {
                    System.out.println("Please input positive rate!!");
                }
            }else {
                System.out.println("Input is invalid!! Input valid integer.");
                scanner.next();
            }
        }
    }
}
