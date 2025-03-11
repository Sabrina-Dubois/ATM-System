package co.simplon.atm_system.business;

import co.simplon.atm_system.model.Card;

public class ATMService {

	private AuthentificationService authService;
	private CustomerAccountService customerAccountService;
	private CSVService csvService;
	private Card currentCard;
	private boolean isJammed;

	public ATMService(CSVService csvService, AuthentificationService authService,
			CustomerAccountService customerAccountService) {
		this.csvService = csvService;
		this.authService = authService;
		this.customerAccountService = customerAccountService;
		this.isJammed = false;

	}

	// Valider le PIN, ne fait qu'appeler AuthentificationService
	public String validatePin(String pin) {
		this.currentCard = authService.authenticate(pin);
		if (this.currentCard == null) {
			return "⚠️ Code PIN incorrect ou carte bloquée.";
		}
		return "Accès autorisé";
	}

	// Effectuer un retrait en déléguant au CustomerAccountService
	public String withdrawMoney(double amount) {
		if (this.currentCard == null) {
			return "Aucune carte authentifiée.";
		}
		if (isJammed) {
			return "⚠️ Problème mécanique. Veuillez contacter le support.";
		}
		String result = customerAccountService.withdrawMoney(currentCard, amount);
		if (result.startsWith("✅")) {
			csvService.saveChanges(customerAccountService.getCards(), customerAccountService.getAccounts(),
					getAtmBalance()); // Enregistrer les changements
		}
		return result;
	}

	public double getBalance() {
		if (this.currentCard == null) {
			return 0.0; // Si aucune carte n'est authentifiée
		}

		// Utiliser le CustomerAccountService pour obtenir le solde
		return customerAccountService.getBalance(this.currentCard);
	}

	public void setJammed(boolean isJammed) {
		this.isJammed = isJammed;
	} // Ajouter cette méthode pour récupérer le solde de l'ATM

	public double getAtmBalance() {
		return csvService.readAtmBalance();
	}

}
