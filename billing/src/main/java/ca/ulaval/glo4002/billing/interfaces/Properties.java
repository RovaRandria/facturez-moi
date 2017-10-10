package ca.ulaval.glo4002.billing.interfaces;

import java.io.IOException;

public class Properties {
  static private java.util.Properties properties = null;

  public static java.util.Properties getInstance() {
    if (properties == null) {
      properties = new java.util.Properties();
      try {
        properties.load(Properties.class.getClassLoader().getResourceAsStream("application.properties"));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return properties;
  }
}
