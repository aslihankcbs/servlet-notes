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
		//�stteki doGet metodunu �a��rmayaca��z.�st classta hata bas�yor o k�s�m incele !
		//super.doGet(req, resp);
		
		PrintWriter pw= resp.getWriter();
		
		LocalTime localTime = LocalTime.now();
		//printwriter print metodunun i�erisine html yaz�labiliyor. Yazmak best practice de�il ama yaz�labilir.
		String message = "HelloServlet!";
		pw.print("<h1>"+ localTime+" </h1>");
		pw.print("<h1>"+ message+" </h1>");
	}
}

// 1) HttpServlet s�n�f�n� extends et

// HTTP GET ->Kaynak iste�inde kullan�l�r
// HTTP POST ->Bir form submit edildi�inde bunu post ile server'� g�ndeririz.

//
