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
		
		
		//getServletConfig GenericServlet sýnýfýnda yer almaktadýr. 
		//ServletConfig objesi elimize gelmektedir Bu sayede. 
		ServletConfig  config =  getServletConfig();
		
		
		//config üzerinden initParameter'lara eriþim saðlanabilmektedir.
		String name = config.getInitParameter("username");
		String pass = config.getInitParameter("password");
		
		resp.getWriter().print(name + " " + pass);
		
		String servletName = config.getServletName();
		ServletContext context = config.getServletContext();
		//parametrelerin isimleri dönülür.
		Enumeration<String> names = config.getInitParameterNames();
	}

}
