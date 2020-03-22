package persistance;

import mediatek2020.items.Utilisateur;

public class Abonne implements Utilisateur {
	private String name;
	
	public Abonne(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isBibliothecaire() {
		return false;
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
