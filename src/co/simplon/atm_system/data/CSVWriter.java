/**
 * Écrit les données dans les fichiers CSV.
 * Responsabilités :
 * - Mettre à jour les fichiers CSV après un retrait : solde du client et de l'ATM
 */

package co.simplon.atm_system.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import co.simplon.atm_system.business.Card;
import co.simplon.atm_system.business.CustomerAccount;

public class CSVWriter {
	public void writeCardsCSV(String fileName, List<Card> cards) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write("CardNumber,PIN\n");

			for (Card card : cards) {
				writer.write(card.getCardNumber() + "," + card.getPin() + "\n");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeAccountsCSV(String fileName, List<CustomerAccount> accounts, List<Card> cards) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write("CardNumber,PIN,Balance,IsBlocked\n");

			// Réécrire toutes les données dans le fichier CSV
			for (CustomerAccount account : accounts) {
				// Trouver la carte correspondante pour obtenir le PIN
				Card correspondingCard = cards.stream()
						.filter(card -> card.getCardNumber().equals(account.getCardNumber())).findFirst().orElse(null);

				if (correspondingCard != null) {
					writer.write(account.getCardNumber() + "," + correspondingCard.getPin() + "," + account.getBalance()
							+ "," + account.isBlocked() + "\n");
					System.out.println(
							"Écriture dans le CSV : " + account.getCardNumber() + " - Solde : " + account.getBalance());
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void updateAtmFundsCsv(String fileName, double atmBalance) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(String.valueOf(atmBalance));
			System.out.println("Solde de l'ATM mis à jour dans le fichier CSV : " + atmBalance); // Affichage du solde
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
