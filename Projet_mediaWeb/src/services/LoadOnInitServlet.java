package services;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2020.Mediatheque;
import mediatek2020.items.Utilisateur;

@WebServlet(urlPatterns="/ident",loadOnStartup=1)
public class LoadOnInitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW = "/view/jsp/dashboard.jsp";
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		System.out.println("******************************************************************");
		try {
			Class.forName("persistant.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!((String) request.getSession().getAttribute("login")).isEmpty()) {
			getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} 
		else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (connectUser(request)) {
			request.getSession().setAttribute("login", request.getParameter("login"));
			getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} 
		else {
			response.sendRedirect(request.getContextPath() + "?lmdp=yes");
		}
	}
	
	public boolean connectUser(HttpServletRequest request) {
		boolean loginStatus = false;
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		Utilisateur u = null;
		
		if ((u = Mediatheque.getInstance().getUser(login, password)) != null) {
			loginStatus = true;
			request.getSession().setAttribute("userBiblio", u.isBibliothecaire());
		}
		return loginStatus;
	}
}