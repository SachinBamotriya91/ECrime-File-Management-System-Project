<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>

</head>
<body bgcolor="gold" font-color="red">
 
<form action="UserLoginCtl" method="post">
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<center><tr>
   <td align='center'><h1>User Login</h1></td>
</tr><center>
 
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>

<tr>
    <td align='center'>Email:</td>
    <td><input type='email' name='login' placeholder='Enter Email' required></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Password:</td>
    <td><input type='password' name='password' placeholder='Enter Password'><br>
     <a  href="ForgetPassword.jsp">Forgot Password</a>
     </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
 <tr >
    <td align='center'>  </td>
      <td ><button ><a href=Registration.jsp>Sign-up</a> </button>
      <input type='submit' name='operation' value="Sign-In">
      </td>
            <td ></td>
      
      
    </tr>
<tr >
   
    <tr> <td>&nbsp;</td> </tr>
    
    </tr>
   

</table>
</table>
 

</form>
<%
	String msg=(String)request.getAttribute("msg");
	if(msg!=null){
	out.print("<h1 style=color:white>"+msg+"</h1>");
	
	}


%>
</body>


</html> --%>






<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body >

<form action="UserLoginCtl" method="post" style="max-width:500px;margin:auto">
  <h2>  &#128110; Login Here &#128110;</h2>
  <div class="input-container">
  <small ><%	  String msg=(String)request.getAttribute("msg");
  if(msg!=null) { %>
	 			 <%=msg %>
  					<%}%>
  </small>
  </div>
  <div class="input-container">
   <!--  <i class="fa fa-envelope icon"></i> -->
    <input class="input-field" type="text" placeholder="Email" name="login" id="email">
  </div>
  <div class="input-container">
  <small id="email1"></small>
  </div>
  
  <div class="input-container">
    <input class="input-field" type="password" placeholder="Password" name="password" id="password">
  </div>
  
  
  
 <div class="input-container">
    <a href="Registration.jsp">Create Account</a>
    <a href="ForgetPassword.jsp">Forget Password</a>
 </div>
  <button type="submit" class="btn" name="operation" value="Sign-In">Sign In</button>

  

</form>

</body>
</html>