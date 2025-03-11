package co.simplon.atm_system.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static final Properties properties = new Properties();

	static {
		try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.err.println("Erreur : fichier config.properties introuvable !");
			} else {
				properties.load(input);// charger props
			}
		} catch (IOException exception) {
			System.err.println("Erreur lors du chargement de config.properties : " + exception.getMessage());
		}
	}

	public static String get(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			System.err.println("⚠️ Attention : clé '" + key + "' introuvable dans config.properties.");
		}
		return value;
	}
}
