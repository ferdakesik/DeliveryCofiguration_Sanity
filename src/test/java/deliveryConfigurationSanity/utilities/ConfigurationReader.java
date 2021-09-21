package deliveryConfigurationSanity.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    private static Properties properties;
    static{
        try {
            String pathOfFile = "Configuration.properties";
            FileInputStream file = new FileInputStream(pathOfFile);
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File could NOT read!    " + e.getMessage());
        }
    }
    // It will return the value of the key that is provided in Configuration.properties file.
    public static String getProperty(String key){
        return properties.getProperty(key);
    }





}
