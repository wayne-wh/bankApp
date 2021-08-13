package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class ForgotPwd
 */
@WebServlet("/ForgotPwd")
public class ForgotPwd extends HttpServlet {
	
	Random rand = new Random();
	int otp = rand.nextInt(900000)+100000;
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toEmail = req.getParameter("email");
		String fromEmail = Credentials.email; //sender's mail id.
		String pwd = Credentials.pwd;		//sender's mail pwd.
//		String toEmail="";  //reciever's mail id.
		
		String subject = "DO NOT REPLY: Mail.";
		String msg="Hi user, \n Here is the reset Password Link: http://localhost:8080/BankApp/resetPwd.html , also enter this OTP when asked for:" + otp; //mail body
		
		HttpSession sess = req.getSession(true);
		sess.setAttribute("email", toEmail);
		sess.setAttribute("otp", otp);
		
		// Creating Session Object
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail, pwd);
			}
		});
		
//		Session session = Session.getDefaultInstance(prop, new Authenticator() {
//		    protected PasswordAuthentication getPasswordAuthentication() {
//		        return new PasswordAuthentication(fromEmail, pwd);
//		    }
//		});
		
		try {
			//Composing the Mail
			MimeMessage mesg = new MimeMessage(session);
			mesg.setFrom(new InternetAddress(fromEmail));
			mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			mesg.setSubject(subject);  
			mesg.setText(msg);  
			
			//Sending the Mail
			Transport.send(mesg);
			System.out.println("Mail Sent!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
