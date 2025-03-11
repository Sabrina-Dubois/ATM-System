/**
 * Représente une banque gérant les comptes et les transactions de l'ATM.
 * Responsabilités :
 * - Stocker les informations des comptes et de l'ATM.
 * - Interagir avec les fichiers CSV via un service dédié.
 * - Fournir les services nécessaires pour la gestion des transactions.
 */

package co.simplon.atm_system.model;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<String, CustomerAccount> accounts;
	// private String name;

	public Bank() {
		accounts = new HashMap<>();
	}

	public void addAccount(CustomerAccount account) {
		accounts.put(account.getAccountNumber(), account);
	}

	// **Getter**
	public CustomerAccount getAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}

}
