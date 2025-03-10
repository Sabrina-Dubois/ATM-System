/**
 * Représente une carte bancaire
 * Responsabilités :
 * - Stocker les informations de la carte : numéro de carte, code PIN, état de blocage
 * - Valider le code PIN saisi par le client
 * - Gérer l'état de la carte 
 */

package co.simplon.atm_system.business;

// Maintenir l'état de la carte : PIN, activation, blocage
public class Card {
	private String cardNumber;
	private String pin;
	private boolean isBlocked;
	private double balance;
	private int failedAttempts;

	public Card(String cardNumber, String pin, double Balance, boolean isBlocked) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.isBlocked = false;
		this.balance = balance;
		this.failedAttempts = 0;
	}

	// Valide le PIN et gère le blocage
	public boolean validatePin(String enteredPin) {
		if (this.isBlocked) {
			return false; // Si la carte est bloquée, le PIN ne peut pas être validé
		}
		if (this.pin.equals(enteredPin)) {
			failedAttempts = 0;
			return true;
		} else {
			failedAttempts++;
			if (failedAttempts >= 3) {
				this.isBlocked = true;
			}
			return false;
		}
	}

	// Vérifie si la carte est bloquée
	public boolean isBlocked() {
		return this.isBlocked;
	}

	// Getters
	public String getCardNumber() {
		return cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	// Setter
	public void setPin(String pin) {
		this.pin = pin;
	}
}
