package persistant;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.Utilisateur;

public abstract class DocumentAbs implements Document{
	private String titre, auteur, logUser, idDoc;
	
	public DocumentAbs(String titre, String auteur, String logUser, String id) {
		this.titre = titre;
		this.auteur = auteur;
		this.logUser = logUser;
		this.idDoc = id;
	}
	
	protected abstract String type();
	
	// une valeur de tableau = une ligne
	@Override
	public String[] data() {
		return new String[]{type(), titre, auteur, logUser, idDoc};
	}

	@Override
	public void emprunter(Utilisateur u) throws EmpruntException {
		if(idDoc == null) {
			throw new EmpruntException();
		} else {
			MediathequeData.updateEmprunteur(Integer.parseInt(idDoc), u.toString());
		}
	}

	@Override
	public void rendre(Utilisateur u) {
		MediathequeData.updateEmprunteur(Integer.parseInt(idDoc), null);
	}
	
	@Override
	public void reserver(Utilisateur u) {
		//Afficher message : "Action encore indisponible"
	}
}
