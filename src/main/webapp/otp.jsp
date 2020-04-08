<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function validate(){
/* 	var confirmpass=document.getElementById("confirmPass").value;
	var newpass=document.getElementById("newPass").value; */
	var otp=document.getElementById("otp").value;
	var confirmotp=document.getElementById("otp1").value;
	var flag=true;
	/* if(confirmpass.match(newpass)==null){
		document.getElementById("confirmPass1").innerHTML="password not matched";
		flag=false;
	} */
	if(otp.match(confirmotp)==null){
		document.getElementById("otp2").style.visibility="visible";
		document.getElementById("otp2").innerHTML="Please Enter Valid OTP";
		flag=false;
	}
	
	return flag;
	
}
</script>
 </head>
<body bgcolor="gold" font-color ="red">
 
<form onsubmit='return validate()' action="ForgetPasswordCtl" method="post">
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<center><tr>
   <td align='center'><h1>OTP Page</h1></td>
</tr><center>
 <table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<tr>
    
    <td><input readonly type='text' name='login' value=<%=request.getAttribute("login")%> hidden>
</tr>

 <tr>
    <td><input readonly type='text' name='login' value=<%=request.getAttribute("otp")%> id="otp" hidden >  </td>
    
</tr>
<tr> <td>&nbsp;</td> </tr>
    <td align='center'>Enter OTP:</td>
    <td><input type='text' name='OTP' value="" id="otp1" required>
     </td>
     <td align="center">We Sent OTP to Your Email</td>
</tr>
<tr> <td>&nbsp;</td> </tr> 
<tr>
<td align="center"> </td> 
<td><a href="ForgetPasswordCtl?login=<%=request.getAttribute("login") %>">Resend OTP</a>

</td>
</tr>

<tr>
    <td align='center'></td>
    <td><span id='otp2' style='visibility: hidden'></span> </td>
</tr>
<tr> <td>&nbsp;</td> </tr>

 <tr>
    <td><input readonly type='text' name='otppage' value='otppage' hidden>  </td>
    
</tr>
<tr> <td>&nbsp;</td> </tr> 
<!-- <tr>
    <td align='center'>New Password:</td>
    <td><input type='text' name='newPassword' id='newPass' required></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Confirm Password:</td>
    <td><input type='text' name='confirmPassword' id='confirmPass' required></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'></td>
    <td><span id='confirmPass1'></span> </td>
</tr>
<tr> <td>&nbsp;</td> </tr> -->
<tr>
    <td align='right'><input type='submit' name='operation' value="Submit"> </td>
</tr>


</table>
</table>
 

</form>

</body>
</html>