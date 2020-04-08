<!DOCTYPE html>
<%@page import="com.model.UserModel"%>
<%@page import="com.bean.UserBean"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
     <!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="js/validate.js"></script>
<meta name="check" id="check" content="registration">

<script type="text/javascript">
function preload(){
	var a=String(null);
		if(document.getElementById("firstname").value==a && document.getElementById("lastname").value==a 
				&& document.getElementById("mobileno").value==a){
		document.getElementById("firstname").value="";
		document.getElementById("lastname").value="";	
		document.getElementById("mobileno").value="";
	}		
}
</script>


</head>
<body onload="preload()" >

<form onsubmit="return lastCheck()" action="UserRegistrationCtl" method="post" style="max-width:500px;margin:auto">
  <h2>Register Form For User</h2>
  
  <div class="input-container">
    <input class="input-field" type="text" placeholder="First Name" name="firstName" id="firstname" onkeyup="fName()" value=<%=request.getAttribute("firstName")%>>
  </div>
 <div class="input-container">
  <small id="firstname1"></small>
  </div>
  
   <div class="input-container">
  <input class="input-field" type="text" placeholder="Last Name" name="lastName" id="lastname" onkeyup="lName()" value=<%=request.getAttribute("lastName")%>>
  </div>

 <div class="input-container">
  <small id="lastname1"></small>
 </div>

  
  <div class="input-container">
    <input class="input-field" type="email" placeholder="Email" name="login" id="email" onkeyup="gmail()">
  
  </div>
    <div class="input-container">
  <small ><%	  String msg=(String)request.getAttribute("msg");
  if(msg!=null) { %>
	 			 <%=msg %>
  					<%}%>
  </small>
  </div>
  
  <div class="input-container">
  <small id="email1"></small>
  </div>

  
  <div class="input-container">
    <input class="input-field" type="password" placeholder="Password" name="password" id="password" maxlength="15" onkeyup="validatePassword()" >
  </div>
   <div class="input-container">
  <small id="password1"></small>
  </div>
 
  
   <div class="input-container">
    <input class="input-field" type="password" placeholder="Confirm Password" name="confirmpassword" id="confirmpassword" maxlength="15" onkeyup="confirmPass()">
  </div>
   <div class="input-container">
  <small id="confirmpassword1"></small>
  </div>
  
  <div class="input-container">
    <input class="input-field" type="Date" placeholder="dd/mm/yyyy" name="dob" id="dob" value=<%=request.getAttribute("dob") %>>
  </div>

  <div class="input-container">
  <small id="dob1"></small>
  </div>
  
  <div class="input-container">
    <input class="input-field" type="text" placeholder="Mobile Number" name="mobileNo" id="mobileno" maxlength="10" onkeyup="phoneNo()" value=<%=request.getAttribute("mobileno") %>>
  </div>

  <div class="input-container">
  <small id="mobileno1"></small>
  </div>
  
  <div class="input-container" style="color: white">
    <i> Gender :</i>
    <select name = "gender">
            <option  value = "Male"  id='gender'>Male</option>            
            <option value = "Female" id=gender>Female</option>
         </select>
  </div>
 
  <div class="input-container">
  <small id="gender1"> </small>
  </div>

  <button type="submit" class="btn" name="operation" value="register" id='register'>Register</button>
</form>

</body>
</html>
