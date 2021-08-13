package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Model;

/**
 * Servlet implementation class ResetPwd
 */
@WebServlet("/ResetPwd")
public class ResetPwd extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userOtp = req.getParameter("otp");
		String newPwd = req.getParameter("newPwd");
		String cnewpwd = req.getParameter("cnewpwd");
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		String otp = (String) session.getAttribute("otp");
		
		if (userOtp.equals(otp) && newPwd.equals(cnewpwd))
		{
			Model m = new Model();
			m.setEmail(email);
			m.setNewpwd(cnewpwd);
			
			int x = m.resetPwd();
			
			if(x ==1 )
			{
				//Successful reset
				System.out.println("Password has been reset");
			}
			else
			{
				System.out.println("Password was not reset");
			}
		}
		else
		{
			System.out.println("OTP does not match");
		}
		
	}

}
