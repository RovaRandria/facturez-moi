package ca.ulaval.glo4002.billing.interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BillingProperties {

  private static Properties properties = new java.util.Properties();
  private static final String FILE = "application\\src\\resources\\application.properties";

  public static Properties getInstance() {
    if (properties.isEmpty()) {
      try {
        String path = System.getProperty("user.dir");
        Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
                .forEach(x -> findFile(x));
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
    return properties;
  }

  private static void findFile(Path file) {
    if (file.toString().contains(FILE)) {
      loadFile(file);
    }
  }

  private static void loadFile(Path file) {
    try {
      properties.load(new FileInputStream(file.toString()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
