<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="StyleSheet" href="CalendarStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<script  src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function getValue() {	
	 var result = jQuery.ajax({
		url: "documentGet",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#form1').serialize ()
	
});
	var message = jQuery.parseJSON(result.responseText);
	$( "#inputs" ).empty();
	 $("#inputs").append (message.htmlText);
}

function addForm (){
	$("#creatForm").empty ();
	 $("#creatForm").append ("<form id = create name = create><input id = addDoc name = addDoc placeholder = Document Name><input type = button value = Save id = Save onclick = addValue()></form>");
}
</script>
<script type="text/javascript">
function addValue() {	
	 var result = jQuery.ajax({
		url: "documentAdd",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#create').serialize ()
	
});
	 $("#creatForm").empty ();
	 $("#creatForm").append ("<button onclick = addForm()> Create Document </button>");
	 getValue ();
}
</script>
<style>
#SearchUsername{
color: white;
}
#button {
    background-color: none;
    
    color: black;
    padding: 14px 20px;
    margin: 8px;
    border-style: double;
    cursor: pointer;
    width: 8%;
}
.month {
   padding: 0px 0px;
    width: 100%;
    height: 10%;
    background: none;
    text-align: center;
    colour: black;
    font-family: 'Aguafina Script';
}

.month ul {
    margin: 0;
    padding: 0;
}

.month ul li {
    color: black;
    font-size: 40px;
    text-transform: uppercase;
    letter-spacing: 3px;
}
#calendarButton {
    background-color: none;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
#todoButton {
    background-color: none;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
button:hover {
    opacity: 0.5;
}

* {box-sizing: border-box;}
ul {list-style-type: none;}
body {font-family: Verdana, sans-serif;}
</style>
</head>
<body onload="getValue();">

<table>
<tr>
<td>
<form action="userSearchServlet" method="get">
<fieldset>
<input type="text" id="SearchUsername" placeholder="Search Username" name="username">

</fieldset>
</form>
</td>
<td>
<form action="todolist.jsp">
    <button type="submit" id = todoButton>To Do List</button>
</form>
</td>
<td>
<form action="calendar.jsp">
	<button type="submit" id = calendarButton>Calendar</button></form>
</td>
</tr>
</table>
<div class="month">      
  <ul>
    <li >
      <span id = monthText> Documents </span><br>
    </li>
  </ul>
</div>
<form action="documentViewServlet" method="get">
<div  id = "inputs">

</div>



</form>

<div id = creatForm>
<button onclick = "addForm()">Create Document </button>

</div>
</body>
</html>