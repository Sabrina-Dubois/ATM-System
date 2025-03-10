/**
 * Point d'entrée de l'application ATM
 * Responsabilités :
 * - Initialiser les services : ATMService, CSVReader, CSVWriter
 * - Démarrer ATMUI
 */

package co.simplon.atm_system;

import co.simplon.atm_system.business.ATMService;
import co.simplon.atm_system.ui.ATMUI;

public class Main {
	public static void main(String[] args) {
		String accountsFileName = "/Users/sabichou/Dev/Dev-CDA/eclipse-workspaces/ATM_System/src/co/simplon/atm_system/data/BankSystem.csv";
		String atmFileName = "/Users/sabichou/Dev/Dev-CDA/eclipse-workspaces/ATM_System/src/co/simplon/atm_system/data/ATMFounds.csv";

		// Créer un service ATM
		ATMService atmService = new ATMService(accountsFileName, atmFileName);

		// Créer une interface utilisateur
		ATMUI atmUI = new ATMUI(atmService);

		atmUI.start();
	}
}