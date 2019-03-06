package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
  private static Logger logger = LoggerFactory.getLogger(Configuration.class);
  private Properties properties = new Properties();
  private String env;

  public Configuration() {
    this.env = System.getProperty("env", "UAT");
    logger.info("Fetching the environmental details from :"+this.env);
  }

  public Configuration(String env) {
    this.env = env;
  }

  public String getConfig(String property) {
    String config = null;
    try {
      properties.load(new FileInputStream("src/main/java/configuration/properties/env.properties"));
      config = properties.getProperty(env);
      properties.load(new FileInputStream(config));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties.getProperty(property);
  }

  public String getConfig(String property, String envConfigPath) {
    String config = null;
    try {
      properties.load(new FileInputStream(envConfigPath));
      config = properties.getProperty(env);
      properties.load(new FileInputStream(config));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties.getProperty(property);
  }
}
