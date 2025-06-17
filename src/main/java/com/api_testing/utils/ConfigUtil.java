package com.api_testing.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("CallToPrintStackTrace")
public class ConfigUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load configuration properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

/*public class Enti {
    private static Enti instance;
    
    private Enti() {}
    
    public static synchronized Enti getInstance() {
        if (instance == null) {
            instance = new Enti();
        }
        return instance;
    }
    
}*/
