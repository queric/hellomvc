package com.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Queric on 2016/12/15.
 */
public class PropertiesFileReader {
    public Properties getProperties() {
        return properties;
    }

    private Properties properties;
    public PropertiesFileReader(String filename) throws IOException{
        properties=new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        properties.load(bf);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
