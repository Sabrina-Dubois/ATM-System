/**
 * Lit les données depuis les fichiers CSV
 * Responsabilités :
 * - Charger les cartes et les comptes clients depuis un fichier CSV
 * - Charger le solde de l'ATM depuis un fichier CSV
 */

package co.simplon.atm_system.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import co.simplon.atm_system.model.Card;
import co.simplon.atm_system.model.CustomerAccount;

public class CSVReader {

	public Map<String, Card> readCardsCSV(String filePath) {
		Map<String, Card> cards = new HashMap<>(); // stock cartes lues

		File file = new File(filePath);
		if (!file.exists()) {
			return cards; // Retourne une map vide si le fichier n'existe pas
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String header = reader.readLine(); // Ignore l'en-tête du CSV

			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 4) {
					String cardNumber = data[0].trim();
					String pin = data[1].trim();
					boolean isBlocked = Boolean.parseBoolean(data[2].trim());
					String accountNumber = data[3].trim();

					Card card = new Card(cardNumber, pin, isBlocked, accountNumber);
					cards.put(cardNumber, card);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return cards;
	}

	// Lire les comptes depuis le csv
	public Map<String, CustomerAccount> readAccountsCSV(String filePath) {
		Map<String, CustomerAccount> accounts = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String header = reader.readLine();

			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 2) {
					String accountNumber = data[0].trim();
					double balance = Double.parseDouble(data[1].trim());

					CustomerAccount account = new CustomerAccount(accountNumber, balance);
					accounts.put(accountNumber, account);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return accounts;
	}

	// Lire le solde atm deouis csv
	public double readAtmFundsCSV(String filePath) {
		double atmBalance = 0.0; // Valeur par défaut
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine(); // Lire la première ligne

			if (line != null && !line.trim().isEmpty()) {
				atmBalance = Double.parseDouble(line.trim());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return atmBalance;
	}
}
