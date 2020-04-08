<%@page import="com.util.DataUtility"%>
<%@page import="com.util.PropertyReader"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="check" id="check" content="UserList">
<title>Insert title here</title>
<style type="text/css">
		body{
			font-family: verdana;
		}
		table{

			width:100%;
			height:100%;
			border-collapse: collapse;
		}
		table,th,td{
			border: 2px solid black;
		}
		td,th{
			vertical-align: top;
			padding: 5px;
		}
		-
		tr:nth-child(even){
			background: #f2f2f2;
		}
	</style>

</head>
<body>
<%ArrayList<UserBean> list =(ArrayList<UserBean>)request.getAttribute("list"); %>
<form action="UserListCtl.do" method="">
	<a href="Welcome.jsp" >Welcome ||</a>
			<a href="Update.jsp">Update ||</a>
			<a href="ResetPassword.jsp">Reset Password ||</a>
		  <a href="UserLogoutCtl">Logout</a>
<table>
		
<br>
		<caption><h2>User  List  </h2></caption>
		
		<caption><input style="margin:10px;" type="text" name="searchdata" placeholder="search" id="searchdata">
		<select name="searchby">
        <option value="firstname">First Name</option>
        <option value="lastname">Last Name</option>
        <option value="login">Login Id</option>
        <option value="mobilenumber">Mobile Number</option> 
        <option value="gender">Gender</option> 
        </select>
		<button  type="submit" name="operation" id="operation" value="search">Search</button> </caption>
				<tr>
 			<th>Select</th>
 			<th>ID </th>
			<th>Login Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Mobile Number</th>
			<th>Dob</th>
			
			
		</tr>
		<%
		session=request.getSession(false);
		if(session.getAttribute("UserBean")!=null){
		
		int index=Integer.parseInt(session.getAttribute("index").toString());
		int pageLimit=5;
		pageLimit=pageLimit+index;
//	System.out.println(i+"      "+i+j);
				/* for(UserBean ms:list){ */
					Iterator<UserBean> it = list.iterator();
					while (it.hasNext()  &&index<pageLimit && index<list.size()&&index>=0 ) {
						UserBean bean = it.next();
						bean=list.get(index);
					%>
		
		<tr>
		
		<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getId() %></td>
			<td><%=bean.getLogin()%></td>
			<td><%=bean.getFirstName() %></td>
			<td><%=bean.getLastName() %></td>
			<td><%=bean.getGender() %></td>
			<td><%=bean.getMobileNo() %></td>
			<td><%=bean.getDob() %></td>
			
			<%-- <td><a href="UserListCtl?rollNo=<%=bean.getId() %>  &list=<%= "UserList" %>">Delete</a><td> --%>
		</tr>
		
		<%
		index++;

					}
					
					session.setAttribute("index", index);
					
		%>
	
		
	</table>
	<table width='100%'>
	<tr>
	<td><button  type="submit" name="operation" id="operation" value="previousPage">PreviousPage</button></td>
	<td>	<input type="submit" name="operation" id="operation" value="Delete">
	</td>
	<td><button  type="submit" name="operation" id="operation" value="nextPage">NextPage</button>
	</td>
	</tr>
	
	</table>
	

	</form>
</body>
<%}
				else{
				request.setAttribute("msg", "session has been Expired");
				response.sendRedirect("Login.jsp");
				}
				
				%>
</html>