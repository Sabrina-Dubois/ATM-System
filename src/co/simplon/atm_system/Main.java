/**
 * Point d'entrée de l'application ATM
 * Responsabilités :
 * - Initialiser les services : ATMService, CSVReader, CSVWriter
 * - Démarrer ATMUI
 */

package co.simplon.atm_system;

import java.util.Map;

import co.simplon.atm_system.business.ATMService;
import co.simplon.atm_system.business.AuthentificationService;
import co.simplon.atm_system.business.CSVService;
import co.simplon.atm_system.business.CustomerAccountService;
import co.simplon.atm_system.model.Card;
import co.simplon.atm_system.model.CustomerAccount;
import co.simplon.atm_system.ui.ATMUI;

public class Main {
	public static void main(String[] args) {
		// Crée les services nécessaires
		CSVService csvService = new CSVService();

		// Lire les cartes et les comptes depuis les CSV
		Map<String, Card> cards = csvService.readCards();
		Map<String, CustomerAccount> accounts = csvService.readAccounts();

		// Initialiser les services
		// Créer le CustomerAccountService avec les comptes et les cartes
		CustomerAccountService customerAccountService = new CustomerAccountService(accounts, cards, csvService);
		// Créer l'AuthentificationService avec les cartes
		AuthentificationService authService = new AuthentificationService(cards);

		// Créer l'ATMService avec les services
		ATMService atmService = new ATMService(csvService, authService, customerAccountService);

		// Créer et démarrer l'interface utilisateur
		ATMUI atmUI = new ATMUI(authService, customerAccountService);
		atmUI.start();
	}
}
