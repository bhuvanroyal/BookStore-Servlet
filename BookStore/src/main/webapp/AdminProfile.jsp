<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" />
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
.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background: rgb(99, 39, 120);
    box-shadow: none;
    border: none
}

.profile-button:hover {
    background: #682773
}

.profile-button:focus {
    background: #682773;
    box-shadow: none
}

.profile-button:active {
    background: #682773;
    box-shadow: none
}

.back:hover {
    color: #682773;
    cursor: pointer
}

.labels {
    font-size: 11px
}

.add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
}
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="AdminMenu.jsp">Home</a>
  <a href="AdminProcessor?action=viewProfile">Profile</a>
  <a style="float:right;" href="AdminProcessor?action=signout">SignOut</a>
</div>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3" style="margin-left:40%;">
                    <h4 class="text-right">Profile Details</h4>
                </div>
                <form action="AdminProcessor">
                <div class="row mt-3" style="width:50%; margin-left:25%;">
					<div class="col-md-12"><label class="labels">User Name</label><input type="text" class="form-control" value="${profile.getAdminName()}" name="adname"></div>
                    <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control" disabled="disabled" value="${profile.getAdminEmail()}" name="email"></div>
                    <div class="col-md-12"><label class="labels">Password</label><input type="text" class="form-control" value="${profile.getAdminPassword()}" name="password"></div>
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" name="action" value="modifyProfile">Save</button></div>
                </form>
            </div>
      </div>
</div>
</body>
</html>