package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2020.Mediatheque;

@WebServlet("/ajoutdoc")
public class AjoutDocument extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		if(request.getParameter("inputTitre").isEmpty() || request.getParameter("inputAuteur").isEmpty() || request.getParameter("type").isEmpty()) {
			out.println("<script type='text/javascript'>"
					+ "alert(\"Certains champs sont manquants\");"
					+ "location='" + request.getContextPath() + "/ident'"
					+ "</script>");
		} else {
			int typeDoc = Integer.parseInt(request.getParameter("type"));
			String title = request.getParameter("inputTitre");
			String author = request.getParameter("inputAuteur");
			Mediatheque.getInstance().nouveauDocument(typeDoc, title, author);
			response.sendRedirect(request.getContextPath() + "/ident");
		}
	}
}
