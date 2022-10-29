<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" />
<style>
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
body {
    background: rgb(99, 39, 120)
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
  <a class="active" href="UserProcessor?action=viewBooks">Home</a>
  <a href="UserProcessor?action=profile">Profile</a>
  <a style="float:right;" href="UserProcessor?action=signOut">SignOut</a>
  <a style="float:right" href="UserProcessor?action=viewOrders">Orders</a>
  <a style="float:right;" href="UserProcessor?action=viewCart&buyer=${buyerMail}">Cart</a>
</div>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3" style="margin-left:40%;">
                    <h4 class="text-right">Profile Details</h4>
                </div>
                <form action="UserProcessor">
                <div class="row mt-3" style="width:50%; margin-left:25%;">
					<div class="col-md-12"><label class="labels">User ID</label><input type="text" class="form-control" disabled="disabled" value="${user.getUserId()}"></div>
                    <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control" disabled="disabled" value="${user.getUserEmail()}" name="email"></div>
                    <div class="col-md-12"><label class="labels">Password</label><input type="text" class="form-control" value="${user.getPassWord()}" name="password"></div>
                    <div class="col-md-12"><label class="labels">Location</label><input type="text" class="form-control" value="${user.getLocation()}" name="location"></div>
                    <div class="col-md-12"><label class="labels">Age</label><input type="text" class="form-control" value="${user.getAge()}" name="age"></div>
                    <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control"  value="${user.getUserMobileNo()}" name="mobileNumber"></div>
                    <div class="col-md-12"><label class="labels">Date Of Birth</label><input type="text" class="form-control" disabled="disabled" value="${user.getDob()}" ></div>
                    <div class="col-md-12"><label class="labels">Select new Birth Date</label><input type="date" class="form-control" value="2013-01-08"  name="dob"></div>
                    
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" name="action" value="modifyUser">Save</button></div>
                </form>
            </div>
      </div>
</div>
</body>
</html>