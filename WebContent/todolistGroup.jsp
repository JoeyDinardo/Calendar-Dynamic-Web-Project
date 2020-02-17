<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

 <script  src="jquery-3.2.1.min.js"></script>

<script type="text/javascript">

function saveValue() {
	
			$.ajax({
				url: "saveTodolistGroup",
				type: 'POST',
				dataType: 'json',
				data: $('#form1').serialize ()
	});
		
}	

function getValue() {	
	 var result = jQuery.ajax({
		url: "getToDoList",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#form1').serialize ()
	    
});
	 var message = jQuery.parseJSON(result.responseText);
	 addNum (message.num);
	 $("#inputs").append (message.htmlText);
	 
	 
	 
	

}	
	
	

</script>

<script type="text/javascript">
var num = 2;
var num2 = 0;
function addTable() {
	num += 1;
	num2 = num + 1;
	$("#inputs").append("<tr><td><input type = time name = " + num +  " id = " + num + " ></input></td><td><textarea style= overflow:hidden rows= 4 cols= 50 " + " name = " + num2 +   " id = " + num2 +  " form= form1></textarea></td></tr>");
	num += 1;
	
	
	

    
}
function addNum(number){
	num = number;
}
function zeroNum (){
	num = 2;
}
</script>
<script type="text/javascript" >
function insertText () {
	var d = new Date ();
	var monthText = "";
	if (d.getMonth () == 11){
		monthText = "December";
	}
	else if (d.getMonth () == 10){
		monthText = "November";
	}
	else if (d.getMonth () == 9){
		monthText = "October";
	}
	else if (d.getMonth () == 8){
		monthText = "September";
	}
	else if (d.getMonth () == 7){
		monthText = "August";
	}
	else if (d.getMonth () == 6){
		monthText = "July";
	}
	else if (d.getMonth () == 5){
		monthText = "June";
	}
	else if (d.getMonth () == 4){
		monthText = "May";
	}
	else if (d.getMonth () == 3){
		monthText = "April";
	}
	else if (d.getMonth () == 2){
		monthText = "February";
	}
	else if (d.getMonth () == 1){
		monthText = "January";
	}
	
	
    document.getElementById('month').innerHTML = monthText + "/";
    document.getElementById('day').innerHTML = (d.getDate ()).toString () + "/";
    document.getElementById('year').innerHTML = (d.getFullYear ()).toString();
    document.getElementById('month').value = d.getMonth () + 1;
    document.getElementById('day').value = d.getDate ();
    document.getElementById('year').value = d.getFullYear ();
    
    
    document.getElementById('month').style.fontFamily = "Aguafina Script";
    document.getElementById('day').style.fontFamily = "Aguafina Script";
    document.getElementById('year').style.fontFamily = "Aguafina Script";
    document.getElementById('month').style.fontSize = '25px';
    document.getElementById('day').style.fontSize = '25px';
    document.getElementById('year').style.fontSize = '25px';
    $("#inputs").append("<input type = hidden name = 'dayForm' id = 'dayForm' value = "  + (d.getDate ()).toString () + "></input>");
    $("#inputs").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + (d.getMonth () + 1).toString() + "></input>");
    $("#inputs").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + (d.getFullYear ()).toString() + "></input>");
    getValue ();
    zeroNum ();
    
    
}
</script>
<script type="text/javascript">
function nextDate () {
	var d = new Date ();
	var month = parseInt (document.getElementById('month').value);
	var monthText = "";
	var day = parseInt (document.getElementById('day').value);
	var year = parseInt (document.getElementById('year').value);
	if (month == 12 && day == 31 ){
		month = 1;
		day = 1;
		year = year + 1;
		monthText = "January";
	}
	else if (month == 11 && day == 30 ){
		month = 12;
		monthText = "December";
		day = 1;
		
	}
	else if (month == 10 && day == 31 ){
		month = 11;
		day = 1;
		monthText = "November";
	}
	else if (month == 9 && day == 30 ){
		month = 10;
		day = 1;
		monthText = "October";
	}
	else if (month == 8 && day == 31 ){
		month = 9;
		day = 1;
		monthText = "September";
	}
	else if (month == 7 && day == 31 ){
		month = 8;
		day = 1;
		monthText = "August";
	}
	else if (month == 6 && day == 30 ){
		month = 7;
		day = 1;
		monthText = "July";
	}
	else if (month == 5 && day == 31 ){
		month = 6;
		day = 1;
		monthText = "June";
	}
	else if (month == 4 && day == 30 ){
		month = 5;
		day = 1;
		monthText = "May";
	}
	else if (month == 3 && day == 31 ){
		month = 4;
		day = 1;
		monthText = "April";
	}
	else if (month == 2 && day == 28 && year%4 != 0 ){
		month = 3;
		day = 1;
		monthText = "March";
	}
	else if (month == 2 && day == 29){
		day = 1;
		month = 3;
		monthText = "March";
	}
	else if (month == 1 && day == 31 ){
		month = 2;
		day = 1;
		monthText = "February";
	}
	else{
		day += 1;
	}
		
		if (monthText!= ""){
			 document.getElementById('month').innerHTML = monthText + "/";

		}
	    document.getElementById('day').innerHTML = day.toString() + "/";
	    document.getElementById('year').innerHTML = year;
	    document.getElementById('month').value = month;
	    document.getElementById('day').value = day;
	    document.getElementById('year').value = year;
	    var parent = document.getElementById("inputs");
	    var child = document.getElementById("dayForm");
	    parent.removeChild(child);
	    var child = document.getElementById("monthForm");
	    parent.removeChild(child);
	    var child = document.getElementById("yearForm");
	    parent.removeChild(child);
	    $("#inputs" ).empty();
	  
	    $("#inputs").append("<input type = hidden name = 'dayForm' id = 'dayForm' value = "  + day.toString() + "></input>");
	    $("#inputs").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + month.toString() + "></input>");
	    $("#inputs").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + year.toString()+ "></input>");
	    getValue();
	    zeroNum ();

}
</script>
<script type="text/javascript">
function backDate () {
	var d = new Date ();
	var monthText = "";
	var month = parseInt (document.getElementById('month').value);
	var day = parseInt (document.getElementById('day').value);
	var year = parseInt (document.getElementById('year').value);
	if (month == 12 && day == 1 ){
		month = 11;
		day = 30;
		monthText = "November"
		
	}
	else if (month == 11 && day == 1 ){
		month = 10;
		day = 31;
		monthText = "October";
		
	}
	else if (month == 10 && day == 1){
		month = 9;
		day = 30;
		monthText = "September";
	}
	else if (month == 9 && day == 1 ){
		month = 8;
		day = 31;
		monthText = "August";
	}
	else if (month == 8 && day == 1 ){
		month = 7;
		day = 31;
		monthText = "July";
	}
	else if (month == 7 && day == 1 ){
		month = 6;
		day = 30;
		monthText = "June";
	}
	else if (month == 6 && day == 30 ){
		month = 5;
		day = 31;
		monthText = "May";
	}
	else if (month == 5 && day == 31 ){
		month = 4;
		day = 30;
		monthText = "April";
	}
	else if (month == 4 && day == 30 ){
		month = 3;
		day = 31;
		monthText = "March";
	}
	else if (month == 3 && day == 1 && year%4 != 0 ){
		month = 2;
		day = 28;
		monthText = "February";
	}
	else if (month == 3 && day == 1 ){
		month = 2;
		day = 29;
		monthText = "February";
	}
	else if (month == 2 && day == 1){
		day = 31;
		month = 1
		monthText = "January";
	}
	else if (month == 1 && day == 1 ){
		month = 12;
		day = 31;
		year -= 1;
		monthText = "December";
	}
	else{
		day -= 1;
	}
		
		
	if (monthText!= ""){
		 document.getElementById('month').innerHTML = monthText + "/";

	}
	    document.getElementById('day').innerHTML = day.toString() + "/";
	    document.getElementById('year').innerHTML = year.toString();
	    document.getElementById('month').value = month;
	    document.getElementById('day').value = day;
	    document.getElementById('year').value = year;
	    var parent = document.getElementById("inputs");
	    var child = document.getElementById("dayForm");
	    parent.removeChild(child);
	    var child = document.getElementById("monthForm");
	    parent.removeChild(child);
	    var child = document.getElementById("yearForm");
	    parent.removeChild(child);
	    $("#inputs" ).empty();
	 
	    $("#inputs").append("<input type = hidden name = 'dayForm' id = 'dayForm' value = "  + day.toString() + "></input>");
	    $("#inputs").append("<input type = hidden name = 'monthForm' id = 'monthForm' value = "  + month.toString() + "></input>");
	    $("#inputs").append("<input type = hidden name = 'yearForm' id = 'yearForm' value = "  + year.toString()+ "></input>");
	    getValue();

}
</script>
<style>

