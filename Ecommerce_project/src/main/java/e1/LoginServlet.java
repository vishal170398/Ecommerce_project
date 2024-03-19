package e1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub"
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		String email=request.getParameter("email1");
		String password=request.getParameter("password");
		
		student e = new student();
		e.setEmail(email);
		e.setPassword(password);
		
		int status = StudCon.checklogin(e);
		
		
		
		if(status==1)
		{
			out.print("<p>User Login Successfull </p>");
			request.getRequestDispatcher("Homepage.html").include(request, response);
		}else if(status==2) {
			out.print("<p>Wrong password </p>");
			request.getRequestDispatcher("Loginpage.html").include(request, response);	
		}else if(status==3) {
			out.print("<p>User not found!! create new account...</p>");
			request.getRequestDispatcher("Loginpage.html").include(request, response);
		}
		else {
			out.print("User not found!! create new account...");
		}
		out.close();
	}

}
