package persistant;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.Utilisateur;

public abstract class ADocument implements Document{
	private String title, author, user, idDoc;
	
	public ADocument(String title, String author, String user, String idDoc) {
		this.title = title;
		this.author = author;
		this.user = user;
		this.idDoc = idDoc;
	}
	
	protected abstract String typeDoc();
	
	// une valeur de tableau = une ligne
	@Override
	public String[] data() {
		return new String[]{typeDoc(), title, author, user, idDoc};
	}

	@Override
	public void emprunter(Utilisateur u) throws EmpruntException {
		if(idDoc == null) throw new EmpruntException();
		MediathequeData.updateEmprunteur(Integer.parseInt(idDoc), u.name());
	}

	@Override
	public void rendre(Utilisateur u) {
		MediathequeData.updateEmprunteur(Integer.parseInt(idDoc), null);
	}
	
	@Override
	public void reserver(Utilisateur u) throws ReservationException {
		synchronized(this) {
			throw new ReservationException();
		}
	}
}
