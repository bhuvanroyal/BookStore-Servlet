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
.btn btn-primary{
	width:50%;

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
table{
border-collapse: separate;
border-spacing: 0 15px;
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
<div style="display:flex; border:1px; solid;width:100%;" >
<div style="width:60%; padding-right:30px;">
<center><h3>Active Orders</h3>
<table  style="width:80%;text-align:center;">
<tr class="header">
	<th>Order Id</th>
	<th>Total Amount</th>
	<th>Delivery Status</th>
	<th>view Items</th>
</tr>
<c:forEach var="i" items="${orderList}">
<c:choose>
	<c:when test="${i.getStatus()=='active'}">
<tr class="row">
	<td>${i.getOrdId()}</td>
	<td>${i.getAmount()}</td>
	<td>Arriving Soon</td>
	<td><button type="button" onclick="location.href='UserProcessor?action=viewOrderItems&ordId=${i.getOrdId()}'">view</button>
</tr>
	</c:when>
</c:choose>
</c:forEach>
<tr>
	<td colspan="6"><hr></td>
</tr>
</table>
</div>
<div style="width:40%">
<center><h3>Order History</h3></center>
<table style="width:100%;text-align:center;">
<tr class="header">
	<th>Order Id</th>
	<th>Delivery Status</th>
	<th>view Items</th>
</tr>
<c:forEach var="i" items="${orderList}">
<c:choose>
	<c:when test="${i.getStatus()=='delivered'}">
<tr class="row">
	<td>${i.getOrdId()}</td>
	<td>Delivered Success</td>
	<td><button class="btn btn-primary" type="button" onclick="location.href='UserProcessor?action=viewOrderItems&ordId=${i.getOrdId()}'">view</button>
</tr>
	</c:when>
</c:choose>
</c:forEach>
</table>
</div>
</div>
</body>
</html>