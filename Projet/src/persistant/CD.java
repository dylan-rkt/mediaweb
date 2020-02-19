package persistant;

public class CD extends DocumentAbs {
	
	public CD(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String type() {
		return "CD";
	}
}
