public class Customer implements Runnable{

    private TicketPool ticketPool;
    private double customerRetrievalRate;
    private int quantity;

    public Customer(TicketPool ticketPool, double customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int j=0;j<quantity;j++){
            Ticket ticket = ticketPool.removeTicket();
            try {
                Thread.sleep((long) (customerRetrievalRate * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }



        }
    }
}
