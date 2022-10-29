<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.virtusa.bstore.dto.*"%>
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
img{
	width:50px;
	height:60px;
}
.inner{
	border:1px solid;
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
.header{
height:35px;
background-color: #708090;
font-size: 14px;
text-transform: uppercase;
letter-spacing: 0.03em;
}
.row{
height:35px;
background-color: #ffffff;
box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
}
table{
text-align:center;
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
	<div class=inner>
	<table >
			<tr>
				<th>Book</th>
				<th>BookId</th>
				<th>Name</th>
				<th>Category</th>
				<th>Quantity</th>
			</tr>
		<c:forEach var="i" items="${blist}">
		<tr>
			<td><img src="images/${i.getImage()}"/></td>
			<td >${i.getBookId()}</td>
			<td >${i.getBookName()}</td>
			<td >${i.getBookCategory()}</td>
			<td >${i.getQuantity()}</td>
			<td><button type="button" onclick="location.href='AdminProcessor?action=removeBook&bId=${i.getBookId()}'">Remove</button>
			<td><button type="button" onclick="location.href='AdminProcessor?action=updateClicked&bId=${i.getBookId()}'">Modify</button></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<div class=inner>
	<center>
	<label>You wanna Add the New Book...?</label><span><button type="button" onclick="location.href='AdminProcessor?action=addClicked'">Click</button></span>
	<c:choose>
    <c:when test="${flag=='1'}">  
		<form action="AdminProcessor">
			<table>
				<tr>
					<td class="header">Book id:</td>
					<td class="row"><input type="number" name="bId" style="width:90%; " required/></td>
				</tr>
				<tr>
					<td class="header">Book Name:</td>
					<td class="row"><input type="text" name="bName" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td class="header">Book Author:</td>
					<td class="row"><input type="text" name="bAuthor" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td class="header">Book Category:</td>
					<td class="row"><input type="text" name="bCategory" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td class="header">Quantity:</td>
					<td class="row"><input type="number" name="quantity" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td class="header">Book Price</td>
					<td class="row"><input type="number" name="bPrice" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td class="header">Add the book</td>
					<td class="row"><input type="file" name="image" style="width:90%;" required/></td>
				</tr>
				<tr>
					<td colspan="2" class="row"><button type="submit" name="action" value="addBook">Add</button>
			</table>
		</form>
		</c:when>
	</c:choose>
	<c:choose>
	<c:when test="${book!=null}">  
		<form action="AdminProcessor">
			<table>
				<tr>
					<td class="header">Book id:</td>
					<td class="row"><input type="number" name="bId" value="${book.getBookId()}"/></td>
				</tr>
				<tr>
					<td class="header">Book Name:</td>
					<td class="row"><input type="text" name="bName" value="${book.getBookName()}" /></td>
				</tr>
				<tr>
					<td class="header">Book Author:</td>
					<td class="row"><input type="text" name="bAuthor" value="${book.getBookAuthor()}"/></td>
				</tr>
				<tr>
					<td class="header">Book Category:</td>
					<td class="row"><input type="text" name="bCategory" value="${book.getBookCategory()}"/></td>
				</tr>
				<tr>
					<td class="header">Quantity:</td>
					<td class="row"><input type="number" name="quantity" value="${book.getQuantity()}"/></td>
				</tr>
				<tr>
					<td class="header">Book Price</td>
					<td class="row"><input type="number" name="bPrice" value="${book.getBookPrice()}"/></td>
				</tr>
				<tr>
					<td  colspan="2"><button type="submit" name="action" value="update">Update</button>
				</td>
				</tr>
			</table>
		</form>
		</c:when>
		</c:choose>
	</div>
</div>


</body>
</html>