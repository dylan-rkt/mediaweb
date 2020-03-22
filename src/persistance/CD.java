package persistance;

public class CD extends ADocument {
	
	public CD(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String typeDoc() {
		return "CD";
	}
}
