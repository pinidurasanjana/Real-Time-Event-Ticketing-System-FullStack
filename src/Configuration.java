import com.google.gson.Gson;
import java.io.*;

public class Configuration {
    private int totalTickets;
    private int maxTicketCapacity;
    private double ticketReleaseRate;
    private double customerRetrievalRate;

    Configuration(){}
    public Configuration(int totalTickets, int maxTicketCapacity, double ticketReleaseRate, double customerRetrievalRate) {
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public double getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(double ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public double getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(double customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void saveToJson(String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
            System.out.println("Configuration saved to JSON.");
        } catch (IOException e) {
            System.out.println("Error saving configuration to JSON: " + e.getMessage());
        }
    }

    public static Configuration loadJson(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading configuration from JSON: " + e.getMessage());
        }
        return null;
    }

    public void saveToTextFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Total Number of Tickets : "+totalTickets+"\n");
            writer.write("Maximum Ticket Capacity : "+maxTicketCapacity+ "\n");
            writer.write("Ticket Release Rate : "+ticketReleaseRate+ "\n");
            writer.write("Customer Retrieval Rate  : "+customerRetrievalRate+ "\n");

            System.out.println("Configuration saved to text file.");
        } catch (IOException e) {
            System.out.println("Error saving configuration to text file: " + e.getMessage());
        }
    }

    public static Configuration loadTextFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int totalTickets = 0;
            int maxTicketCapacity=0;
            int ticketReleaseRate=0;
            int customerRetrievalRate=0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Total Number of Tickets : ")) {
                    totalTickets = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Maximum Ticket Capacity : ")) {
                    maxTicketCapacity = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Ticket Release Rate : ")) {
                    ticketReleaseRate =Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Customer Retrieval Rate : ")) {
                    customerRetrievalRate =Integer.parseInt(line.split(":")[1].trim());
                }
            }
            return new Configuration(totalTickets, maxTicketCapacity, ticketReleaseRate,customerRetrievalRate);

        } catch (IOException e) {
            System.out.println("Error loading configuration from text file: " + e.getMessage());
        }
        return null;
    }

}
