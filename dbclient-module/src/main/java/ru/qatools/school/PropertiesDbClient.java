package ru.qatools.school;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by merkushevio on 5/3/16.
 */
public class PropertiesDbClient {
    private Properties prop = new Properties();
    private InputStream input = null;

    public Properties getProperties() {
        try {
            input = getClass().getClassLoader().getResourceAsStream("connect.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
