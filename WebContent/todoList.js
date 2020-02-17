var xmlHttpRequest  = new XMLHttpRequest ();
function findTutorials (){
	xmlHttpRequest.open ("POST","todolistServlet?name=" + document.getElementById ('usera').value,true);
	xmlHttpRequest.onreadystatechange = processTutorials;
	xmlHttpRequest.send ();
	document.getElementById ('usera').value = "";
}

function processTutorials (){
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
		table.innerHTML = "";
		var dom = (new DOMParse ()).parseFromString (xmlHttpRequest.responseText, "text/xml");
		
	}
}

