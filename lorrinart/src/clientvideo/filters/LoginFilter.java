package clientvideo.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();

		// System.out.println(uri);
		// System.out.println(uri.endsWith("login.html"));
		// System.out.println("seesion and loginservlet");
		// System.out.println(session == null);
		// System.out.println("loginservlet");
		// System.out.println((uri.endsWith("loginservlet")));
		// System.out.println("login");
		// System.out.println(uri.endsWith("login.html"));
		// System.out.println("session & !loginservlet");
		// System.out.println(session == null &&
		// !(uri.endsWith("loginservlet")));
		// System.out.println("session & !login.html");
		// System.out.println(session == null && !(uri.endsWith("login.html")));
		// System.out.println("!(login  | servlet)");
		// System.out.println(!(uri.endsWith("login.html") || uri
		// .endsWith("loginservlet")));
		// System.out.println(!(uri.endsWith("login.html") || uri
		// .endsWith("loginservlet")));
		// session.invalidate();
		
		
		
//		String user = (String) session.getAttribute("user");
//		 		String userName = null;
//		 		String sessionID = null;
//		 		String age = null;
//		 		Cookie[] cookies = request.getCookies();
//		 		if(cookies!=null){
//		 	for (Cookie c: cookies){
//		 		System.out.println("Nume cookie"+c.getName());
//		 		System.out.println("Cookie age"+c.getMaxAge());
//		 		if(c.getName().equals("user")) {
//		 	userName = c.getValue();
//		 	
//		 		}
//		 		if(c.getName().equals("JSESSIONID")){
//		 	sessionID= c.getValue();
//		 	Enumeration<String> names = session.getAttributeNames();
//		 	while(names.hasMoreElements()){
//		 		String name = names.nextElement();
//		 		String value =(String) session.getAttribute(name);
//		 		System.out.println("nume: "+ name + "valoare: "+value);
//		 		System.out.println(session.getMaxInactiveInterval());
//		 	}
//		 	
//		 	
//		 		}
//		 		age =String.valueOf(session.getMaxInactiveInterval());
//		 		}
//		 		}

		if (session == null
				&& !(uri.endsWith("login.html") || uri.endsWith("loginservlet"))) {
			System.out.println("hereeeeeeeeeee");
			// pass the request along the filter chain
			req.getRequestDispatcher("login.html").forward(req, resp);
		} else {

			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
