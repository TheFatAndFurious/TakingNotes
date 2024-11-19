package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "prod");
        String configFile = env.equals("test") ? "app-test.properties" : "app.properties";
        try (FileInputStream input = new FileInputStream(configFile)){
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + configFile, e);
        }
    }

    public static String getProperties(String key){
        return properties.getProperty(key);
    }
}
