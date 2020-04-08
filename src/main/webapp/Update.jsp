<%@page import="com.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
<link rel="stylesheet" type="text/css" href="style.css">


<script type="text/javascript" src="js/validate.js">
<% 

UserBean bean=(UserBean)session.getAttribute("UserBean");
session=request.getSession(false);
String id=(String)session.getAttribute("Id");
System.out.print("session :"+session);
if(session.getId().equals(id)){
%>
</script>
</head>
 
 <body>

<form onsubmit="return lastCheck();" action="UserLoginCtl" method="post" style="max-width:500px;margin:auto">
  <h2>Update  Data</h2>
  <div class="input-container">
    <input class="input-field" type="text" placeholder="First-Name" name="firstname" id="firstname" value=<%=bean.getFirstName() %> onkeyup="fName()">
  </div>
  <div class="input-container">
  <small id="firstname1"></small>
  </div>
  
   <div class="input-container">
    <input class="input-field" type="text" placeholder="Last Name" name="lastname" id="lastname" value=<%=bean.getLastName() %> onkeyup="lName()">
  </div>

 <div class="input-container">
  <small id="lastname1"></small>
  </div>

  
  <div class="input-container">
   <!--  <i class="fa fa-envelope icon"></i> -->
    <input class="input-field" type="text" placeholder="Email" name="login" id="email" value=<%=bean.getLogin() %> onkeyup="gmail()" readonly>
  </div>
  <div class="input-container">
  <small id="email1"></small>
  </div>
  
  <div class="input-container">
    <input class="input-field" type="Date" placeholder="Date of Birth" name="dob" id="dob" value=<%=bean.getDob() %>>
  </div>
  <div class="input-container">
  <small id="dob1"></small>
  </div>
  
  <div class="input-container">
    <input class="input-field" type="text" placeholder="Mobile Number" name="mobilenumber" id='mobileno' minlength=10 maxlength=10 value=<%=bean.getMobileNo() %> onkeyup="phoneNo()" >
  </div>
  <div class="input-container">
  <small id="mobileno1"></small>
  </div>
  
  <div class="input-container" style="color: white">
    <i> Gender :</i>
    <select name = "gender">
            <option value = "Male" selected id='gender'>Male</option>            
            <option value = "Female" id=gender>Female</option>
         </select>
  </div>
  <div class="input-container">
  <small id="gender1"> </small>
  </div>
  
  
  

  <button type="submit" class="btn"  name="operation" value='Update'>Save</button>.
  
</form>
<%}
else{
	request.setAttribute("msg","session has been expired");
	request.getRequestDispatcher("Login.jsp").forward(request, response);
}
%>
</body>

</html>


















<%-- <body  bgcolor="gold" font-color="red">
<form onsubmit="return validate()" action="UserLoginCtl" method="post">
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<center><tr>
   <td><h1>Update</h1></td>
</tr><center>
 
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
<tr>
    <td align='center'>Update Info:</td>
    <td><input type='radio' name='user' value='civilian'   id='user' >Civilian 
    		<input type='radio' name='user' value='inspector'  id='user'>Inspector 
    		<input type='radio' name='user' value='commissioner'  id='user'>Commissioner
    		<br>
    		<span id="user1"></span> </td>
        
    
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>

<tr>
    <td align='center'>First Name:</td>
    <td><input type='text' name='firstname' id='firstname' value=<%=bean.getFirstName() %>>
    <br>
    <span id='firstname1' ></span> </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Last Name:</td>
    <td><input type='text' name='lastname'  id='lastname' value=<%=bean.getLastName() %>>
    			  <br>
    			 <span id='lastname1' ></span></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Email:</td>
    <td><input type='email' name='login' id='email' value=<%=bean.getLogin() %> >
    <br>
    <span id="email1"></span>
    </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>

<tr>
    <td align='center'>Date Of Birth:</td>
    <td><input type='date' name='dob' id="dob" value=<%=bean.getDob() %>></td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Mobile Number:</td>
    <td><input type='text' name='mobilenumber' id='mobilenumber' min="10" max="11" required value=<%=bean.getMobileNo() %>>
    <br>
    		 
    		 
    </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<tr>
    <td align='center'>Gender :</td>
    <td  id='gender ' value=<%=bean.getGender() %>><input type='radio' name='gender' value='Male' >Male 
    		<input type='radio' name='gender' value='Female' >Female
    		<br>
    		 <span id='gender1' ></span> 
    </td>
</tr>
<tr> <td>&nbsp;</td> </tr>
<table border='0' cellpadding='0' cellspacing='0' width='480px' align='center'>
<tr>
    <td align='center'><button name="operation" value='Update'>Submit </button>
      </td>
</tr>
</table>
</table>
</table>
</form>
</body>  --%>