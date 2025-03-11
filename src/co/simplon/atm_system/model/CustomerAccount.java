/**
 * Représente le compte bancaire d'un client
 * Responsabilités :
 * - Stocker les informations du compte : numéro de carte associée, solde
 * - Vérifier si le solde est suffisant pour un retrait
 * - Mettre à jour le solde après un retrait
 */

package co.simplon.atm_system.model;

// compte bancaire
public class CustomerAccount {
	private String accountNumber;
	private double balance;

	// Constructor: initialiser compte avec numéro + un solde
	public CustomerAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public void withdrawMoney(double amount) {
		this.balance -= amount;
	}

	// **Getters**
	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	// **Setters**
	// Mettre a jour le solde
	public void setBalance(double balance) {
		this.balance = balance;
	}
}