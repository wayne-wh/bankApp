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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		
		if (email.equals("admin@gmail.com")) {
			Model m = new Model();
			int check = m.activeUsers();
			if (check == 1) {
				int num_users = m.getNo_users();
				System.out.println(num_users);
				HttpSession session = req.getSession(true);
				session.setAttribute("num_users", num_users);
				resp.sendRedirect("/BankApp/admin.jsp");
			} else {
				resp.sendRedirect("/BankApp/notregistered.html");
			}
		} else {
			Model m = new Model();
			m.setEmail(email);
			m.setPwd(pwd);
			
			int x = m.login();
			if (x==0) {
				resp.sendRedirect("/BankApp/notregistered.html");
			} else {
				String name = m.getName();
				String un = m.getUn();
				int balance = m.getBalance();
				HttpSession session = req.getSession(true);
				session.setAttribute("name", name);
				session.setAttribute("un", un);
				session.setAttribute("balance", balance);
				resp.sendRedirect("/BankApp/home.jsp");
			}
		}
		
	}

}
