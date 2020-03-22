package persistance;

public class DVD extends ADocument {
	
	public DVD(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String typeDoc() {
		return "DVD";
	}

}
