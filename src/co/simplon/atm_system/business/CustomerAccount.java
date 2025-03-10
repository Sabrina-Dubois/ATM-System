/**
 * Représente le compte bancaire d'un client
 * Responsabilités :
 * - Stocker les informations du compte : numéro de carte associée, solde
 * - Vérifier si le solde est suffisant pour un retrait
 * - Mettre à jour le solde après un retrait
 */

package co.simplon.atm_system.business;

// compte bancaire
public class CustomerAccount {
	private String cardNumber;
	private double balance; // solde
	private boolean isBlocked;

	// Constructor initailiser un compte avec numéro et un solde
	public CustomerAccount(String cardNumber, double balance, boolean isBlocked) {
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.isBlocked = isBlocked;
	}

	public void updateBalance(double amount) {
		this.balance += amount;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	// getters
	public String getCardNumber() {
		return cardNumber;
	}

	public double getBalance() {
		return balance;
	}

	// setters
	// Mettre a jour le solde
	public void setBalance(double balance) {
		this.balance = balance;

	}

}