package ca.ulaval.glo4002.billing.interfaces;

import java.io.IOException;
import java.util.Properties;

public class BillingProperties {

	static private Properties properties = new java.util.Properties();
	private final static String FILE = "application.properties";

	public static Properties getInstance() {
		if (properties.isEmpty()) {
			try {
				properties.load(BillingProperties.class.getClassLoader().getResourceAsStream(FILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

}
