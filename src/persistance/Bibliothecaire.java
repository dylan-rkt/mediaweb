package persistance;

import mediatek2020.items.Utilisateur;

public class Bibliothecaire implements Utilisateur {
	private String name;
	
	public Bibliothecaire(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isBibliothecaire() {
		return true;
	}
	
	@Override
	public String name() {
		return this.name;
	}
	
	@Override
	public Object[] data() {
		return new String[]{this.name};
	}
}
