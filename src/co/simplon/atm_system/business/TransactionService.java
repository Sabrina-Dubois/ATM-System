package co.simplon.atm_system.business;

import co.simplon.atm_system.model.CustomerAccount;
import co.simplon.atm_system.model.Transaction;

public class TransactionService {
	private Transaction transactions;

	public TransactionService() {
		this.transactions = new Transaction();
	}

	public String validateWithdrawal(double amount, CustomerAccount account, double atmBalance) {
		// Montant de retrait valide ?
		if (amount <= 0 || amount % 10 != 0) {
			return "\033[31m⚠️ Montant invalide. Veuillez entrer un multiple de 10.\033[0m";
		}
		if (account.getBalance() < amount) {
			return "\033[31m⚠️ Solde insuffisant sur votre compte.\033[0m";
		}
		if (atmBalance < amount) {
			return "\033[31mFonds insuffisants dans l'ATM, veuillez vous dirigez vers un autre ATM\033[0m";
		}
		return null; // Si tout est valide, retour de null (pas d'erreur)
	}
}