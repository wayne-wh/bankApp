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
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int withdrawAmount = Integer.parseInt(req.getParameter("amount"));
		HttpSession session = req.getSession();
		int balance = (int) session.getAttribute("balance");
		if (withdrawAmount > balance) {
			resp.sendRedirect("/BankApp/failedWithdrawal.html");
		} else {
			Model m = new Model();
			int finalBalance = balance - withdrawAmount;
			String un = (String) session.getAttribute("un");
			m.setUn(un);
			m.setBalance(finalBalance);
			int x = m.withdraw();
			if (x == 0) {
				resp.sendRedirect("/BankApp/failedWithdrawal.html");
			} else {
				resp.sendRedirect("/BankApp/successWithdrawal.html");
			}
		}
	}

}
