package persistant;

import mediatek2020.items.Document;

public class DocumentFactory {
	public static Document creerDocument(String typeDoc, String auteur, String titre, String logUser, String idDoc) {
		Document d = null;
		switch(typeDoc) {
			case "Livre":	// Livre
				d = new Livre(auteur, titre, logUser, idDoc);
				break;
			case "CD":
				d = new CD(auteur, titre, logUser, idDoc);
				break;
			case "DVD":
				d = new DVD(auteur, titre, logUser, idDoc);
				break;
		}
		return d;
	}
}
