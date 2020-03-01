package persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.PersistentMediatheque;
import mediatheque.Utilisateur;

//classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
//via une auto-déclaration dans son bloc static
public class MediathequeData implements PersistentMediatheque{
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}
	
	private static Statement statement;
	
	private MediathequeData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediaweb", "root", "");
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int idDoc) {
		Document doc = null;
		String sql = "SELECT * FROM documents WHERE idDoc=" + idDoc;
		ResultSet result = null;
		try {
			result = statement.executeQuery(sql);
			if(result.next()) {
				int typeDoc = result.getInt("typeDoc");
				String data1 = result.getString("dataDoc1");
				String data2 = result.getString("dataDoc2");
				String data3 = result.getString("logUser");
				doc = DocumentFactory.creerDocument(typeDoc, data1, data2, data3, Integer.toString(idDoc));
			} else {
				System.out.println("Document non trouvé");
			}
			result.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return doc;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String sql = null;
		if(password != null)
			sql = "SELECT * FROM users WHERE login='"
				+ login + "' AND password='" + password + "';";
		else
			sql = "SELECT * FROM users WHERE login='" + login + "';";
		ResultSet result = null;
		Utilisateur u = null;
		try {
			result = statement.executeQuery(sql);
			if(result.next()) {
				if(result.getString("type").equals("abonne"))
					u = new Abonne(result.getString("login"));
				else if(result.getString("type").equals("bibliothecaire"))
					u = new Bibliothecaire(result.getString("login"));
			} else {
				System.out.println("Login et/ou mot de passe incorrect(s)");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		// ajout à la bdd
		String sql = "INSERT INTO documents(dataDoc1, dataDoc2, typeDoc)"
				+ "VALUES('" + args[0] + "','" + args[1] + "'," + type + ")";
		try {
			int status = statement.executeUpdate(sql);
			if(status != 0) {	// Requete effectuee avec succes
				System.out.println("Document ajouté avec succès");
			} else {
				System.out.println("Echec lors de l'ajout du document");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		List<Document> docs = new ArrayList<Document>();
		String sql = "SELECT * FROM documents";
		ResultSet result = null;
		int cptDoc = 0;
		try {
			result = statement.executeQuery(sql);
			while(result.next()) {
				cptDoc++;
				int typeDoc = result.getInt("typeDoc");
				String data1 = result.getString("dataDoc1");
				String data2 = result.getString("dataDoc2");
				String data3 = result.getString("logUser");
				String idDoc = result.getString("idDoc");
				docs.add(DocumentFactory.creerDocument(typeDoc, data1, data2, data3, idDoc));
			}

			result.close();
			System.out.println("Lecture des documents terminée, trouvé : " + cptDoc);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	public static void updateEmprunteur(int idDoc, String loginUser) {
		String sql = null;
		if(loginUser != null)
			sql = "UPDATE documents SET logUser='" + loginUser + "' WHERE idDoc=" + idDoc;
		else
			sql = "UPDATE documents SET logUser=NULL WHERE idDoc=" + idDoc;
		try {
			int status = statement.executeUpdate(sql);
			if(status != 0) {	// Requete effectuee avec succes
				System.out.println("Document mis à jour avec succès");
			} else {
				System.out.println("Echec lors de la mise à jour du document" + status);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
