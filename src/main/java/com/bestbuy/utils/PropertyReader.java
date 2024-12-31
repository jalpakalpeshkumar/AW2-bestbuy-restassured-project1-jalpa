package com.bestbuy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader(String filePath) throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
