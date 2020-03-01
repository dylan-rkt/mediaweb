package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/empruntdoc")
public class EmpruntDocument extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int idDoc = Integer.parseInt(request.getParameter("id"));
		Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
				null);
		Document d = Mediatheque.getInstance().getDocument(idDoc);
		try {
			d.emprunter(u);
		} catch (EmpruntException e) {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"
					+ "alert(\"Erreur lors de l'emprunt (document peut-être déjà emprunté ?)\");"
					+ "location='connexion';"
					+ "console.log(location);"
					+ "</script>");
		}
		response.sendRedirect(request.getContextPath() + "/connexion");
	}
}
