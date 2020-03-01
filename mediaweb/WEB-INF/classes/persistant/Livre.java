package persistant;

public class Livre extends DocumentAbs {
	
	public Livre(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String type() {
		return "Livre";
	}

}
