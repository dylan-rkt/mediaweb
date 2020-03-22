package persistance;

public class Livre extends ADocument {
	
	public Livre(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String typeDoc() {
		return "Livre";
	}

}
