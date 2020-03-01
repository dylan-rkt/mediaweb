package persistant;

public class Revue extends DocumentAbs {
	
	public Revue(String titre, String auteur, String logUser, String idDoc) {
		super(titre, auteur, logUser, idDoc);
	}

	@Override
	protected String type() {
		return "Revue";
	}
}