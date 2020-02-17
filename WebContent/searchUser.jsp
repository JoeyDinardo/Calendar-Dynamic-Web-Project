<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="StyleSheet" href="CalendarStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<script  src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function saveValue() {
	
	$.ajax({
		url: "calendarSave",
		type: 'POST',
		dataType: 'json',
		data: $('#dayform').serialize ()
});
	
}
</script>
</head>
<style>
h1{
font-family: "Aguafina Script";
color: black;

}
td{
color: white;
font-family: "Aguafina Script";
font-size: 25px;
}
button{
font-size: 30px;
}
</style>

<form  action="saveFriendServlet" method="post">
<table>
<tr>
<h1>UserName</h1>
</tr>
<% boolean [] friends=(boolean []) request.getAttribute("friends"); 
String [] names = (String []) request.getAttribute("names");
	for (int i =0; i < friends.length;i++){
		%>
		<tr>
		<td>
		
		<%= names[i]%>
		</td>
		<td>
		<%if ( friends[i] == true){
			%>	
			<button type="submit" name = "Delete" value = <%=names[i]%> >Delete </button>
			
			<%
		}
		else{%>	
			<button type="submit"  name = "Add"  value = <%=names[i]%>  >Add </button>
			<%
		}
		%>
		</td>
		</tr>
		<%
	}
%>
</table>
</form>
</html>
		
