<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.virtusa.bstore.dto.Book" %>
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
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid #00B4CC;
  border-right: none;
  padding: 5px;
  height: 20px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
}

.searchTerm:focus{
  color: #00B4CC;
}

.searchButton {
  width: 150px;
  height: 36px;
  border: 1px solid #00B4CC;
  background: #00B4CC;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}

.wrap{
  width: 50%;
  margin-top:2%;
  margin-left:50%;
  transform: translate(-50%, -50%);
}
table{
width:100%;
text-align:center;
font-family: 'Open Sans', sans-serif;
}
img {
  height: 200px;
  width: 150px;
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
background: #5E5DF0;
  border-radius: 999px;
  box-shadow: #5E5DF0 0 10px 20px -10px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  font-family: Inter,Helvetica,"Apple Color Emoji","Segoe UI Emoji",NotoColorEmoji,"Noto Color Emoji","Segoe UI Symbol","Android Emoji",EmojiSymbols,-apple-system,system-ui,"Segoe UI",Roboto,"Helvetica Neue","Noto Sans",sans-serif;
  font-size: 16px;
  font-weight: 700;
  line-height: 24px;
  opacity: 1;
  outline: 0 solid transparent;
  padding: 8px 18px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: fit-content;
  word-break: break-word;
  border: 0;
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
<form action="UserProcessor">
<div class="wrap">
  <div class="search">
    <input type="text" class="searchTerm" placeholder="Search by Category" name="bCategory" />
    <button type="submit" class="searchButton" name="action" value="searchCategory">
      Search
    </button>
  </div>
</div>
</form>
<c:choose>
    <c:when test="${blist!='null'}">
        <c:forEach var="i" items="${blist}">
			<div style="width:49.8%; float:left; border:1px solid;" >
				<table>
				<tr>
					<td><img src="images/${i.getImage()}" /></td>
					<td><label style="font-family:serif;font-size: 20px; font-weight:500;">
						${i.getBookName()}<br>
						Category: ${i.getBookCategory()}<br>
						Author: ${i.getBookAuthor()}<br>
						Price: <span style="font-size:35px; font-weignt:bold;">${i.getBookPrice()}</span>RS</label></td>
				</tr>
				<tr><td></td>
					<td><button type="button" style="background-color:#6ee04f" onclick="location.href='UserProcessor?action=addCart&bookId=${i.getBookId()}&bookName=${i.getBookName()}&bookPrice=${i.getBookPrice()}&buyerMail=${buyerMail}&image=${i.getImage()}'">AddCart</button>
					&emsp;&emsp;&emsp;<button type="button" onclick="location.href='UserProcessor?action=buyNow&bookId=${i.getBookId()}&bookName=${i.getBookName()}&bookPrice=${i.getBookPrice()}&buyerMail=${buyerMail}&image=${i.getImage()}'">BuyNow</button></td>
				</tr>
				</table>
			</div>
		</c:forEach>
    </c:when>  
   </c:choose>
  <c:choose>
  	<c:when test="${categoryList != 'null'}">
		<c:forEach var="i" items="${categoryList}">
			<div style="width:49.8%; float:left; border:1px solid;" >
				<table>
			<tr>
				<td><img src="images/${i.getImage()}" /></td>
				<td><label style="font-family:serif;font-size: 20px; font-weight:500;">
					${i.getBookName()}<br>
					Category: ${i.getBookCategory()}<br>
					Author: ${i.getBookAuthor()}<br>
					Price: <span style="font-size:35px; font-weignt:bold;">${i.getBookPrice()}</span>RS</label></td>
			</tr>
			<tr><td></td>
				<td><button type="button" style="background-color:#6ee04f" onclick="location.href='UserProcessor?action=addCart&bookId=${i.getBookId()}&bookName=${i.getBookName()}&bookPrice=${i.getBookPrice()}&buyerMail=${buyerMail}&image=${i.getImage()}'">AddCart</button>
				&emsp;&emsp;&emsp;<button type="button" onclick="location.href='UserProcessor?action=buyNow&bookId=${i.getBookId()}&bookName=${i.getBookName()}&bookPrice=${i.getBookPrice()}&buyerMail=${buyerMail}&image=${i.getImage()}'">BuyNow</button></td>
			</tr>
		</table>
		</div>
	</c:forEach>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${flag=='1'}">
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script type="text/javascript">
		Swal.fire("Added Item");
		</script>
	</c:when>
</c:choose>
</body>
</html>