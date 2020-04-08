<%@page import="com.model.UserModel"%>
<%@page import="com.bean.UserBean"%>
<%@ page import="com.ctl.DoFilter" %>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
<% UserBean bean=(UserBean)session.getAttribute("UserBean");
String genotp=new UserModel().generateOTP(bean.getLogin());
%>
function validate(){
	var confirmpass=document.getElementById("confirmPass").value;
	var newpass=document.getElementById("newPass").value;
	var otp=document.getElementById("otp").value;

	
	var flag=true;
	if(confirmpass.match(newpass)==null){
		document.getElementById("confirmPass1").innerHTML="password not matched";
		flag=false;
	}
	if(otp.match(<%= genotp %>)==null){
		document.getElementById("otp1").innerHTML="Enter Valid OTP";
		flag=false;
		
	}
	
	return flag;
}
function validateOTP(){
	var flag=true;
	var otp=document.getElementById("otp").value;
	if(otp.match(<%= genotp %>)==null){
		document.getElementById("otp1").innerHTML="Enter Valid OTP";
		flag=false;
		
	}
	return false;
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
<title>Insert title here</title>
</head>
<body bgcolor="gold" font-color="red">
 
<form onsubmit='return validate()' action="UserResetPasswordCtl" method="post">
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<center><tr>
   <td align='center'><h1>Reset Password</h1></td>
</tr><center>
 
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>

<tr>
    <td align='center' >Email:</td>
    <td><input readonly type='text' name='login' value=<%=bean.getLogin()%>></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center' >Enter OTP:</td>
    <td><input  type='text' name='otp' id='otp'><br>
    <span id='otp1'></span>
    
    </td>
</tr>
<tr> <td>&nbsp;</td> </tr>

    <td align='center'>old Password:</td>
    <td><input type='password' name='oldPassword' value=<%=bean.getPassword() %>></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>New Password:</td>
    <td><input type='text' name='newPassword' id='newPass'></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Confirm Password:</td>
    <td><input type='text' name='confirmPassword' id='confirmPass'></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'></td>
    <td><span id='confirmPass1'></span> </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='right'><input type='submit' name='operation' value="Submit"> </td>
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
	 --%>
	
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Add icon library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript" src="js/validate.js"></script>
	<% UserBean bean=(UserBean)session.getAttribute("UserBean");
	HttpSession session1=request.getSession(false);
	//System.out.print(bean.getFirstName());
	/* String session1=(String)request.getAttribute("Id"); */
	if(session1.getAttribute("UserBean")!=null){
		//System.out.print(bean);
	String genotp=new UserModel().generateOTP(bean.getLogin());
	//System.out.println("here is otp  "+genotp);
	%>
	<%-- var genotp1="<%=genotp %>"; --%>

	</head>
	<body>

	<form onsubmit="return compareOTP(<%=genotp %>)"  action="UserResetPasswordCtl" method="post" style="max-width:500px;margin:auto">
	  <h2>Reset Password</h2>
	   <span>Enter  OTP We  are sent  You OTP on Your Email</span>
  <div class="input-container"></div>
	  <div class="input-container"><input class="input-field" type="text" readonly placeholder="Enter Email" name="login" value="<%=bean.getLogin() %>" id="email">
	  </div>
	  
	
	<div class="input-container">
   <input class="input-field" readonly type="password" placeholder="Old Password" name="oldPassword" value=<%=bean.getPassword() %> >
	  </div>

	<div class="input-container">
	    <input class="input-field" type="password" placeholder=" Enter New Password" name="password"id="password" maxlength="15" onkeyup="validatePassword()">
	  </div> 

	  <div class="input-container">
	    <small id="password1"></small>
	  </div>   

	<div class="input-container">
	    <input class="input-field"  type="password" placeholder="Confirm Password" name="confirmPassword"  id="confirmpassword" maxlength="15" onkeyup="confirmPass()" >
	  </div>
	  <div class="input-container">
	    <small id="confirmpassword1"></small>
	  </div>   

<div class="input-container">
	    <input class="input-field" type="text" placeholder="Enter OTP" name="OTP" id="OTP" onkeyup="compareOTP(<%=genotp %>)" >
	  </div>

	<div class="input-container"  >
	    <small id="OTP1"></small>
	  </div>


	  <button type="submit" class="btn" name="operation" value="Submit">Submit</button>

	  

	</form>
	<%}
	else{
		request.setAttribute("msg", "Please Login First");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		}%>
	</body>
	</html>