<%@page import="com.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<%
session=request.getSession(false);

UserBean bean= (UserBean)session.getAttribute("UserBean");
if(session.getAttribute("UserBean")!=null){
%>
<body>
<h2 style="color:yellow"> Hello <%=bean.getFirstName()%></h2>
  <a  href="ResetPassword.jsp">Reset Password</a>
  <a  href="Update.jsp"> Edit</a>
  <a href="UserListCtl.do">List</a>
  <a href="UserLogoutCtl">Logout</a>
</body>
<%}
else{
	request.setAttribute("msg", "session has been expired");
	request.getRequestDispatcher("Login.jsp").forward(request, response);
}
%>
</html>