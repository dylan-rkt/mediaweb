package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

@WebServlet("/retourdoc")
public class RetourDocument extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int idDoc = Integer.parseInt(request.getParameter("id"));
		Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
				null);
		Document d = Mediatheque.getInstance().getDocument(idDoc);
		try {
			d.rendre(u);
		} catch (RetourException e) {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"
					+ "alert(\"Erreur lors du retour (document peut-être déjà retourné ?)\");"
					+ "location='connexion';"
					+ "console.log(location);"
					+ "</script>");
		}
		response.sendRedirect(request.getContextPath() + "/connexion");
	}
}
