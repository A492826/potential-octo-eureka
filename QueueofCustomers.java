import java.util.LinkedList;

public class QueueofCustomers {
    private LinkedList<Customer> queue;

    // Constructor initializes the queue
    public QueueofCustomers() {
        this.queue = new LinkedList<>();
    }

    // Add a customer to the queue
    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    // Remove a customer from the queue and return it
    public Customer removeCustomer() {
        // Check if the queue is empty before polling
        if (queue.isEmpty()) {
            return null; // Return null if the queue is empty
        }
        return queue.poll(); // Removes and returns the first customer in the queue
    }

    // Get the entire queue
    public LinkedList<Customer> getQueue() {
        return queue;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the size of the queue
    public int getSize() {
        return queue.size();
    }

    // Get all customers in the queue as a string (for easier display)
    public String getAllCustomers() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : queue) {
            sb.append("Customer Name: ").append(customer.getName())
              .append(", Parcel ID: ").append(customer.getParcelId())
              .append(", Sequence Number: ").append(customer.getSequenceNumber()).append("\n");
        }
        return sb.toString();
    }

    // Clear the queue (optional, if needed for future operations)
    public void clearQueue() {
        queue.clear();
    }
}
