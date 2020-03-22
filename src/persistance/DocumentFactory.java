package persistance;

import mediatek2020.items.Document;

public class DocumentFactory {
	public static Document creerDocument(int typeDoc, String auteur, String titre, String logUser, String idDoc) {
		Document d = null;
		switch(typeDoc) {
			case 1:	// Livre
				d = new Livre(auteur, titre, logUser, idDoc);
				break;
			case 2:
				d = new DVD(auteur, titre, logUser, idDoc);
				break;
			case 3:
				d = new CD(auteur, titre, logUser, idDoc);
				break;
		}
		return d;
	}
}
