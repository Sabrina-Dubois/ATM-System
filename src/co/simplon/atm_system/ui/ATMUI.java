/**
 * Gère l'interface utilisateur de l'ATM
 * Responsabilités :
 * - Afficher les menus : retrait, consultation du solde
 * - Interagir avec l'utilisateur pour saisir les montants ou sélectionner des options
 * - Afficher des messages d'erreur ou de confirmation en fonction des actions de l'utilisateur
 * - Empêcher les interactions si le client n'est pas authentifié
 */

package co.simplon.atm_system.ui;

import java.util.Scanner;

import co.simplon.atm_system.business.AuthentificationService;
import co.simplon.atm_system.business.CustomerAccountService;
import co.simplon.atm_system.model.Card;

public class ATMUI {
	private AuthentificationService authentificationService;
	private Scanner scanner;
	private Card currentCard; // Carte actuellement authentifiée
	private CustomerAccountService customerAccountService;

	public ATMUI(AuthentificationService authService, CustomerAccountService customerAccountService) {
		this.authentificationService = authService;
		this.customerAccountService = customerAccountService;
		this.scanner = new Scanner(System.in);
	}

	public void start() {
		System.out.println("\033[35m=================================\033[0m");
		System.out.println("        \n" + "\033[35m\n" + " (`-')  _ (`-')     <-. (`-')      \n"
				+ " (OO ).-/ ( OO).->     \\(OO )_     \n" + " / ,---.  /    '._  ,--./  ,-.)    \n"
				+ " | \\ /`.\\ |'--...__)|   `.'   |    \n" + " '-'|_.' |`--.  .--'|  |'.'|  |    \n"
				+ "(|  .-.  |   |  |   |  |   |  |    \n" + " |  | |  |   |  |   |  |   |  |    \n"
				+ " `--' `--'   `--'   `--'   `--'    \n"
				+ "                                                                                                                                            \n"
				+ "\033[0m");
		System.out.println("\033[35m=================================\033[0m");

		showPinMenu();

		int choice = getUserChoice();
		scanner.nextLine();

		switch (choice) {
		case 1:
			handlePinValidation();
			break;
		case 2:
			System.out.println("\033[32mMerci d'avoir utilisé notre service. À bientôt !\033[0m");
			break;
		default:
			System.out.println("\033[31mChoix invalide. Veuillez réessayer.\033[0m");
			start();
		}
	}

	private void showPinMenu() {
		System.out.println("\n\033[36m Bonjour, que souhaitez-vous faire aujourd'hui ?\033[0m");
		System.out.println("\033[90m1. Entrer le code PIN\033[0m");
		System.out.println("\033[90m2. Quitter\033[0m");
		System.out.println("\033[90m===================================\033[0m");
	}

	private int getUserChoice() {
		System.out.print("\033[36mChoisissez une option : \033[0m");
		return scanner.nextInt();
	}

	private void handlePinValidation() {
		System.out.println("\n\033[36mVeuillez entrer votre code PIN.\033[0m");
		String enteredPin = scanner.nextLine();

		int attempts = 0;
		while (attempts < 3) {
			currentCard = authentificationService.authenticate(enteredPin);

			if (currentCard != null) {
				System.out.println("\033[1;32mAccès autorisé.\033[0m");
				showMainMenu();
				return;
			} else {
				System.out.println("\033[31mCode PIN incorrect.\033[0m");
			}

			attempts++;
			if (attempts < 3) {
				System.out.println("\033[33mTentative " + attempts + " sur 3. Veuillez réessayer.\033[0m");
				enteredPin = scanner.nextLine();
			}
		}
		System.out.println(
				"\033[31mVotre carte est bloquée après 3 tentatives incorrectes, contactez le support au 0836656565.\033[0m");
	}

	private void showMainMenu() {
		System.out.println("\033[35m===================================\033[0m");
		System.out.println("\033[1;35m        Menu Principal            \033[0m");
		System.out.println("\033[35m===================================\033[0m");

		System.out.println("\n\033[36mQue souhaitez-vous faire ?\033[0m");
		System.out.println("\033[90m1. Retirer de l'argent\033[0m");
		System.out.println("\033[90m2. Consulter votre solde\033[0m");
		System.out.println("\033[90m3. Quitter\033[0m");
		System.out.println("\033[90m===================================\033[0m");

		int choice = getUserChoice();
		scanner.nextLine();
		handleOptionsChoice(choice);
	}

	private void handleOptionsChoice(int choice) {
		switch (choice) {
		case 1:
			showWithdrawalMenu();
			break;
		case 2:
			double balance = getBalance();
			System.out.println("\033[1;32mVotre solde est : " + balance + "€\033[0m");
			showMainMenu();
			break;
		case 3:
			System.out.println("\033[32mMerci d'avoir utilisé notre service. À bientôt !\033[0m");
			break;
		default:
			System.out.println("\033[31mChoix invalide. Veuillez réessayer.\033[0m");
			showMainMenu();
			break;
		}
	}

	private void showWithdrawalMenu() {
		System.out.println("\033[35m===================================\033[0m");
		System.out.println("\033[1;35m       Menu de Retrait             \033[0m");
		System.out.println("\033[35m===================================\033[0m");

		System.out.println("\033[90m1. 10€\033[0m");
		System.out.println("\033[90m2. 20€\033[0m");
		System.out.println("\033[90m3. 50€\033[0m");
		System.out.println("\033[90m4. 100€\033[0m");
		System.out.println("\033[90m5. 200€\033[0m");
		System.out.println("\033[90m6. 500€\033[0m");
		System.out.println("\033[90m7. Entrer un montant personnalisé\033[0m");
		System.out.println("\033[90m8. Retourner au menu principal\033[0m");
		System.out.println("\033[90m===================================\033[0m");

		int choice = getUserChoice();
		handleWithdrawalChoice(choice);
	}

	private void handleWithdrawalChoice(int choice) {
		double amount = 0;

		switch (choice) {
		case 1:
			amount = 10;
			break;
		case 2:
			amount = 20;
			break;
		case 3:
			amount = 50;
			break;
		case 4:
			amount = 100;
			break;
		case 5:
			amount = 200;
			break;
		case 6:
			amount = 500;
			break;
		case 7:
			System.out.print("Entrez le montant: ");
			amount = scanner.nextDouble();
			break;
		case 8:
			showMainMenu();
			return;
		default:
			System.out.println("\033[31mChoix invalide. Veuillez réessayer.\033[0m");
			showWithdrawalMenu();
			return;
		}

		String result = withdraw(amount);
		System.out.println("\033[1;32m" + result + "\033[0m");
		showMainMenu();
	}

	private String withdraw(double amount) {
		double balance = customerAccountService.getBalance(currentCard);

		if (balance >= amount) {
			customerAccountService.updateBalance(currentCard, balance - amount); // Mise à jour du solde
			return "Retrait de " + amount + "€ effectué avec succès.";
		} else {
			return "Fonds insuffisants !";
		}
	}

	// Méthode simulant l'obtention du solde (exemple simple)
	private double getBalance() {
		// Logique pour obtenir le solde (à implémenter)
		return customerAccountService.getBalance(currentCard);
	}
}
