package persistant;

import mediatek2020.items.Document;

public class DocumentFactory {
	public static Document creerDocument(int type, String auteur, String titre, String logUser, String idDoc) {
		Document d = null;
		switch(type) {
			case 1:	// Livre
				d = new Livre(auteur, titre, logUser, idDoc);
				break;
			case 2:
				d = new CD(auteur, titre, logUser, idDoc);
				break;
			case 3:
				d = new DVD(auteur, titre, logUser, idDoc);
				break;
		}
		return d;
	}
}
