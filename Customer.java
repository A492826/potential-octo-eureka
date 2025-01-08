public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelId;

    // Constructor to initialize a Customer object
    public Customer(int sequenceNumber, String name, String parcelId) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelId = parcelId;
    }

    // Getters
    public int getSequenceNumber() { 
        return sequenceNumber; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getParcelId() { 
        return parcelId; 
    }

    // Setters (if needed for modifying customer data after initialization)
    public void setSequenceNumber(int sequenceNumber) { 
        this.sequenceNumber = sequenceNumber; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setParcelId(String parcelId) { 
        this.parcelId = parcelId; 
    }

    // Method to display customer details in a formatted way
    public void displayCustomerDetails() {
        System.out.println("Customer Sequence: " + sequenceNumber);
        System.out.println("Customer Name: " + name);
        System.out.println("Parcel ID: " + parcelId);
    }

    // Override the toString() method for a clean string representation of the customer
    @Override
    public String toString() {
        return "Customer Sequence: " + sequenceNumber + ", Name: " + name + ", Parcel ID: " + parcelId;
    }

    // Method to check if the customer is eligible for a discount (example logic)
    public boolean isEligibleForDiscount() {
        // Example condition: if the customer has been in the queue for more than 5 customers, they get a discount
        return sequenceNumber > 5;
    }
}
