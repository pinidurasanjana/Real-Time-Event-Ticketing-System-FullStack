import java.math.BigDecimal;

public class Vendor implements Runnable {

    private TicketPool ticketPool;
    private int totalTickets;
    private double ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int totalTickets, double ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i=1;i<=totalTickets;i++){
            Ticket ticket = new Ticket(i,00+i,"Event "+i,new BigDecimal(1000*i));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep((long) (ticketReleaseRate * 1000));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
