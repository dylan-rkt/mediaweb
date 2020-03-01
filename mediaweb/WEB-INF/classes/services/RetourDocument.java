package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/retourdoc")
public class RetourDocument extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int idDoc = Integer.parseInt(request.getParameter("id"));
		Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
				null);
		Document d = Mediatheque.getInstance().getDocument(idDoc);
		d.retour();
		response.sendRedirect(request.getContextPath() + "/connexion");
	}
}
