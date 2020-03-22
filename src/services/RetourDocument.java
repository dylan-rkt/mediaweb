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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		if(request.getParameter("id") == null) {
			out.println("<script type='text/javascript'>"
					+ "alert(\"Sélectionnez un document SVP\");"
					+ "location='" + request.getContextPath() + "/ident'"
					+ "</script>");
		}
		else {
			int idDoc = Integer.parseInt(request.getParameter("id"));
			Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
					null);
			Document d = Mediatheque.getInstance().getDocument(idDoc);
			try {
				d.rendre(u);
				response.sendRedirect(request.getContextPath() + "/ident");
			} catch (RetourException e) {
				out.println("<script type='text/javascript'>"
						+ "alert(\"Le document a déjà été retourné\");"
						+ "location='" + request.getContextPath() + "/ident'"
						+ "</script>");
			}
		}
	}
}
