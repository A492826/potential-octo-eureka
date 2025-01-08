
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static Log instance;
    private StringBuilder log;

    // Private constructor to prevent instantiation
    private Log() {
        this.log = new StringBuilder();
    }

    // Singleton pattern to get the unique instance of Log
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Adds a new log entry with a timestamp
    public void addLog(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.append("[").append(timestamp).append("] ").append(message).append("\n");
    }

    // Retrieves the entire log as a string
    public String getLog() {
        return log.toString();
    }

    // Clears the current log
    public void clearLog() {
        log.setLength(0);
    }

    // Saves the log to a specified file
    public void saveLogToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.err.println("Error saving log to file: " + e.getMessage());
        }
    }

    // Overriding toString() to get a quick log representation
    @Override
    public String toString() {
        return log.toString();
    }
}
