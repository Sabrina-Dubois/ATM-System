/**
 * Service pour gérer les opérations liées aux cartes bancaires.
 * 
 * Responsabilités :
 * - Authentifier un utilisateur en vérifiant son code PIN.
 * - Gérer le verrouillage et le déverrouillage des cartes.
 */

package co.simplon.atm_system.business;

import java.util.Map;

import co.simplon.atm_system.model.Card;

public class AuthentificationService {

	private Map<String, Card> cards;

	public AuthentificationService(Map<String, Card> cards) {
		this.cards = cards;
	}

	public Card authenticate(String pin) {
		for (Card card : cards.values()) {
			if (card.getPin().equals(pin)) { // On trouve une carte avec le bon PIN
				if (card.isBlocked()) {
					// System.out.println("\033[31m⚠️ Votre carte est bloquée. Veuillez contacter le
					// support.\033[0m");
					return null;
				}
				card.setFailedAttempts(0);
				return card;
			}
		}
		// System.out.println("\033[31m⚠️ Code PIN incorrect.\033[0m");
		return null;
	}

	public void handleFailedAttempt(Card card) {
		int failedAttempts = card.getFailedAttempts() + 1;
		card.setFailedAttempts(failedAttempts);
		if (failedAttempts >= 3) {
			card.setBlocked(true);
			// System.out.println("\033[31m⚠️ Votre carte est bloquée après 3 tentatives
			// incorrectes.\033[0m");
			// } else {
			// System.out.println("\033[31m⚠️ Code PIN incorrect. Tentative " +
			// failedAttempts + "/3\033[0m");
		}
	}
}