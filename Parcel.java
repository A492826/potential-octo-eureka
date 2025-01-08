
public class Parcel {
    private String id;
    private int daysInDepot;
    private double weight;
    private int[] dimensions; // {length, width, height}
    private boolean isCollected;

    // Constructor for initializing Parcel object
    public Parcel(String id, int daysInDepot, double weight, int[] dimensions) {
        this.id = id;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.dimensions = dimensions;
        this.isCollected = false;
    }

    // Getters
    public String getId() { 
        return id; 
    }
    
    public int getDaysInDepot() { 
        return daysInDepot; 
    }
    
    public double getWeight() { 
        return weight; 
    }
    
    public int[] getDimensions() { 
        return dimensions; 
    }
    
    public boolean isCollected() { 
        return isCollected; 
    }

    // Setters
    public void setCollected(boolean collected) { 
        isCollected = collected; 
    }

    // Method to calculate the processing fee for the parcel
    public double calculateFee() {
        // Example fee calculation logic based on weight and days in depot
        double fee = 0;

        // Fee calculation based on weight (e.g., $0.50 per kg)
        fee += weight * 0.5;

        // Add fee for the number of days in depot (e.g., $2 per day)
        fee += daysInDepot * 2;

        // Apply discount or additional charges based on parcel dimensions if needed
        // Example: add $5 if the parcel is large (over 50x50x50 cm)
        int volume = dimensions[0] * dimensions[1] * dimensions[2]; // volume = length * width * height
        if (volume > 50 * 50 * 50) {
            fee += 5; // extra charge for large parcels
        }

        return fee;
    }

    // Override toString() method for better readability when displaying a Parcel object
    @Override
    public String toString() {
        return "Parcel ID: " + id + "\n" +
               "Days in Depot: " + daysInDepot + "\n" +
               "Weight: " + weight + " kg\n" +
               "Dimensions (L x W x H): " + dimensions[0] + " x " + dimensions[1] + " x " + dimensions[2] + " cm\n" +
               "Collected: " + (isCollected ? "Yes" : "No") + "\n";
    }

    // Method to display parcel details in a formatted way (can be used in GUI or logs)
    public void displayParcelDetails() {
        System.out.println(toString());
    }
    public double calculateFee() {
        double baseFee = 10.0;  // Base fee
        double weightFee = weight * 0.5;  // Calculate fee based on weight
        double daysFee = daysInDepot * 0.2;  // Calculate fee based on days in depot
        return baseFee + weightFee + daysFee;
    }
    public class Parcel {
        private String id;
        private boolean isProcessed;
    
        public void markAsProcessed() {
            this.isProcessed = true;
        }
    
        public boolean isProcessed() {
            return isProcessed;
        }
    }
    
    
}
