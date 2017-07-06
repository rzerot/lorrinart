package clientvideo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 437622070231095784L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);
		if (session != null) {
			System.out.println("Session is not null");
			session.invalidate();
		}
			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher("/logout.html");
			
				System.out.println("Going over request dispatch");
			rd.include(req, resp);
//		resp.sendRedirect("/logout.html");

	}

}
