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
table{
	text-align:center;
}
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="UserProcessor?action=viewBooks">Home</a>
  <a href="UserProcessor?action=profile">Profile</a>
  <a style="float:right;" href="UserProcessor?action=signOut">SignOut</a>
  <a style="float:right" href="UserProcessor?action=viewOrders">Orders</a>
  <a style="float:right;" href="UserProcessor?action=viewCart&buyer=${buyerMail}">Cart</a>
</div>
<center>
<h3>Ordered Items </h3>
<table width=600>
<tr>
	<th>BookImage</th>
	<th>BookName</th>
	<th>BookPrice</th>
	<th>Quantity</th>
</tr>
	<c:forEach var="i" items="${ordItemList}">
		<tr>
			<td><img src="images/${i.getImage()}" style="width=30px;height:50px;"/></td>
			<td>${i.getBookName()}</td>
			<td>${i.getBookPrice()}</td>
			<td>${i.getQuantity()}</td>
			<c:set var="total" value="${total + i.getBookPrice()*i.getQuantity()}" />
		</tr>
	</c:forEach>
<tr>
	<td colspan="4"><hr></td>
</tr>
<tr>
	<td colspan="2">Total Amount</td>
	<td colspan="2">${total}</td>
</tr>
</table>
</body>
</html>