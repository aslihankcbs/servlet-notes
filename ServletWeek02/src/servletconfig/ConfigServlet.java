package servletconfig;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//getServletConfig GenericServlet s�n�f�nda yer almaktad�r. 
		//ServletConfig objesi elimize gelmektedir Bu sayede. 
		ServletConfig  config =  getServletConfig();
		
		
		//config �zerinden initParameter'lara eri�im sa�lanabilmektedir.
		String name = config.getInitParameter("username");
		String pass = config.getInitParameter("password");
		
		resp.getWriter().print(name + " " + pass);
		
		String servletName = config.getServletName();
		ServletContext context = config.getServletContext();
		//parametrelerin isimleri d�n�l�r.
		Enumeration<String> names = config.getInitParameterNames();
	}

}
