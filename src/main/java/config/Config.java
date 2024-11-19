package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "prod");
        String configFile = env.equals("test") ? "app-test.properties" : "app.properties";
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(configFile)){
            if(input == null) {
                throw new RuntimeException("Can not find " + configFile);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + configFile, e);
        }
    }

    public static String getProperties(String key){
        return properties.getProperty(key);
    }
}
