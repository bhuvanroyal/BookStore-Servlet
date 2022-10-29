<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
background-image: url("images/background.jpg");
}
button{
	width:100px;
	height:25px;
	border:1px;
	border-radius:6px;
	background-color:skyblue;
}
button:active 
    {background-color: #61ede8}
.table-wrapper{
	justyfy-content:center;
	width:30%;
	border-radius: 25px;
    margin: 100px 450px 40px;
    box-shadow: 0px 35px 50px rgba( 0, 0, 0, 0.2 );
}
.fl-table {
    border-radius: 25px;
    font-size: 15px;
    font-weight: normal;
    border: none;
    border-collapse: collapse;
    width: 100%;
    max-width: 100%;
    white-space: nowrap;
    background-color: white;
}

.fl-table td, .fl-table th {
    text-align: center;
    padding: 8px;
}

.fl-table td {
    border-right: 1px solid #f8f8f8;
    font-size: 15px;
}

.fl-table thead th {
    color: #ffffff;
    background: #4FC3A1;
}


.fl-table thead th:nth-child(odd) {
    color: #ffffff;
    background: #324960;
}

.fl-table tr:nth-child(even) {
    background: #F8F8F8;
}

/* Responsive */

@media (max-width: 767px) {
    .fl-table {
        display: block;
        width: 100%;
    }
    .table-wrapper:before{
        content: "Scroll horizontally >";
        display: block;
        text-align: right;
        font-size: 15px;
        color: white;
        padding: 0 0 10px;
    }
    .fl-table thead, .fl-table tbody, .fl-table thead th {
        display: block;
    }
    .fl-table thead th:last-child{
        border-bottom: none;
    }
    .fl-table thead {
        float: left;
    }
    .fl-table tbody {
        width: auto;
        position: relative;
        overflow-x: auto;
    }
    .fl-table td, .fl-table th {
        padding: 20px .625em .625em .625em;
        height: 60px;
        vertical-align: middle;
        box-sizing: border-box;
        overflow-x: hidden;
        overflow-y: auto;
        width: 120px;
        font-size: 15px;
        text-overflow: ellipsis;
    }
    .fl-table thead th {
        text-align: left;
        border-bottom: 1px solid #f7f7f9;
    }
    .fl-table tbody tr {
        display: table-cell;
    }
    .fl-table tbody tr:nth-child(odd) {
        background: none;
    }
    .fl-table tr:nth-child(even) {
        background: transparent;
    }
    .fl-table tr td:nth-child(odd) {
        background: #F8F8F8;
        border-right: 1px solid #E6E4E4;
    }
    .fl-table tr td:nth-child(even) {
        border-right: 1px solid #E6E4E4;
    }
    .fl-table tbody td {
        display: block;
        text-align: center;
    }
}
</style>
</head>
<script>
function validate(){
	var email=document.getElementById("email").value;
	var location=document.myform.ulocation.value;
	var mno=document.myform.uMobileNumber.value;
	var age=document.myform.uAge.value;
	 if( !/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/.test(email)){
			alert("Enter the valid email");
			return false;
		}
	 if(!/[a-zA-Z]+/.test(location)){
			alert("Enter the valid Location");
			return false;
		}
	 if(!/^[1-9]{1}[0-9]{1}$/.test(age)){
			alert("Enter the valid Age");
			return false;
		}
	 if(!/^[7-9]{1}[0-9]{9}$/.test(mno)){
			alert("Enter the valid mobile number");
			return false;
		}
	 return true;
}
</script>
<body>
<div  class="table-wrapper">
<form action="UserProcessor" onsubmit="return validate()" name="myform">
<table class="fl-table">
<tr>
	<td>User Id</td>
	<td><input type="number" name="uId" required/></td>
</tr>
<tr>
	<td>Email</td>
	<td><input type="text" name="uEmail"  id="email" /></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="uPwd" /></td>
</tr>
<tr>
	<td>location</td>
	<td><input type="text" name="ulocation" /></td>
</tr>
<tr>
	<td>age</td>
	<td><input type="number" name="uAge" /></td>
</tr>
<tr>
	<td>Mobile Number</td>
	<td><input type="number" name="uMobileNumber" /></td>
</tr>
<tr>
	<td>Date Of Birth</td>
	<td><input type="date" name="dob" required/></td>
</tr>
<tr>
	<td colspan="2"><button type="submit" name="action" value="signup">SignUp</button>&emsp;&emsp;<a href="UserLogin.jsp" >Go back</a></td>
</tr>
</table>
</form>
</div>
</body>
</html>