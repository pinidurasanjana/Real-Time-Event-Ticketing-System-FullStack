import java.math.BigDecimal;

public class Ticket {
    private int ticketId;
    private int eventId;
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket(int ticketId, int eventId, String eventName, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Ticket Id=" + ticketId +
                ", Event Id=" + eventId +
                ", Event Name='" + eventName + '\'' +
                ", Ticket Price=" + ticketPrice +
                '}';
    }
}
