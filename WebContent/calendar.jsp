<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

<head>
  <link rel="StyleSheet" href="CalendarStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
 <style>
* {box-sizing: border-box;}
ul {list-style-type: none;}
body {font-family: 'Aguafina Script';}
textarea {
  height:100px;
  width: 100%;

opacity:0.5;

}
.month {
    padding: 0px 0px;
    width: 100%;
    height: 10%;
    background: none;
    text-align: center;
}

.month ul {
    margin: 0;
    padding: 0;
}

.month ul li {
    color: Black;
    font-size: 40px;
    text-transform: uppercase;
    letter-spacing: 3px;
}

.month .prev {
    float: left;
    padding-top: 10px;

   
}

.month .next {
    float: right;
    padding-top: 10px;

}

.weekdays {
    margin: 0;
    padding: 10px 0;
    background-color: none;
}

.weekdays li {
    display: inline-block;
    width: 13.6%;
    color: none;
    text-align: center;
}

#tables{
    width:100%;
    padding: 0;
    margin: 0;
    border: 0;
    border-collapse: collapse;
    
}
#days {
    padding: 0px 0;
    margin: 0;
    height: 90%;
    width: 100%;
}
.search{
border-style: none;
}
#docButton {

    background-color: None;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
#toDoButton {

    background-color: None;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
#Notify{
background-color: None;
color: white;
}
#SearchUser{
background-color: None;
    color: white;
    border: none;
 border-style: none;
}
#userGroupButton{
color: white;
}


#days li {
    list-style-type: none;
    display: inline-block;
    text-align: center;
    margin:0;
    padding:0;
    font-size:30px;
    color: none;
}

#days li .active {

   padding: 10px;
   background: #1abc9c;
   color: white !important 
}

/* Add media queries for smaller screens */

</style>
<title>Welcome <%=session.getAttribute("name")%></title>
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
function getValue() {	
	 var result = jQuery.ajax({
		url: "calendar",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#dayform').serialize ()
	
});
	
 var message = jQuery.parseJSON(result.responseText);
 
 $("#days").append (message.htmlText); 
	 
	 
	

}
function notifyMe(){
	
	var task ='';
	var temp;
	for (i = 1; i <= 31; i++) {
		if ($('textarea#' + i).val()) {
		temp = $('textarea#' + i).val();
		if (temp && temp != undefined){
			task += (temp + '\n');
		}
		}		
	}
	task = task.replace(/^\s+|\s+$/gm,'');
    alert(task.trim());
		
}
function insertText () {
	var d = new Date ();
    $("#days").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + (d.getMonth ()).toString() + "></input>");
    $("#days").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + (d.getFullYear ()).toString() + "></input>");
    
    determineMonth (d.getMonth ().toString());
    getValue();
}

function determineMonth (month){
	$("#monthText").empty ();
	if (month == '0'){
		$("#monthText").append ("January");
	}
	if (month == '1'){
		$("#monthText").append ("February");
	}
	if (month == '2'){
		$("#monthText").append ("March");
	}
	if (month == '3'){
		$("#monthText").append ("April");
	}
	if (month == '4'){
		$("#monthText").append ("May");
	}
	if (month == '5'){
		$("#monthText").append ("June");
	}
	if (month == '6'){
		$("#monthText").append ("July");
	}
	if (month == '7'){
		$("#monthText").append ("August");
	}
	if (month == '8'){
		$("#monthText").append ("September");
	}
	if (month == '9'){
		$("#monthText").append ("October");
	}
	if (month == '10'){
		$("#monthText").append ("November");
	}
	if (month == '11'){
		$("#monthText").append ("Decemeber");
	}
}


function nextMonth (){
	var month = parseInt (document.getElementById('monthForm').value);
	var year = parseInt (document.getElementById('yearForm').value);
	if (month == 11){
		month = 0;
		year = year + 1;
	}
	else{
		month = month + 1;
	}
	$("#days" ).empty();
	 $("#days").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + month.toString() + "></input>");
	 $("#days").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + year.toString() + "></input>");
	
	getValue ();
	determineMonth (month.toString());
	$("#yearText" ).empty();
	$("#yearText" ).append(year.toString());
	
}

function backMonth (){
	var month = parseInt (document.getElementById('monthForm').value);
	var year = parseInt (document.getElementById('yearForm').value);
	if (month == 0){
		month = 11;
		year = year -1;
	}
	else{
		month = month -1;
	}
	$("#days" ).empty();
	 $("#days").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + month.toString() + "></input>");
	 $("#days").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + year.toString() + "></input>");
	 
	getValue ();
	
	determineMonth (month.toString());
	$("#yearText" ).empty();
	$("#yearText" ).append(year.toString());
	
}
</script>
</head>
<body>



</head>
<body onload="insertText();">

<div id = search>
<table>
<tr>
<td>
<form action="userSearchServlet" method="get">
<fieldset>
<input type="text" id="SearchUser" placeholder="Search Username" name="username" >

</fieldset>
</form>
</td>
<td>
<form action="todolist.jsp">
    <button type="submit" id = todoButton>To Do List</button>
</form>
</td>

<td>
<form action="documents.jsp">
	<button type="submit" id = docButton>Documents</button></form>

</td>
<td>
<form action="viewFriends" method = "post">
    <button type="submit" id = userGroupButton>Group</button>
</form>
</td>

<td>
<form>
<input type="button" value="Notify" id="Notify" onclick="notifyMe()">
</form>
</td>
</tr>
</table>
</div>

<div class="month" >      
  <ul>
    <li class="prev"><button id = backbutton onclick = "backMonth()">back </button></li>
    <li class="next"><button id = backbutton onclick = "nextMonth()">next </button></li>
    <li >
      <span id = monthText> October </span>
      <span id = yearText style="font-size:40px">2017</span>
    </li>
  </ul>
</div>



<form name = "dayform" id = "dayform">


<div  id = days >

 </div>

</form>

</body>
</html>
