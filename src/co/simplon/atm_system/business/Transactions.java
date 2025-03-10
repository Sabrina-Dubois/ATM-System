/**
 * Gère les transactions bancaires
 * Responsabilités :
 * - Initier un retrait ou une consultation de solde
 * - Vérifier les conditions préalables à une transaction : solde suffisant, fonds disponibles
 */

package co.simplon.atm_system.business;

public class Transactions {

	// Retrait
	public String withdraw(CustomerAccount account, double amount) {
		if (amount <= 0 || amount % 10 != 0) {
			return "Montant invalide. Veuillez entrer un multiple de 10.";
		}

		if (account.getBalance() < amount) {
			return "Solde insuffisant sur votre carte.";
		}

		// Effectuer le retrait et met a jour
		account.setBalance(account.getBalance() - amount);
		return "Retrait réussi. Nouveau solde : " + account.getBalance() + "€";
	}

	public String checkBalance(CustomerAccount account) {
		return "Solde actuel : " + account.getBalance();
	}
}
