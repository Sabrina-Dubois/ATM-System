package co.simplon.atm_system.business;

import java.util.Map;

import co.simplon.atm_system.config.Configuration;
import co.simplon.atm_system.data.CSVReader;
import co.simplon.atm_system.data.CSVWriter;
import co.simplon.atm_system.model.Card;
import co.simplon.atm_system.model.CustomerAccount;

public class CSVService {
	private CSVReader csvReader;
	private CSVWriter csvWriter;

	public CSVService() {
		this.csvReader = new CSVReader();
		this.csvWriter = new CSVWriter();
	}

	public Map<String, Card> readCards() {
		String cardsFilePath = Configuration.get("cardsFileName");
		Map<String, Card> cards = csvReader.readCardsCSV(cardsFilePath);
		return cards;
	}

	public Map<String, CustomerAccount> readAccounts() {
		String accountsFilePath = Configuration.get("accountsFileName");
		return csvReader.readAccountsCSV(accountsFilePath);
	}

	public double readAtmBalance() {
		String atmFilePath = Configuration.get("atmFileName");
		return csvReader.readAtmFundsCSV(atmFilePath);
	}

	public void saveChanges(Map<String, Card> cards, Map<String, CustomerAccount> accounts, double atmBalance) {
		if (cards == null || cards.isEmpty()) {
			return;
		}
		String cardsFilePath = Configuration.get("cardsFileName");
		String accountsFilePath = Configuration.get("accountsFileName");
		String atmFilePath = Configuration.get("atmFileName");

		csvWriter.writeCardsCSV(cardsFilePath, cards);
		csvWriter.writeAccountsCSV(accountsFilePath, accounts);
		csvWriter.updateAtmFundsCsv(atmFilePath, atmBalance);
	}

	public void writeAccounts(Map<String, CustomerAccount> accounts) {
		String accountsFilePath = Configuration.get("accountsFileName");
		csvWriter.writeAccountsCSV(accountsFilePath, accounts);
	}
}
