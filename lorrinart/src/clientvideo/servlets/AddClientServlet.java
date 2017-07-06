package clientvideo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clientvideo.POJO.Client;
import clientvideo.dao.ClientService;

public class AddClientServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Client client = new Client();
		//client.setClient_id(888);

		Enumeration<String> attr= req.getParameterNames();
		System.out.println("before while");
		while(attr.hasMoreElements()){
	String parameter=attr.nextElement();
	System.out.println("before if");
	System.out.println();
			if (req.getParameter(parameter).trim().isEmpty()){
				System.out.println("one param empty");
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/Success.jsp");
				PrintWriter out = resp.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(req, resp);
			}
	
	
		//	System.out.println(req.getParameter(parameter));
		}


		client.setNameOne(req.getParameter("nameone"));
		//System.out.println(req.getParameter("nameone"));
		client.setNameTwo(req.getParameter("nametwo"));
	//	System.out.println(req.getParameter("nametwo"));
		client.setNameOneImage(req.getParameter("nameoneimage"));
		client.setNameTwoImage(req.getParameter("nametwoimage"));
		client.setLinkOne(req.getParameter("linkone"));
		client.setLinkTwo(req.getParameter("linktwo"));
		client.setClientPass(req.getParameter("password"));
	
		client.setUsername(req.getParameter("username"));
		
		System.out.println(req.getParameter("password"));
		ClientService clientService = new ClientService();
		
		clientService.insert(client);
		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher("/Success.jsp");
	
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
