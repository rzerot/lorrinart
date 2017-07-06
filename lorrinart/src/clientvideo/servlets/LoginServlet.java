package clientvideo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clientvideo.datasource.DataSourceFactory;
import clientvideo.security.PBKDF2;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5359935565217887658L;

	ResultSet rs = null;
	PreparedStatement ps = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
		String inputUsername = req.getParameter("email").toLowerCase();
		String inputPassword = req.getParameter("password");

		String queryString = "SELECT * FROM USERS where USERNAME=?";

		try {
			ps = DataSourceFactory.getOracleDataSource().getConnection()
					.prepareStatement(queryString);

			ps.setString(1, inputUsername);
			// ps.setString(2, inputPassword);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// have to change the check for username and password
//			HttpSession httpSession = req.getSession(false);
//			if(httpSession.getLastAccessedTime())
			System.out.println();
			if (rs.next()
					&& PBKDF2.validatePassword(inputPassword,
							rs.getString("USER_PASSWORD".toUpperCase()))) {
				HttpSession session = req.getSession();
				session.setAttribute("user", "Rot");
				session.setMaxInactiveInterval(30 * 60);
			
				Cookie userName = new Cookie("user", inputUsername);
				userName.setMaxAge(30 * 60);
				resp.addCookie(userName);

				System.out.println("success");
				resp.sendRedirect("Success.jsp");

			} else {

				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/login.html");

				System.out.println("fail");
				PrintWriter out = resp.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(req, resp);

			}
		} catch (SQLException | NoSuchAlgorithmException
				| InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
