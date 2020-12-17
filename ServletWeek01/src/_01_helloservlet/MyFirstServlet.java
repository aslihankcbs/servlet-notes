package _01_helloservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("MyFirstServlet#doGet is called");
		//üstteki doGet metodunu çaðýrmayacaðýz.Üst classta hata basýyor o kýsým incele !
		//super.doGet(req, resp);
		
		PrintWriter pw= resp.getWriter();
		
		LocalTime localTime = LocalTime.now();
		//printwriter print metodunun içerisine html yazýlabiliyor. Yazmak best practice deðil ama yazýlabilir.
		String message = "HelloServlet!";
		pw.print("<h1>"+ localTime+" </h1>");
		pw.print("<h1>"+ message+" </h1>");
	}
}

// 1) HttpServlet sýnýfýný extends et

// HTTP GET ->Kaynak isteðinde kullanýlýr
// HTTP POST ->Bir form submit edildiðinde bunu post ile server'ý göndeririz.

//
