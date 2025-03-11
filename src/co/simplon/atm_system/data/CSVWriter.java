/**
 * Ecrit les données dans les fichiers CSV
 * Responsabilités :
 * - Écrire les informations des cartes dans le fichier CSV
 * - Écrire les informations des comptes dans le fichier CSV
 * - Maj les fonds de l'ATM dans le fichier CSV
 */

package co.simplon.atm_system.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import co.simplon.atm_system.model.Card;
import co.simplon.atm_system.model.CustomerAccount;

public class CSVWriter {

	// Écrit les informations des cartes dans le fichier CSV
	public void writeCardsCSV(String filePath, Map<String, Card> newCards) {
		Map<String, Card> oldCards = new CSVReader().readCardsCSV(filePath); // Lire l'ancien fichier
		oldCards.putAll(newCards); // Ajouter les nouvelles cartes aux anciennes

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
			writer.write("CardNumber, PIN, IsBlocked, AccountNumber\n"); // Réécrire l'entête

			// Écrire toutes les cartes (anciennes + nouvelles)
			for (Card card : oldCards.values()) {
				writer.write(card.getCardNumber() + "," + card.getPin() + "," + card.isBlocked() + ","
						+ card.getAccountNumber() + "\n");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	// Écrit les informations des comptes dans le fichier CSV
	public void writeAccountsCSV(String filePath, Map<String, CustomerAccount> accounts) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			File file = new File(filePath);

			if (file.length() == 0) {
				writer.write("AccountNumber, Balance\n");
			}

			// // Écrire chaque compte dans le fichier CSV
			for (CustomerAccount account : accounts.values()) {
				writer.write(account.getAccountNumber() + "," + account.getBalance() + "\n");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	// Maj les fonds de l'ATM dans le fichier CSV
	public void updateAtmFundsCsv(String filePath, double atmBalance) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(String.valueOf(atmBalance));
			System.out.println("Solde de l'ATM mis à jour dans le fichier CSV : " + atmBalance); // Affichage du solde
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void updateAccountCSV(String filePath, Map<String, CustomerAccount> accounts) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write("AccountNumber,Balance\n");
			// Parcourez les comptes pour les réécrire avec les soldes mis à jour
			for (CustomerAccount account : accounts.values()) {
				writer.write(account.getAccountNumber() + "," + account.getBalance() + "\n");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}