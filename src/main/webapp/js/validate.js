 var letters = /^[A-Za-z]+$/;
 alert("hhhhhhhhhhhhhhhhh");
function fName(){
	
	var a=document.getElementById("check").content;
	 if(document.getElementById("firstname").value.match(letters)==null){
		document.getElementById("firstname1").innerHTML="Invalid First Name";
	}
	 else{
		 document.getElementById("firstname1").innerHTML="";
	 }	 
}	

function lName(){
	if(document.getElementById("lastname").value.match(letters)==null){
		document.getElementById("lastname1").innerHTML="Invalid Last Name";
	}
	else{
		document.getElementById("lastname1").innerHTML="";
	}
}
function gmail(){
	
	var gmail=/(\W|^)[\w.+\-]*@gmail\.com(\W|$)/;
if(document.getElementById("email").value.match(gmail)==null) {
	//alert("if");
	document.getElementById("email1").innerHTML="Please Enter Valid Gmail";
}
else{
	document.getElementById("email1").innerHTML="";
}
}
function confirmPass(){
	
	var pass=document.getElementById("password").value;
	var confirmpass=document.getElementById("confirmpassword").value;
	if(pass.match(confirmpass)==null &&confirmpass.match(pass)==null){
		document.getElementById("confirmpassword1").innerHTML="Password must be same";
}
	else{
		document.getElementById("confirmpassword1").innerHTML="";
	}
}
function validatePassword(){
	var regex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,15}$/;
	if(document.getElementById("password").value.match(regex)==null){
		document.getElementById("password1").innerHTML="Password Must Contain Capital Letter ,Number ,@ and lenght must be 8-15 character";
	}
	else{
		document.getElementById("password1").innerHTML="";
	}
}
function phoneNo(){
	var regex=/^[6-9]\d{9}$/;
	if(document.getElementById("mobileno").value.match(regex)==null){
		document.getElementById("mobileno1").innerHTML="Invalid Mobile Number";
	}
	else{
		document.getElementById("mobileno1").innerHTML="";
	}
	
}
function compareOTP(otp){
	
	var page=document.getElementById("check").content;
	
	if(document.getElementById("OTP").value.match(otp)==null ){
		document.getElementById("OTP1").innerHTML="Please Enter Valid OTP";
		return false;
	}
	else{
		document.getElementById("OTP1").innerHTML="";
		if(page=="ResetPassword"){
		if(document.getElementById("password").value.match(document.getElementById("confirmpassword").value)!=null 
				&& document.getElementById("confirmpassword").value.match(document.getElementById("password").value)!=null 
				&& document.getElementById("password").value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,15}$/)!=null ) {
			return true;
		}
		else{
			
			return false;
		}
		}
		else if(page=="ForgetPassword"){
			return true;
		}
		
	}
}

function preload(){
	alert("hhha");
	var a=String(null);
		if(document.getElementById("firstname").value==a && document.getElementById("lastname").value==a 
				&& document.getElementById("mobileno").value==a){
		document.getElementById("firstname").value="";
		document.getElementById("lastname").value="";	
		document.getElementById("mobileno").value="";
	}		
}
function lastCheck(){
	
	if(document.getElementById("firstname").value.match(letters)!=null 
			&& document.getElementById("lastname").value.match(letters)!=null 
			&& document.getElementById("email").value.match(/(\W|^)[\w.+\-]*@gmail\.com(\W|$)/)!=null
			&& document.getElementById("password").value.match(document.getElementById("confirmpassword").value)!=null 
			&& document.getElementById("confirmpassword").value.match(document.getElementById("password").value)!=null 
			&& document.getElementById("password").value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,15}$/)!=null
			&& document.getElementById("mobileno").value.match(regex) !=null){
		return true;
	}
	
	else{
		return false;
	}
}
