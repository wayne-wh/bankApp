package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet res;
	
	private String name;
	private String email;
	private String un;
	private String pwd;
	private String newpwd;
	private int accNo;
	private int balance;
	private int no_users;
	private int min = 100000;
	private int max = 999999;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getNo_users() {
		return no_users;
	}
	public void setNo_users(int no_users) {
		this.no_users = no_users;
	}
	
	public Model() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApp", "root", "qwer1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int register() {
		try {
			String sql = "Insert into users values(?,?,?,?,?,?)";
			int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
			setAccNo(random_int);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, un);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, accNo);
			pstmt.setInt(6, 0);
			
			int x = pstmt.executeUpdate();
			return x;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int login() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * from users where email=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			if (res.next()==true) {
				name = res.getString("name");
				un = res.getString("username");
				balance = res.getInt("balance");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int changePwd() {
		
		try {
			String sql1 = "Select * from users where username=? AND password=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1,un);
			pstmt.setString(2,pwd);
			
			res = pstmt.executeQuery();
			if (res.next()==true) {
				String sql2 = "UPDATE USERS SET PASSWORD=? WHERE USERNAME=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1,newpwd);
				pstmt.setString(2,un);
				
				int x = pstmt.executeUpdate();
				return x;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int withdraw() {
		// TODO Auto-generated method stub
		try {
			String sql1 = "Select * from users where username=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1,un);
			
			res = pstmt.executeQuery();
			System.out.println(res);
			if (res.next()==true) {
				String sql2 = "UPDATE USERS SET BALANCE=? WHERE USERNAME=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1,balance);
				pstmt.setString(2,un);
				
				int x = pstmt.executeUpdate();
				return x;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int resetPwd() {
		try {
			String sql = "Update users set password=? where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newpwd);
			pstmt.setString(2, email);
			
			int x = pstmt.executeUpdate();
			return x;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int activeUsers() {
		try {
			String sql = "Select count(name) as no_users from users";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			if (res.next()==true) {
				no_users = res.getInt("no_users");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
