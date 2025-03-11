/**
 * Représente une carte bancaire utilisée dans l'ATM.
 * Responsabilités :
 * - Stocker le numéro de la carte et le code PIN.
 * - Gérer le verrouillage et le déverrouillage de la carte.
 * - Vérifier si la carte est bloquée.
 */

package co.simplon.atm_system.model;

public class Card {
	private String cardNumber;
	private String pin;
	private boolean isBlocked;
	private int failedAttempts;
	private String accountNumber;

	public Card(String cardNumber, String pin, boolean isBlocked, String accountNumber) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.isBlocked = false;
		this.failedAttempts = 0;
		this.accountNumber = accountNumber;
	}

	// **Getters & Setters**
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean blocked) {
		isBlocked = blocked;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
}
