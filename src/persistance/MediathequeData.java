package persistance;

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

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediaweb?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Document> tousLesDocuments() {
		List<Document> docs = new ArrayList<Document>();
		String sql = "SELECT * FROM documents";
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();
			
			while (result.next()) {
				int typeDoc = result.getInt("typeDoc");
				String title = result.getString("title");
				String author = result.getString("author");
				String login = result.getString("login");
				String idDoc = result.getString("idDoc");
				docs.add(DocumentFactory.creerDocument(typeDoc, title, author, login, idDoc));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return docs;
	}

	@Override
	public Utilisateur getUser(String login, String password) { // Renvoie NULL si User inexistant
		String sql;
		boolean userIsExist;
		
		if (password != null) {
			sql = "SELECT * FROM users WHERE login = ? AND password = ?";
			userIsExist = true;
		}
		else {
			sql = "SELECT * FROM users WHERE login = ?";
			userIsExist = false;
		}
		
		ResultSet result;
		Utilisateur u = null;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			
			if (userIsExist) 
				statement.setString(2, password);
			
			result = statement.executeQuery();
			if (result.next()) {
				if (result.getString("typeUser").equals("abonne"))
					u = new Abonne(result.getString("login"));
				else if (result.getString("typeUser").equals("bibliothecaire"))
					u = new Bibliothecaire(result.getString("login"));
			} 
			else {
				System.out.println("Login et/ou mot de passe incorrect(s)");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public Document getDocument(int idDoc) { // si idDoc n'existe pas, renvoie null
		Document doc = null;
		String sql = "SELECT * FROM documents WHERE idDoc = ?";
		ResultSet result;
		
		try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, idDoc);
				result = statement.executeQuery();
				
				if (result.next()) {
					int typeDoc = result.getInt("typeDoc");
					String title = result.getString("title");
					String author = result.getString("author");
					String login = result.getString("login");
					doc = DocumentFactory.creerDocument(typeDoc, title, author, login, Integer.toString(idDoc));
			} 
			else {
				System.out.println("Le document n'a pas été trouvé");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	@Override
	public void nouveauDocument(int type, Object... args) {
		String sql = "INSERT INTO documents(title, author, typeDoc)" + "VALUES(?,?,?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, (String) args[0]);
			statement.setString(2, (String) args[1]);
			statement.setInt(3, type);
			if (statement.executeUpdate() != 0) {
				System.out.println("Le document a été ajouté avec succès !");
			} else {
				System.out.println("L'ajout n'a pas pu être effectué");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void changerEmprunteur(int idDoc, String loginUser) {
		String sql = "UPDATE documents SET login = ? WHERE idDoc = ?";
		try {
			statement = connection.prepareStatement(sql);		
			if (loginUser != null)
				statement.setString(1, (String) loginUser);
			else
				statement.setNull(1, java.sql.Types.VARCHAR);
			statement.setInt(2, idDoc);
			if (statement.executeUpdate() != 0) {
				System.out.println("Le document a bien été mis à jour !");
			} 
			else {
				System.out.println("La mise à jour n'a pas pu être effectuée");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
