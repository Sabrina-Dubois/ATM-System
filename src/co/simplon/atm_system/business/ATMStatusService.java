package co.simplon.atm_system.business;

public class ATMStatusService {

	private double atmBalance;
	private boolean isJammed;

	public ATMStatusService(double atmBalance) {
		this.atmBalance = atmBalance;
		this.isJammed = false;
	}

	public boolean isJammed() {
		return isJammed;
	}

	public void setJammed(boolean isJammed) {
		this.isJammed = isJammed;
	}

	public double getAtmBalance() {
		return atmBalance;
	}

	public void updateAtmBalance(double amount) {
		atmBalance -= amount;
	}
}
