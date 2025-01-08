import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {

    private static JTextArea outputArea; // To display logs and messages
    private static QueueofCustomers customerQueue; // Customer queue
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Manager::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Parcel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // GUI components
        JButton loadParcelsButton = new JButton("Load Parcels");
        JButton loadCustomersButton = new JButton("Load Customers");
        JButton processCustomersButton = new JButton("Process Customers");
        JButton saveLogButton = new JButton("Save Log");
        JButton addCustomerButton = new JButton("Add Customer");

        JTextField customerNameField = new JTextField(15); // Input for customer name
        JTextField parcelIdField = new JTextField(15); // Input for parcel ID
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadParcelsButton);
        buttonPanel.add(loadCustomersButton);
        buttonPanel.add(processCustomersButton);
        buttonPanel.add(saveLogButton);

        JPanel addCustomerPanel = new JPanel();
        addCustomerPanel.add(new JLabel("Customer Name:"));
        addCustomerPanel.add(customerNameField);
        addCustomerPanel.add(new JLabel("Parcel ID:"));
        addCustomerPanel.add(parcelIdField);
        addCustomerPanel.add(addCustomerButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(addCustomerPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        

        // Initialize model components
        ParcelMap parcelMap = new ParcelMap();
        customerQueue = new QueueofCustomers();
        Log log = Log.getInstance();
        Worker worker = new Worker();

        // Action for loading parcels
        loadParcelsButton.addActionListener(e -> loadParcels("parcels.txt", parcelMap));

        // Action for loading customers (as before)
        loadCustomersButton.addActionListener(e -> loadCustomers("customers.txt", customerQueue));

        // Action for adding customers manually
        addCustomerButton.addActionListener(e -> {
            String customerName = customerNameField.getText().trim();
            String parcelId = parcelIdField.getText().trim();
            if (!customerName.isEmpty() && !parcelId.isEmpty()) {
                addCustomerToQueue(customerName, parcelId);
                customerNameField.setText(""); // Clear fields
                parcelIdField.setText("");
            } else {
                appendOutput("Please enter both customer name and parcel ID.\n");
            }
        });

        // Action for processing customers
        processCustomersButton.addActionListener(e -> processCustomers(customerQueue, parcelMap, worker, log));

        // Action for saving log to file
        saveLogButton.addActionListener(e -> saveLog(log));

        frame.setVisible(true);
    }

    // Append logs to the outputArea (for display)
    private static void appendOutput(String message) {
        outputArea.append(message);
    }

    // Load parcels from a file
    public static void loadParcels(String fileName, ParcelMap parcelMap) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                String id = data[0].trim();
                int daysInDepot = Integer.parseInt(data[1].trim());
                double weight = Double.parseDouble(data[2].trim());
                int[] dimensions = {
                    Integer.parseInt(data[3].trim()),
                    Integer.parseInt(data[4].trim()),
                    Integer.parseInt(data[5].trim())
                };
                Parcel parcel = new Parcel(id, daysInDepot, weight, dimensions);
                parcelMap.addParcel(parcel);
            }
            appendOutput("Parcels loaded successfully.\n");
        } catch (FileNotFoundException e) {
            appendOutput("Error loading parcels: File not found.\n");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            appendOutput("Error loading parcels: Invalid number format.\n");
            e.printStackTrace();
        } catch (Exception e) {
            appendOutput("Error loading parcels: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    // Load customers from a file (as before)
    public static void loadCustomers(String fileName, QueueofCustomers customerQueue) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int sequenceNumber = 1; // Start sequence number from 1
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 2) { // Ensure we have exactly two elements: name and parcelId
                    String name = data[0].trim();         // Name is the first element
                    String parcelId = data[1].trim();     // Parcel ID is the second element
                    Customer customer = new Customer(sequenceNumber++, name, parcelId); // Increment the sequence number
                    customerQueue.addCustomer(customer);
                } else {
                    appendOutput("Skipping invalid customer data: " + String.join(",", data) + "\n");
                }
            }
            appendOutput("Customers loaded successfully.\n");
        } catch (FileNotFoundException e) {
            appendOutput("Error loading customers: File not found.\n");
            e.printStackTrace();
        } catch (Exception e) {
            appendOutput("Error loading customers: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        
    }

    // Add customer manually to the queue
    public static void addCustomerToQueue(String name, String parcelId) {
        int sequenceNumber = customerQueue.getSize() + 1; // Increment sequence number based on queue size
        Customer customer = new Customer(sequenceNumber, name, parcelId);
        customerQueue.addCustomer(customer);
        appendOutput("Added customer: " + name + " with Parcel ID: " + parcelId + "\n");
    }

    // Process the customer queue, collecting parcels
    public static void processCustomers(QueueofCustomers customerQueue, ParcelMap parcelMap, Worker worker, Log log) {
        if (customerQueue.isEmpty()) {
            appendOutput("No customers in the queue to process.\n");
            return;
        }

        Customer customer = customerQueue.removeCustomer();
        worker.processCustomer(customer, parcelMap, log);
        appendOutput("Processed customer: " + customer.getName() + "\n");
    }

    // Save the log to a text file
    public static void saveLog(Log log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
            writer.write(log.getLog());
            appendOutput("Log saved successfully to 'log.txt'.\n");
        } catch (IOException e) {
            appendOutput("Error saving log: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}
