package services;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		System.out.println("******************************************************************");
		try {
			Class.forName("persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!((String) request.getSession().getAttribute("login")).isEmpty()) {
			getServletContext().getRequestDispatcher("/view/jsp/dashboard.jsp").forward(request, response);
		} 
		else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		boolean status = false;
		Utilisateur user = null;
		
		if ((user = Mediatheque.getInstance().getUser(login, password)) != null) {
			status = true;
			request.getSession().setAttribute("userBiblio", user.isBibliothecaire());
		}
		
		if (status) {
			request.getSession().setAttribute("login", request.getParameter("login"));
			getServletContext().getRequestDispatcher("/view/jsp/dashboard.jsp").forward(request, response);
		}
		else {
			out.println("<script type='text/javascript'>"
					+ "alert(\"Nom d'utilisateur ou mot de passe incorrect\");"
					+ "location='" + request.getContextPath() + "'"
					+ "</script>");
		}
	}
}