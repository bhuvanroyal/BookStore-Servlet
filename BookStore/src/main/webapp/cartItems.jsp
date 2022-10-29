<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="sweetalert2.min.css">
<style>
img {
  height: 100px;
  width: 70px;
}
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
button{
  background: linear-gradient(to bottom right, #EF4765, #FF9A5A);
  border: 0;
  border-radius: 12px;
  color: #FFFFFF;
  cursor: pointer;
  display: inline-block;
  font-family: -apple-system,system-ui,"Segoe UI",Roboto,Helvetica,Arial,sans-serif;
  font-size: 16px;
  font-weight: 500;
  line-height: 2.5;
  outline: transparent;
  padding: 0 1rem;
  text-align: center;
  text-decoration: none;
  transition: box-shadow .2s ease-in-out;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
}

button:not([disabled]):focus {
  box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem rgba(239, 71, 101, 0.5), .125rem .125rem 1rem rgba(255, 154, 90, 0.5);
}

button:not([disabled]):hover {
  box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem rgba(239, 71, 101, 0.5), .125rem .125rem 1rem rgba(255, 154, 90, 0.5);
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
<table width=600>
<tr>
	<th>Book</th>
	<th>Book Id</th>
	<th>Book Name</th>
	<th>Book Price</th>
	<th>Quantity</th>
	<th>Total</th>
</tr>
<c:forEach var="i" items="${clist}">
<tr>
	<td><img src="images/${i.getImage()}" /></td>
	<td>${i.getBookId()}</td>
	<td>${i.getBookName()}</td>
	<td>${i.getBookPrice()}</td>
	<td>${i.getQuantity()}</td>
	<td>${i.getBookPrice()*i.getQuantity()}</td>
	<td><button type="button" onclick="location.href='UserProcessor?action=removeCartItem&bId=${i.getBookId()}'">Remove</button>
	<c:set var="total" value="${total + i.getBookPrice()*i.getQuantity()}" />
</tr>
</c:forEach>
<tr>
	<td colspan="6"><hr></td>
</tr>
<tr>
	<td colspan="5">Total Amount</td>
	<td>${total}</td>
</tr>
<tr>
	<td colspan="4"></td>
	<td colspan="2"><button type="button" onclick="location.href='UserProcessor?action=checkOut&total=${total}'" style="width:100%">Check Out</button></td>
</tr>
</table>
<c:choose>
	<c:when test="${flag=='1'}">
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script type="text/javascript">
		Swal.fire('Your Cart is Empty')
		</script>
	</c:when>
	<c:when test="${flag=='2'}">
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script type="text/javascript">
		Swal.fire({
			  icon: 'success',
			  title: 'Your Order is Confirmed',
			  showConfirmButton: false,
			  timer: 1500
			})
		</script>
	</c:when>
	
</c:choose>
</body>

</html>