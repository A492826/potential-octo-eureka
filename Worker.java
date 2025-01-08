public class Worker {

    // Calculates the fee for processing a parcel
    public double calculateFee(Parcel parcel) {
        double baseFee = 10.0; // Base fee for processing a parcel
        double weightFee = parcel.getWeight() * 0.5; // Fee based on parcel weight
        double sizeFee = (parcel.getDimensions()[0] * parcel.getDimensions()[1] * parcel.getDimensions()[2]) * 0.2; // Fee based on parcel size (volume)
        double storageFee = parcel.getDaysInDepot() * 0.1; // Fee based on the number of days the parcel has been in the depot
        
        // Return the total fee by summing the components
        return baseFee + weightFee + sizeFee + storageFee;
    }

    // Processes a customer by retrieving their parcel and calculating the fee
    public void processCustomer(Customer customer, ParcelMap parcelMap, Log log) {
        // Retrieve the parcel based on the customer's parcel ID
        Parcel parcel = parcelMap.getParcel(customer.getParcelId());

        // Check if the parcel exists and has not been collected
        if (parcel != null && !parcel.isCollected()) {
            // Calculate the fee for the parcel
            double fee = calculateFee(parcel);

            // Mark the parcel as collected
            parcel.setCollected(true);

            // Log the collection and fee information
            log.addLog("Customer " + customer.getName() + " collected parcel " + parcel.getId() + ". Fee: " + fee);
            // Optionally, you can display this information on the GUI (if integrated)
        } else if (parcel == null) {
            // Log if the parcel is not found in the map
            log.addLog("Customer " + customer.getName() + " attempted to collect a non-existing parcel with ID: " + customer.getParcelId());
        } else {
            // Log if the parcel has already been collected
            log.addLog("Customer " + customer.getName() + " attempted to collect parcel " + parcel.getId() + ", but it has already been collected.");
        }
    }
}
