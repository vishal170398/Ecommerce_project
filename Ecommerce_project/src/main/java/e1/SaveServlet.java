package e1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String firstname=request.getParameter("name1");
		String lastname=request.getParameter("name2");
		String email=request.getParameter("email1");
		long phone=Long.parseLong(request.getParameter("phone"));
		String website=request.getParameter("website");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		
		student e=new student();
		e.setFirstname(firstname);
		
		e.setLastname(lastname);
		e.setEmail(email);
		e.setPhone(phone);
		e.setWebsite(website);
		e.setPassword(password);
		e.setRepassword(repassword);
		
    int status=StudCon.save(e);
		
		if(status>0)
		{
			out.print("<p>Record saved successfull </p>");
			request.getRequestDispatcher("Loginpage.html").include(request, response);
		}
		else {
			out.print("Sorry! unable to save record");
		}
		out.close();
	}
		
	}

