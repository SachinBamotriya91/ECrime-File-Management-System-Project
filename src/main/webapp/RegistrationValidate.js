 /**Registration Validation Method  */
 

/*function Registrationvalidate(){
	alert("hekll");
    var email=document.getElementById('email').value;
    var atpos=email.indexOf("@");
    var dotpos=email.lastIndexOf(".");
  //var user= document.getElementById("user");
  var fName=document.getElementById("firstname").value;
  var lName=document.getElementById("lastname").value;
  var password=document.getElementById("password").value;
  var confirmpassword=document.getElementById("confirmpassword").value;
  var mobilenumber= document.getElementById("mobilenumber").value;
  var dob=document.getElementById("dob").value;
  var date=new Date(dob);
  var year=date.getFullYear();
  
  //var dateregex=/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
 // var gender=document.getElementById("gender").value;
  var flag=true;
   var letters = /^[A-Za-z]+$/;
    if(user.value==null){
     document.getElementById("user1").innerHTML="one option must be select ";
     flag=false;
     
   } 
   
   document.getElementById("firstname1").style.display='none';
   document.getElementById("lastname1").style.display='none';
   document.getElementById("email1").style.display='none';
   document.getElementById("password1").style.display='none';
   document.getElementById("dob1").style.display='none';
   document.getElementById("mobilenumber1").style.display='none';   
  if(fName.match(letters)==null){
    //document.getElementById("firstname1").style.visibilty='visible';
	  document.getElementById("firstname1").style.display='block';
    document.getElementById("firstname1").innerHTML="First Name can  contain only Alphabet";
    flag=false;
    
    }
  if(lName.match(letters)==null){
	 document.getElementById("lastname1").style.display='block';
    document.getElementById("lastname1").innerHTML="Last Name can  contain only Alphabet";
    flag=false;
    //return false
    
    }
  if(atpos<1 || dotpos<atpos+2 || dotpos+2>email.length){
	  document.getElementById("email1").style.display='block';

	  document.getElementById('email1').innerHTML="Please Enter Valid Email";
    flag=false;
    }
  if(password.match(confirmpassword)==null || confirmpassword.match(password)==null || password.length<=2){
	  document.getElementById("password1").style.display='block';

	  document.getElementById("password1").innerHTML="Password not Matched";
    flag=false;
  } 
  if(date.match(dateregex)==null){
	  document.getElementById("dob1").innerHTML="Enter Valid Date";
  }
  
  var mobile=/^[6-9]\d{9}$/;
   if(mobilenumber.match(mobile)==null){
		  document.getElementById("mobilenumber1").style.display='block';
    document.getElementById("mobilenumber1").innerHTML="InValid Mobile Number";
    flag=false;
    } 
   if(dob>= new date('01/01/2000')) {
	   document.getElementById("dob1").style.display='block';
	    document.getElementById("dob1").innerHTML="Please Enter valid Date of Birth";
	    flag=false;
   }
   return flag;
}*/
 
 /**Update Validation Method  */
 function updateValidate(){
	var email=document.getElementById('email').value;
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");
//var user=	document.getElementById("user");
	var fName=document.getElementById("firstname").value;
	var lName=document.getElementById("lastname").value;
	var mobilenumber=	document.getElementById("mobilenumber").value;
	var flag=true;
	var letters = /^[A-Za-z]+$/;
	
	document.getElementById("firstname1").style.display='none';
	   document.getElementById("lastname1").style.display='none';
	   document.getElementById("email1").style.display='none';
	   document.getElementById("mobilenumber1").style.display='none';   

	/*   if(user.value==null){
	 document.getElementById("user1").innerHTML="one option must be select ";
	 flag=false;	} */
	
	if(fName.match(letters)==null){
		 document.getElementById("firstname1").style.display='block';
		document.getElementById("firstname1").innerHTML="First Name can  contain only Alphabet";
		flag=false;  }
	
	if(lName.match(letters)==null){
		document.getElementById("lastname1").style.display='block';
		document.getElementById("lastname1").innerHTML="Last Name can  contain only Alphabet";
		flag=false;
		}
	
	if(atpos<1 || dotpos<atpos+2 || dotpos+2>email.length){
		document.getElementById("email1").style.display='block';
		document.getElementById('email1').innerHTML="Please Enter Valid Email";
		flag=false;
		}
	
	if(mobilenumber.length!=10){
		document.getElementById("mobilenumber1").style.display='block';
		document.getElementById("mobilenumber1").innerHTML="Invalid Mobile Number";
		flag=false;
		return false;
	}  
	return flag;
} 

 
 