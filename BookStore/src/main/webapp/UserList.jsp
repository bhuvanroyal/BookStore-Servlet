<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
table{
text-align:center;
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
.row{
height:35px;
background-color: #ffffff;
box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
}
.header{
height:35px;
background-color: #95A5A6;
font-size: 14px;
text-transform: uppercase;
letter-spacing: 0.03em;
}
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="AdminMenu.jsp">Home</a>
  <a href="AdminProcessor?action=viewProfile">Profile</a>
  <a style="float:right;" href="AdminProcessor?action=signout">SignOut</a>
</div>
<center>
<h1>UserList</h1>
<table width=800 >
<tr class="header">
	<th>User Id</th>
	<th>Email</th>
	<th>password</th>
	<th>location</th>
	<th>Age</th>
	<th>Mobile Number</th>
	<th>Date Of Birth</th>
</tr>
<c:forEach var="i" items="${ulist}">
<tr class="row">
	<td>${i.getUserId()}</td>
	<td>${i.getUserEmail()}</td>
	<td>${i.getPassWord()}</td>
	<td>${i.getLocation()}</td>
	<td>${i.getAge()}</td>
	<td>${i.getUserMobileNo()}</td>
	<td>${i.getDob()}</td>
	<td><button type="button" onclick="location.href='AdminProcessor?action=removeUser&uId=${i.getUserId()}'">Remove</button>
</tr>
</c:forEach>
</table>
</body>
</html>