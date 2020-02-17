<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="StyleSheet" href="CalendarStyleSheet.css">
<link href='https://fonts.googleapis.com/css?family=Aguafina Script'
	rel='stylesheet'>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function saveValue() {

		$.ajax({
			url : "documentSave",
			type : 'POST',
			dataType : 'json',
			data : $('#docSave').serialize()
		});

	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#document").keyup(function() {
			saveValue();
		});
	});
	;
</script>
<style>
#SearchUsername{
   color: white;
}
#todoButton {
	background-color: none;
	color: white;
	border: none;
	cursor: pointer;
	width: 100%;
}

#calendarButton {
	background-color: none;
	color: white;
	border: none;
	cursor: pointer;
	width: 100%;
}

#button {
	background-color: none;
	color: white;
	padding: 14px 20px;
	margin: 8px;
	border: none;
	cursor: pointer;
	width: 8%;
}

html, body {
	height: 100%;
}

#document {
	margin-top: 15%;
	width: 60%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	left: 20%
}

#save {
	posistion: fixed;
}

.month {
	padding: 10px;
	width: 100%;
	height: 10%;
	margin: 0 0 20px 0;
	background: none;
	text-align: center;
}

.month ul {
	margin: 0;
	padding: 0;
}

.month ul li {
	color: black;
	font-family: Aguafina Script;
	font-size: 40px;
	text-transform: uppercase;
	letter-spacing: 3px;
}
</style>

</head>
<body>
	<table>
		<tr>
			<td>
				<form action="userSearchServlet" method="get">
					<fieldset>
						<input type="text" id="SearchUsername"
							placeholder="Search Username" name="username">

					</fieldset>
				</form>
			</td>
			<td>
				<form action="todolist.jsp">
					<button type="submit" id=todoButton>To Do List</button>
				</form>
			</td>
			<td>
				<form action="calendar.jsp">
					<button type="submit" id=calendarButton>Calendar</button>
				</form>
			</td>
		</tr>
	</table>
	<%
		String name = (String) request.getAttribute("docName");
		String docText = (String) request.getAttribute("docText");
	%>
	<div class="month">
		<ul>
			<li><span id=monthText> <%=name%>
			</span><br></li>
		</ul>
	</div>

	<form id=docSave>
		<input type=hidden value=<%=name%> name=name id=name>
		<textarea style="overflow: hidden" form=docSave id=document
			name=document TextMode="multiline" ToolTip="Content">
<%=docText%>
</textarea>


	</form>

</body>