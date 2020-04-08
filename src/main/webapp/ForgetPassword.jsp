<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

</style>
<script type="text/javascript">

</script>
</head>
<body bgcolor="gold" font-color="red">
 
<form  action="UserForgetPasswordCtl" method="post">
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<center><tr>
   <td align='center'><h1>Forget Password</h1></td>
</tr>

 <% String otp1=(String)request.getAttribute("otp");
 if(otp1==null){%>
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>

<tr>
    <td align='center'>Enter Email:</td>
    <td><input type='text' name='login' id='form1' >
    <input type='submit' name='operation' value="Next"></td>
</tr>
<%} %>
<tr> <td>&nbsp;</td> </tr>

</form>


<% String otp=(String)request.getAttribute("otp"); 
if(otp!=null){ %>
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<tr>
<form  onsubmit='return validate();' action="UserForgetPasswordCtl" method='Post' >

<input type='text' name=login value=<%=request.getAttribute("login") %> hidden>
<input type='text' name='OTP' id='otp' value=<%=request.getAttribute("otp") %> hidden>

<tr >
    <td align='center'>Enter OTP:</td>
    <td><input type='text' name='otp' id='otp1' > </td>
    
     <td align="center">We Sent OTP to Your Email</td>
    <span id='otp2'></span>
    <br>
    <td align='center'><a href="UserForgetPasswordCtl?login=<%=request.getAttribute("login") %> &Resend=<%= "ResendOTP"%>">Resend OTP</a>
    <br>
    
  <td>  <input type='submit' name='operation' value="Submit"></td>

</form>
</tr>
<%} %>

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
<script type="text/javascript" src="js/validate.js"></script>
<meta name="check" id="check" content="ForgetPassword">

</head>
<body>
<%String msg=(String)request.getAttribute("msg"); %>
<%-- <%String msg=(String)request.getAttribute("msg");
if(msg!=null){
	out.print("<h1>"+msg+"</h1>");
}
%> --%>

<form   action="UserForgetPasswordCtl" method="post" style="max-width:500px;margin:auto" onsubmit="return compareOTP(<%=request.getAttribute("OTP")%>)">
<h2>Forget Password</h2>
  

  <span>Enter Your Email We will Send You OTP on Your Email</span>
  <div class="input-container"></div>
 <%if(request.getAttribute("OTP")!=null) {%>
  <div class="input-container">
  
    <input class="input-field" type="text" placeholder="Enter Email" name="login" id="email" value="<%=request.getAttribute("login")%>" readonly="readonly">
  </div>
  
   
   
  <div class="input-container"  id='work'>
   
    <input class="input-field" type="text" placeholder="Enter OTP" name="otp" id="OTP" minlength=4 maxlength=4  onkeyup="compareOTP(<%=request.getAttribute("OTP")%>)" >
  </div>
    <div  class="input-container" >
  <small id='OTP1'></small>
  </div>
    <button type="submit" class="btn" name="operation" value="Submit" id='submit'>Submit</button>
  <%} else{ %>
  <div class="input-container">
  
    <input class="input-field" type="text" placeholder="Enter Email" name="login" id="email" onkeyup="gmail()">
  </div>
<div class="input-container" >
  <small id="email1" ><% if(msg!=null){ %>
  <%=msg %>
  <% } %></small>
  </div>
  
  <button type="submit" class="btn" name="operation" value="GETOTP" id='submit'>GET OTP</button>
  <%} %>

  

</form>
</body>
</html>




