package com.amzi.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.amzi.dao.FriendGroups;
import com.amzi.dao.Message;
import com.google.gson.Gson;
import com.amzi.dao.GroupDB;

@WebServlet ("/groupGet")
public class GroupGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Message message = new Message();
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		FriendGroups [] friendGroup = GroupDB.getGroups(name);
		String htmlText = "<table>";
		for (int i =0; i < friendGroup.length; i ++){
			int charLocation = 0;
	    	StringBuilder myName = new StringBuilder ();
	    	
	    		myName = new StringBuilder (friendGroup[i].groupName);
	    		 while (charLocation != -1){
			    	   charLocation = myName.indexOf (" ");
			    	   if (charLocation != -1){
			    		   myName.setCharAt (charLocation,'+');
			    	   }
	    		 }
			       
	    		 System.out.println ("myName " + myName.toString());
			System.out.println(friendGroup[i].groupName);
			htmlText += "<tr><td><button ";
			htmlText += " value = " + myName.toString () +" id = add name = add ";
			htmlText += ">" + friendGroup [i].groupName + "</button> </td>";
			if (GroupDB.checkAdmin (friendGroup [i].groupName, name)){
				htmlText += "<td><button ";
				htmlText += " value = " + myName.toString () +" id = delete name = delete ";
				htmlText += "> Delete </button> </td></tr>";
			}
		}
		message.setText (htmlText);
		Gson gson = new Gson(); 
    	String content = gson.toJson(message);
    	response.setContentType("text/json");
    	response.getWriter().print(content);

		
		
	}

}
