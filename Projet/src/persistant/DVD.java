package persistant;

public class DVD extends DocumentAbs {
	
	public DVD(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String type() {
		return "DVD";
	}

}
