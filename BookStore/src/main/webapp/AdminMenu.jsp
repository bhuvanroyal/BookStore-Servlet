<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
}
.inner{
	width:25%;
	height:50%;
	text-align: center;
	margin-top:10%;
}
.topnav {
  background-color: #333;
  overflow: hidden;
}
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
.topnav a:hover {
  background-color: #ddd;
  color: black;
}
.topnav a.active {
  background-color: #04AA6D;
  color: white;
}
button{
display: inline-block;
outline: none;
cursor: pointer;
font-size: 14px;
line-height: 1;
border-radius: 500px;
transition-property: background-color,border-color,color,box-shadow,filter;
transition-duration: .3s;
border: 1px solid transparent;
letter-spacing: 2px;
min-width: 160px;
text-transform: uppercase;
white-space: normal;
font-weight: 700;
text-align: center;
padding: 17px 48px;
color: #18141c;
background-color: #1adbdb;
height: 48px; 
}
button:hover{
	transform: scale(1.04);
	background-color: #4CAF50;
	} 
                 
                
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="AdminMenu.jsp">Home</a>
  <a href="AdminProcessor?action=viewProfile">Profile</a>
  <a style="float:right;" href="AdminProcessor?action=signout">SignOut</a>
</div>
<div class="container" style="width:100%; display:flex; border:1px solid; height:500px; " >
	<div class="inner"><br>
		<img src="images/adminBook.png"/>
		<button type="button" onclick="location.href='AdminProcessor?action=viewBooks'">Books</button></div>
	<div class="inner"><br>
		<img src="images/order.png"/>
		<button type="button" onclick="location.href='AdminProcessor?action=viewOrders'">Orders</button></div>
	<div class="inner"><br>
		<img src="images/user.png"/>
		<button type="button" onclick="location.href='AdminProcessor?action=viewUsers'">Users</button></div>
	<div class="inner"><br>
		<img src="images/admin.png"/>
		<button type="button" onclick="location.href='AdminProcessor?action=viewAdmins'">Admins</button></div>
</div>
</body>
</html>