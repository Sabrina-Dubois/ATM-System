
package co.simplon.atm_system.business;

import java.util.Map;

import co.simplon.atm_system.model.Card;
import co.simplon.atm_system.model.CustomerAccount;

public class CustomerAccountService {

	private Map<String, CustomerAccount> accounts; // Assurez-vous que vous avez ce champ pour stocker les comptes
	private Map<String, Card> cards;
	private CSVService csvService;

	public CustomerAccountService(Map<String, CustomerAccount> accounts, Map<String, Card> cards,
			CSVService csvService) {
		this.accounts = accounts;
		this.cards = cards;
		this.csvService = csvService;
	}

	// Récupère le solde d'un compte à partir de la carte
	public double getBalance(Card card) {
		CustomerAccount account = accounts.get(card.getAccountNumber());
		if (account != null) {
			return account.getBalance();
		}
		return 0.0; // Si aucun compte n'est trouvé, retourne 0
	}

	// Récupère les cartes
	public Map<String, Card> getCards() {
		return cards;
	}

	// Récupère les comptes clients
	public Map<String, CustomerAccount> getAccounts() {
		return accounts;
	}

	// Effectuer un retrait, ici on assumerait que cette méthode existe aussi
	public String withdrawMoney(Card card, double amount) {
		CustomerAccount account = accounts.get(card.getAccountNumber());
		if (account == null) {
			return "⚠️ Aucun compte trouvé pour cette carte.";
		}
		if (account.getBalance() < amount) {
			return "⚠️ Solde insuffisant.";
		}
		account.withdrawMoney(amount);
		return "✅ Retrait de " + amount + "€ effectué.";
	}

	public void updateBalance(Card card, double newBalance) {
		CustomerAccount account = accounts.get(card.getAccountNumber());
		if (account != null) {
			account.setBalance(newBalance);
			csvService.writeAccounts(accounts);
		}
	}
}
