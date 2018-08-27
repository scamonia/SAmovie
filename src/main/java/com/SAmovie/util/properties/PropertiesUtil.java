package com.SAmovie.util.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
    public PropertiesUtil() {
    }

    public String readPropertyBykey(String url, String key) {
        InputStream input = this.getClass().getResourceAsStream(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Properties properties = new Properties();

        try {
            properties.load(reader);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return !"null".equals(properties.getProperty(key)) && properties.getProperty(key) != null?properties.getProperty(key):"null";
    }

    public Properties getProperties(String url) {
        InputStream input = this.getClass().getResourceAsStream(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Properties properties = new Properties();

        try {
            properties.load(reader);
            return properties;
        } catch (IOException var6) {
            var6.printStackTrace();
            return null;
        }
    }
}