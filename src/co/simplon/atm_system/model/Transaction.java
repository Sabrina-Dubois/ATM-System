/**
 * Gère les transactions bancaires.
 * Responsabilités :
 * - Initier un retrait ou une consultation de solde.
 * - Vérifier les conditions préalables à une transaction : solde suffisant, fonds disponibles.
 */

package co.simplon.atm_system.model;

public class Transaction {

	// Retrait
	public String withdraw(CustomerAccount account, double amount) {
		// Maj solde
		account.setBalance(account.getBalance() - amount);
		return "Retrait réussi. Nouveau solde : " + account.getBalance() + "€";
	}

}
