package persistant;

import mediatheque.Utilisateur;

public class Bibliothecaire implements Utilisateur {
	private String login;
	
	public Bibliothecaire(String login) {
		this.login = login;
	}

	@Override
	public boolean isBibliothecaire() {
		return true;
	}
	
	public String toString() {
		return login;
	}
}
