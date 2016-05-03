package ru.qatools.school;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by merkushevio on 5/3/16.
 */
public class PropertiesDbClient {
    private Properties prop = new Properties();
    private InputStream input = null;

    public Properties getProperties() throws IOException {
        try {
            input = getClass().getClassLoader().getResourceAsStream("connect.properties");
            // load a properties file
            System.out.println(input.available());
            prop.load(input);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return prop;
    }

    public static void main(String[] args) throws IOException{
        System.out.println(new PropertiesDbClient().getProperties().getProperty("db.url"));
    }

}
