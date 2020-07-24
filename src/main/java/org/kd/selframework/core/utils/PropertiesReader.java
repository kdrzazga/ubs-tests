package org.kd.selframework.core.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesReader {

    private static final TestLogger logger = TestLoggerSingleton.getInstance();

    public static <T> T readFromConfig(String keyName) {
        PropertiesConfiguration config = new PropertiesConfiguration();
        String propertiesFilePath = "src/main/resources/application.properties";
        try {
            config.load(propertiesFilePath);
            try {
                Integer value = config.getInt(keyName);
                return (T) value;
            } catch (ConversionException notInteger) {
                try {
                    Double value = config.getDouble(keyName);
                    return (T) value;
                } catch (ConversionException notDouble) {
                    return (T) config.getString(keyName);
                }
            }
        } catch (ConfigurationException e) {
            logger.warn("Could not parse " + propertiesFilePath);
            return (T) "";
        }
    }
}
