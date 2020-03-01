package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	public static final String VUE = "/Ressources/Layer/connected.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("persistant.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		if(!((String) request.getSession().getAttribute("login")).isEmpty()) {
			getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		
		if(connectUser(request)) {
			request.getSession().setAttribute("login", request.getParameter("login"));
			
			getServletContext().getRequestDispatcher(VUE).forward(request, response);

		} else {	// Affichage d'un message d'erreur
			out.println("<script type='text/javascript'>"
					+ "alert(\"Nom d'utilisateur ou mot de passe incorrect\");"
					+ "location='index.jsp';"
					+ "console.log(location);"
					+ "</script>");
		}
	}
	
	public boolean connectUser(HttpServletRequest request) {
		boolean loginStatus = false;
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		Utilisateur u = null;
		if((u = Mediatheque.getInstance().getUser(login, password)) != null) {
			loginStatus = true;
			request.getSession().setAttribute("userBiblio", u.isBibliothecaire());
		}
		return loginStatus;
	}
}