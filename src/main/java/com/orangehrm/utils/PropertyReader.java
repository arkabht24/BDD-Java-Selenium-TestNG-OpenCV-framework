package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String getProperty(String propertyFileName, String key) {
        String value = null;
        Properties properties = new Properties();
        if(!propertyFileName.contains(".properties")){
            propertyFileName = propertyFileName+".properties";
        }
        try (FileInputStream fis = new FileInputStream("resources/properties/"+propertyFileName)) {
            properties.load(fis);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return value;
    }


}
