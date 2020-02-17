<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="StyleSheet" href="CalendarStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<head>
 <script  src="jquery-3.2.1.min.js"></script>
<script  type="text/javascript">
function getToDoList() {	
	 var result = jQuery.ajax({
		url: "todolistNameGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#todoListsGet').serialize ()
	
});
	var message = jQuery.parseJSON(result.responseText);
	$( "#inputTodoList" ).empty();
	 $("#inputTodoList").append (message.htmlText);
}


function addToDoList() {	
	 var result = jQuery.ajax({
		url: "addTodolistGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#createTodoList').serialize ()
	
});
	 $("#createFormTodoList").empty ();
	 $("#createFormTodoList").append ("<button onclick = addFormTodoList()> Create To do list </button>");
	 getToDoList ();
	 
}

function addValueDocument() {	
	 var result = jQuery.ajax({
		url: "documentAddGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#createDocument').serialize ()
	
});
	 $("#creatFormDocument").empty ();
	 $("#creatFormDocument").append ("<button onclick = addFormDocument()> Create Document </button>");
	 getDocument ();
	 
}

function getDocument() {	
	 var result = jQuery.ajax({
		url: "documentGetGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#getDocuments').serialize ()
	
});
	var message = jQuery.parseJSON(result.responseText);
	$( "#inputs" ).empty();
	 $("#inputs").append (message.htmlText);
}
$(document).ready(function () {
	getDocument();
	getToDoList ();
	getCalendar ();
});

function addFormTodoList (){
	$("#createFormTodoList").empty ();
	 $("#createFormTodoList").append ("<input id = addDoc name = addDoc placeholder = To Do List><input type = button value = Save id = Save onclick = addToDoList()>");
}
function addFormDocument (){
	$("#creatFormDocument").empty ();
	 $("#creatFormDocument").append ("<input id = addDoc name = addDoc placeholder = Document Name><input type = button value = Save id = Save onclick = addValueDocument()>");
}

function addUser() {
	var result = jQuery.ajax({
		url: "addUserGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#addUserForm').serialize ()
});
	 var message = jQuery.parseJSON(result.responseText);
	 $("#friends").empty ();
	 $("#friends").append (message.htmlText);
}
function addForm (){
	$("#creatForm").empty ();
	 $("#creatForm").append ("<input id = addCal name = addCal placeholder = Calendar Name><input type = button value = Save id = Save onclick = addCalendar()>");
}

function addCalendar() {
	var result = jQuery.ajax({
		url: "addCalendarGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#create').serialize ()
});
	$("#creatForm").empty ();
	 $("#creatForm").append ("<button onclick = addForm()>Create Calendar </button>");
	 getCalendar();
	 
}
function getCalendar() {	
	 var result = jQuery.ajax({
		url: "calendarGetGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#getDocuments').serialize ()
	
});
	var message = jQuery.parseJSON(result.responseText);
	$( "#calendar" ).empty();
	 $("#calendar").append (message.htmlText);
}


</script>
</head>
<style>
h1{
color: white;
font-family: "Aguafina Script";
font-size: 25px;
}
h4{
font-family: "Aguafina Script";
color: black;
font-size: 30px;
}
td{
color: white;
font-family: "Aguafina Script";
font-size: 25px;
}
button{
font-size:25px;
}
</style>
<body onload = "addUser()">

<% String [] names=(String []) request.getAttribute("userNames");
String [] calendarNames=(String []) request.getAttribute("calendarNames");
String groupName = (String) request.getAttribute ("groupName");
Boolean Admin = (Boolean) request.getAttribute ("Admin");
%>
<h4><%=groupName %></h4>
<h1>Users</h1>
<div id = names>
<table>
<%for (int i = 0; i < names.length; i++){ %>
	<tr>
	<td>
	<%=names [i] %>
	</td>
	</tr>
<%} %>
</table>
<h4>Calendars</h4>
<form action="calendarGroup" method="GET">
<div id = calendar>

</div>
<input type = hidden value = <%=groupName %> name = group id = group>
</form>
<form id = todoListsGet name = todoListsGet action="todolistViewGroup" method="get">

<div id = inputTodoList>

</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
</form>
<form id = getDocuments name = getDocuments action="documentViewGroup" method="get">
<div id = inputs>
</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
</form>
</div>
<%if (Admin == true){  %>
<form id = addUserForm name = addUserForm action="addUserGroup" method="get">
<div id = friends name = friends>
</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
</form>
<%} %>
<%if (Admin == true){  %>
<form id = create name = create>
<div id = creatForm>

<button onclick = "addForm()">Create Calendar </button>

</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
<%} %>

</form>
<%if (Admin == true){  %>
<form id = createDocument name = createDocument>
<div id = creatFormDocument>
<button onclick = "addFormDocument()">Create Document </button>
</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
</form>
<%} %>
<%if (Admin == true){  %>
<form id = createTodoList name = createTodoList>
<div id = createFormTodoList>
<button onclick = "addFormTodoList()">Create To do list </button>
</div>
<input type = hidden value = "<%=groupName %>" name = groupName id = groupName >
</form>
<%} %>



</body>