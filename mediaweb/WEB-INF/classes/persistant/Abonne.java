package persistant;

import mediatheque.Utilisateur;

public class Abonne implements Utilisateur {
	private String login;
	
	public Abonne(String login) {
		this.login = login;
	}
	
	@Override
	public boolean isBibliothecaire() {
		return false;
	}
	
	public String toString() {
		return login;
	}

}
