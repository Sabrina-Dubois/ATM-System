/**
 * Lit les données depuis les fichiers CSV
 * Responsabilités :
 * - Charger les cartes et les comptes clients depuis un fichier CSV
 * - Charger le solde de l'ATM depuis un fichier CSV
 */

package co.simplon.atm_system.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.simplon.atm_system.business.Card;
import co.simplon.atm_system.business.CustomerAccount;

public class CSVReader {

	public List<Card> readCardsCSV(String fileName) {
		// stocker les carte lues
		List<Card> cards = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String header = reader.readLine(); // Ignore l'en-tête du CSV

			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(","); // Sépare les données par une virgule

				// verifie qu'il y a 4 colonnes
				if (data.length == 4) {
					String cardNumber = data[0].trim(); // Numéro de la carte
					String pin = data[1].trim(); // PIN de la carte
					double balance = Double.parseDouble(data[2].trim()); // Solde du compte
					boolean isBlocked = Boolean.parseBoolean(data[3].trim()); // Convertir en booléen

					// Crée la carte
					cards.add(new Card(cardNumber, pin, balance, isBlocked));

				} else {
					System.out.println("Ligne invalide dans le fichier CSV : " + line);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return cards;
	}

	// lire les comptes depuis le csv
	public List<CustomerAccount> readAccountsCSV(String fileName) {
		List<CustomerAccount> accounts = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String header = reader.readLine(); // Ignore l'en-tête du CSV

			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 4) {
					String cardNumber = data[0].trim();
					double balance = Double.parseDouble(data[2].trim());
					boolean isBlocked = Boolean.parseBoolean(data[3].trim());
					// creer le compte et l'ajouter
					CustomerAccount account = new CustomerAccount(cardNumber, balance, isBlocked);

					accounts.add(account); // Ajoute le compte à la liste
				} else {
					System.out.println("Ligne invalide dans le fichier CSV : " + line);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return accounts;
	}

	// lire le solde atm deouis csv
	public double readAtmFundsCSV(String fileName) {
		double atmBalance = 0.0; // Valeur par défaut
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = reader.readLine(); // Lire la première ligne

			if (line != null && !line.trim().isEmpty()) {
				// Vérifie que la ligne contient un nombre valide
				atmBalance = Double.parseDouble(line.trim());
				System.out.println("Solde de l'ATM après lecture : " + atmBalance); // Debug
			} else {
				System.out.println("Le fichier est vide ou contient une ligne vide.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Erreur lors de la conversion du solde : " + e.getMessage());
		}
		return atmBalance;
	}

}
