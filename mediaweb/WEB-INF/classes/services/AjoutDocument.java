package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

@WebServlet("/ajoutdoc")
public class AjoutDocument extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		if(request.getParameter("inputTitre").isEmpty() || request.getParameter("inputAuteur").isEmpty()) {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"
					+ "alert(\"Certains champs sont manquants\");"
					+ "location='connexion';"
					+ "console.log(location);"
					+ "</script>");
		} else {
			int type = Integer.parseInt(request.getParameter("type"));
			String title = request.getParameter("inputTitre");
			String author = request.getParameter("inputAuteur");
			Mediatheque.getInstance().nouveauDocument(type, title, author);
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}
}