#Add{

    background-color: white;
    color: white;
    border: none;
   
    font-family: 'Aguafina Script'; 
    font-size:20px;
    background:none;
}
#Add:Hover{
  opacity: 0.5;
}
#Save{
    background-color: white;
    color: white;
    border: none;
    font-family: 'Aguafina Script'; 
    font-size:20px;
    background:none;
}
#Save:Hover{
  opacity: 0.5;
}
#docButton {
    background-color: None;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
#calendarButton {
    background-color: None;
    color: white;
    border: none;
    cursor: pointer;
    width: 100%;
}
#dateHeader {
    padding: 0px 0px 0px 0px;
    margin: 0px 0px 0px 0px;
    width: 100%;
    height: 100%;
    background: None;
    text-align: center;
}
#SearchUsername{
 background-color: white;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    font-family: 'Aguafina Script'; 
    font-size:20px;
    background:none;
}
#SearchUsername.Hover{
 opacity: 0.5;
}
</style>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <link rel="StyleSheet" href="todoListStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<body onload="insertText();">
<% String groupName =(String) request.getAttribute("groupName");
String todolist =(String) request.getAttribute("add");
%>
<table>
<tr>
<td>
<form action="userSearchServlet" method="get">
<fieldset>
<input type="text" id = "SearchUsername" placeholder="Search Username" name="username" >

</fieldset>
</form>
</td>
<td>
<form action="calendar.jsp">
    <button type="submit" id = calendarButton>Calendar</button>
</form>
</td>
<td>
<form action="documents.jsp">
	<button type="submit" id = docButton>Documents</button></form>
</td>
</tr>
</table>

<div id = dateHeader>
    <table  id = date>
        <tr>
        <td id = back> <button id = backbutton onclick = "backDate()" style="font-size:30px">back </button> </td>
        <td id = month ></td>
        <td id = day ></td>
        <td id = year ></td>
        <td id = next> <button id = nextbutton onclick = "nextDate()" style="font-size:30px">next </button> </td>
        </tr>
        
    </table>
 </div>
    
<form name = "form1"  id = form1>
<input type = hidden id = todolist name = todolist value = <%=todolist %> >
<input type = hidden id = groupName name = groupName value = <%=groupName %> >
 <table id = inputs style="opacity: 0.6">
 
 
	
</table>

    <input type = button value = "Save" id = "Save" onclick = "saveValue()">
    <input type = "button" value = "add" id = "Add" onclick = "addTable()">
 </form>
   
</body>
</html>

