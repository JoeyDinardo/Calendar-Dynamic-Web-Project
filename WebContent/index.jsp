<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="StyleSheet" href="StyleSheet.css">
<link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<style>
h1{
 text-align: center; 
  font-weight: lighter; 
 font-family: 'Aguafina Script';font-size: 75px; 
 
}
.container{
 font-family: 'Aguafina Script';font-size: 25px;
 font-weight: lighter;
 
}
</style>
</head>
<body>
<h1>It's Your Calendar</h1>

<form action="loginServlet" method="post">
<fieldset  style="border:none">
  <div class="imgcontainer">
  		
<!--     <img src="user1.png" alt="Avatar" class="avatar"> -->
  </div>

  <div class="container">
    <label><b>Username</b></label>
    <input type="text" name="username" required="required">
<!-- placeholder="Enter Username"  -->
    <label><b>Password</b></label>
    <input type="password"  name="psw" required="required">
<!-- placeholder="Enter Password" -->
    <button type="submit">Login</button>
    <input type="checkbox" checked="checked"> Remember me
    <span class="psw"> <a href="signUp.jsp">Sign Up</a></span>
  </div>
</fieldset>
  
</form>

</body>
</html>