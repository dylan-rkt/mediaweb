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
import mediatek2020.items.ReservationException;
import mediatek2020.items.Utilisateur;

@WebServlet("/reservationdoc")
public class ReserverDocument extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int idDoc = Integer.parseInt(request.getParameter("id"));
		Utilisateur u = Mediatheque.getInstance().getUser((String) request.getSession().getAttribute("login"), 
				null);
		Document d = Mediatheque.getInstance().getDocument(idDoc);
		try {
			d.reserver(u);
		} catch (ReservationException e) {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"
					+ "alert(\"Erreur lors de la réservation (Action encore indisponible)\");"
					+ "</script>");
		}
		response.sendRedirect(request.getContextPath() + "/connexion");
	}
}
