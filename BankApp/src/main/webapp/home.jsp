<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
<%
	out.println("Welcome "+session.getAttribute("name"));
%>
</h1><br>

<a href="/BankApp/changepw.html">Change Password</a><br>
<a href="/BankApp/remit.html">Remit Fund</a><br>
<a href="/BankApp/deposit.html">Deposit</a><br>
<a href="/BankApp/withdraw.html">Withdraw</a><br>
<a href="Logout">Logout</a>
</body>
</html>