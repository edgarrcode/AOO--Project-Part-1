/**
* This class configures the logger.
* Find the output of the logger in a "log.txt" file
*/
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class LoggingConfiguration {
    public static void setupLogging() {
        try {
            // Specify the file that will store the log messages
            FileHandler fileHandler = new FileHandler("log.txt", true);
            
            // Optionally, use a formatter to define the format of log messages
            fileHandler.setFormatter(new SimpleFormatter());
            
            // Get the global logger to configure it
            Logger logger = Logger.getLogger("");
            
            // Add the FileHandler to the logger
            logger.addHandler(fileHandler);
            
            // Set the default logging level (e.g., INFO, DEBUG, ERROR)
            logger.setLevel(java.util.logging.Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
