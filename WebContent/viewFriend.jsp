<head>
<link rel="StyleSheet" href="CalendarStyleSheet.css">
  <link href='https://fonts.googleapis.com/css?family=Aguafina Script' rel='stylesheet'>
<script  src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">

function addForm (){
	$("#creatForm").empty ();
	 $("#creatForm").append ("<form id = create name = create><input id = addGroup name = addGroup placeholder = Group Name><input type = button value = Save id = Save onclick = addValue()></form>");
}
function getValue() {	
	 var result = jQuery.ajax({
		url: "groupGet",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#form1').serialize ()
	
});
	var message = jQuery.parseJSON(result.responseText);
	$( "#inputs" ).empty();
	 $("#inputs").append (message.htmlText);
}
function addValue() {	
	 var result = jQuery.ajax({
		url: "createGroup",
		type: 'POST',
		dataType: 'json',
		async: false,
		data: $('#create').serialize ()
	
});
	 $("#creatForm").empty ();
	 $("#creatForm").append ("<button onclick = addForm()> Create Group </button>");
	 getValue ();
}
</script>
<style>
td{
color: white;
font-family: "Aguafina Script";
font-size: 25px;
}
h3{
font-family: "Aguafina Script";
color: black;
font-size: 30px;
}
</style>
</head>
<body onload="getValue();">
<% String [] names=(String []) request.getAttribute("names");%>
<h3>FriendsList</h3>
<table>
<%
for (int i =0; i < names.length;i++){
	%>
	<tr>
	<td>
	<%=names[i]%>
	</td>
	</tr>
<% 	
}
%>
</table>
<form action="groupView" method="post">
<div  id = "inputs">

</div>
</form>


<div id = creatForm>
<button onclick = "addForm()">Create Group </button>

</div>
</body>
