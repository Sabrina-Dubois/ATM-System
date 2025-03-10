/**
 * Gère la logique métier de l'ATM.
 * Responsabilités :
 * - Authentifier le client via son code PIN.
 * - Gérer les retraits d'argent : vérifier les fonds, le solde du client.
 * - Mettre à jour le solde du client et de l'ATM après un retrait.
 * - Interagir avec les fichiers CSV pour lire et écrire les données.
 */

package co.simplon.atm_system.business;

import java.util.List;

import co.simplon.atm_system.data.CSVReader;
import co.simplon.atm_system.data.CSVWriter;

public class ATMService {
	private List<Card> cards; // Liste des cartes depuis le CSV

	private List<CustomerAccount> accounts;
	private CSVReader csvReader;
	private CSVWriter csvWriter;
	private Transactions transactions;
	private Card currentCard;
	private CustomerAccount customerAccount;
	private double atmBalance;
	private boolean isJammed;
	private String atmFileName;
	private String accountsFileName;

	public ATMService(String accountsFileName, String atmFileName) {
		this.csvReader = new CSVReader();
		this.csvWriter = new CSVWriter();
		this.transactions = new Transactions();
		this.cards = csvReader.readCardsCSV(accountsFileName);
		this.accounts = csvReader.readAccountsCSV(accountsFileName);
		this.atmBalance = csvReader.readAtmFundsCSV(atmFileName);
		this.atmFileName = atmFileName;
		this.accountsFileName = accountsFileName;
		this.isJammed = false;
	}

	// Authentification
	public String validatePin(String enteredPin) {
		// Vérifier chaque carte pour voir si le PIN correspond
		for (Card card : cards) {
			if (card.validatePin(enteredPin)) {
				this.currentCard = card;

				// Associer la carte avec le compte correspondant
				for (CustomerAccount account : accounts) {
					if (account.getCardNumber().equals(card.getCardNumber())) {
						this.customerAccount = account;
						return "Accès autorisé";
					}
				}
				return "Aucun compte associé à cette carte.";
			} else if (card.isBlocked()) {
				return "\033[31mVotre carte est bloquée. Veuillez contacter le support au 0836656565.\033[0m";
			}
		}
		return "Code PIN incorrect";
	}

	// Effectuer un retrait
	public String withdrawMoney(double amount) {
		if (this.currentCard == null) { // Si la carte n'est pas authentifiée
			return "Aucune carte authentifiée.";
		}
		// Montant de retrait valide ?
		if (amount <= 0) {
			return "\033[31mLe montant de retrait doit être supérieur à zéro.\033[0m";
		}
		// Argent disponible dans l'ATM ?
		if (amount > atmBalance) {
			return "\033[31mFonds insuffisants dans l'ATM, veuillez vous dirigez vers un autre ATM\033[0m";
		}

		// Vérifier que le solde du compte client est suffisant pour le retrait
		if (customerAccount.getBalance() < amount) {
			return "\033[31mSolde insuffisant sur votre compte.\033[0m";
		}

		// Vérifier si l'ATM est bloqué️
		if (isJammed) {
			return "\033[31m Problème mécanique. Veuillez contacter le support.\033[0m";
		}

		customerAccount.setBalance(customerAccount.getBalance() - amount);

		// Mettre à jour le solde de l'ATM
		atmBalance -= amount;

		// Sauvegarder les modifications dans les fichiers CSV
		csvWriter.writeAccountsCSV(accountsFileName, accounts, cards); // Mettre à jour les comptes clients
		csvWriter.updateAtmFundsCsv(atmFileName, atmBalance); // Mettre à jour le solde de l'ATM

		return "Retrait effectué avec succès. Nouveau solde : " + customerAccount.getBalance() + "€";
	}

	// Obtenir le solde du compte client
	public double getBalance() {
		return customerAccount.getBalance();
	}

	// Simuler un problème mécanique
	public void setJammed(boolean isJammed) {
		this.isJammed = isJammed;
	}
}