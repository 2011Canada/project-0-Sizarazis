package com.revature.menus;

public class RegistrationState implements BankState {
	boolean willingToRegister = false;
	String password;
	String passwordVerified;
	
	public String Display() {
		// TODO Auto-generated method stub
		String s = "\nHello sir or madam! \n" +
					"If you would like to register for an account, please type \"register\"\n" +
					"\n Please note that this bank comes with no perks, no security, and no guarantees." +
					"Any and all assets you may give us are at your own risk.\n";
		return s;
	}

	public BankState HandleUserInput(String cmd) {
		// TODO Auto-generated method stub
		if (!willingToRegister && cmd.equals("register")) {
			willingToRegister = true;
			return this;
		}
		else if (!willingToRegister && !cmd.equals("register")) {
			System.out.println("Fine! We didn't want you anyways!\n");
			return new WelcomeState();
		}
		else if (willingToRegister) {
			
		}
		return null;
	}

}
