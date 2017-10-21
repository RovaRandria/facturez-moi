package ca.ulaval.glo4002.billing.interfaces;

import java.io.IOException;
import java.util.Properties;

public class BillingProperties {

	static private Properties properties = null;
	private final static String FILE = "application.properties";

	public static Properties getInstance() {
		if (properties == null) {
			properties = new java.util.Properties();
			try {
				properties.load(BillingProperties.class.getClassLoader().getResourceAsStream(FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

}
