package ca.ulaval.glo4002.billing.interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import ca.ulaval.glo4002.billing.exceptions.FileLookupFailedException;

public class BillingProperties {

  private static Properties properties = new java.util.Properties();
  private static final String FILE = "application\\src\\resources\\application.properties";
  private static final String GETTING_PROPERTIES_FILE_URL_DESCRIPTION_ERROR_DESCRIPTION = "An error occurred while getting an URL from application.properties.";
  private static final String LOADING_PROPERFIES_FILE_DESCRIPTION_ERROR_DESCRIPTION = "The application.properties file could not be loaded.";

  public static Properties getInstance() {
    if (properties.isEmpty()) {
      try {
        String path = System.getProperty("user.dir");
        Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
            .forEach(x -> findFile(x));
      } catch (IOException ex) {
        throw new FileLookupFailedException(BillingProperties.class.getSimpleName(),
            GETTING_PROPERTIES_FILE_URL_DESCRIPTION_ERROR_DESCRIPTION);
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
      throw new FileLookupFailedException(BillingProperties.class.getSimpleName(),
          LOADING_PROPERFIES_FILE_DESCRIPTION_ERROR_DESCRIPTION);
    }
  }

}
