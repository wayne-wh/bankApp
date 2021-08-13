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
 * Servlet implementation class Changepwd
 */
@WebServlet("/Changepwd")
public class Changepwd extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pwd = req.getParameter("pwd");
		String newPwd = req.getParameter("newPwd");
		String cNewPwd = req.getParameter("cNewPwd");
		
		if(newPwd.equals(cNewPwd))
		{
			HttpSession session = req.getSession();  
			String un = (String) session.getAttribute("un");
			Model m = new Model();
			m.setUn(un);
			m.setPwd(pwd);
			m.setNewpwd(cNewPwd);
			int x = m.changePwd();
			System.out.println(x);
			if (x == 0)
			{
				//password not updated
				resp.sendRedirect("/BankApp/failedPasswordChange.html");
			}
			else
			{
				//password updated
				resp.sendRedirect("/BankApp/successPasswordChange.html");
			}
		
		}
		
	}

}
