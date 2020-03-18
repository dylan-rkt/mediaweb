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
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.Utilisateur;

@WebServlet("/empresdoc")
public class EmpResDocument extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		if(request.getParameter("id") == null) {
			out.println("<script type='text/javascript'>"
					+ "alert(\"S�lectionnez un document SVP\");"
					+ "location='" + request.getContextPath() + "/ident'"
					+ "</script>");
		}
		else {
			int idDoc = Integer.parseInt(request.getParameter("id"));
			Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
					null);
			Document d = Mediatheque.getInstance().getDocument(idDoc);
			try {
				if(request.getParameter("traitement").equals("emprunt"))
					d.emprunter(u);
				else
					d.reserver(u);
				response.sendRedirect(request.getContextPath() + "/ident");
			} catch (EmpruntException e) {
				out.println("<script type='text/javascript'>"
						+ "alert(\"Erreur lors de l'emprunt (document peut-�tre d�j� emprunt� ?)\");"
						+ "location='" + request.getContextPath() + "/ident'"
						+ "</script>");
			} catch (ReservationException e) {
				out.println("<script type='text/javascript'>"
						+ "alert(\"Action encore indisponible \");"
						+ "location='" + request.getContextPath() + "/ident'"
						+ "</script>");
			}
		}
	}
}
