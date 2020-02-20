package persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2020.Mediatheque;
import mediatek2020.PersistentMediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.Utilisateur;

//classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
//via une auto-déclaration dans son bloc static
public class MediathequeData implements PersistentMediatheque{
	private static Connection connection;
	private static PreparedStatement statement;
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}
	
	
	private MediathequeData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediaweb", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Document getDocument(int idDoc) { // Renvoie NULL si idDoc inexistant
		Document doc = null;
		String sql = "SELECT * FROM documents WHERE idDoc=" + idDoc;
		ResultSet result;
		try {
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery(sql);
			if(result.next()) {
				String typeDoc = result.getString("typeDoc");
				String data1 = result.getString("title");
				String data2 = result.getString("author");
				String data3 = result.getString("login");
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

	@Override
	public Utilisateur getUser(String login, String password) { //Renvoie NULL si User inexistant
		String sql;
		if(password != null)
			sql = "SELECT * FROM users WHERE login='"
				+ login + "' AND password='" + password + "';";
		else
			sql = "SELECT * FROM users WHERE login='" + login + "';";
		ResultSet result;
		Utilisateur u = null;
		try {
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery(sql);
			if(result.next()) {
				if(result.getString("typeUser").equals("abonné"))
					u = new Abonne(result.getString("login"));
				else if(result.getString("typeUser").equals("bibliothécaire"))
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
		String sql = "INSERT INTO documents(title, author, typeDoc)"
				+ "VALUES('" + args[0] + "','" + args[1] + "'," + type + ")";
		try {
			statement = connection.prepareStatement(sql);
			if(statement.executeUpdate(sql) != 0) {
				System.out.println("Document ajouté avec succès");
			} else {
				System.out.println("Echec lors de l'ajout du document");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public List<Document> tousLesDocuments() { // Renvoie la liste de tous les documents
		List<Document> docs = new ArrayList<Document>();
		String sql = "SELECT * FROM documents";
		ResultSet result = null;
		int cptDoc = 0;
		try {
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery(sql);
			while(result.next()) {
				cptDoc++;
				String typeDoc = result.getString("typeDoc");
				String data1 = result.getString("title");
				String data2 = result.getString("author");
				String data3 = result.getString("login");
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
			statement = connection.prepareStatement(sql);
			if(statement.executeUpdate(sql) != 0) {	// OK
				System.out.println("Document mis à jour avec succès");
			} else {
				System.out.println("Echec lors de la mise à jour du document");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
