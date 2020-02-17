package com.amzi.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.DocumentDB;
import com.amzi.dao.FriendGroups;
import com.amzi.dao.Message;
import com.google.gson.Gson;
import com.amzi.dao.GroupDB;
import com.amzi.dao.UserFriendDB;

@WebServlet ("/addUserGroup")
public class AddUserGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("AddUserServlet");
		String html = "";
		response.setContentType("text/html");
		Message message = new Message ();
		String groupName =request.getParameter("groupName");
		
		String friend = request.getParameter("add");
		System.out.println("groupName: " + groupName);
		System.out.println("friendName: " + friend);
		
		
		HttpSession session = request.getSession();
	       String name = (String)session.getAttribute("name");
		String [] names = UserFriendDB.getFriend(name);
		html = "<table>";
		for (int i =0; i < names.length;i++){
			System.out.println(names [i]);
			if (GroupDB.checkUsersGroup(groupName, names [i])){
				html += "<tr><td> " + names [i] +  " </td> <td>";
				
				html += "<button value = " + names [i] + " name = delete >" + "Delete " + " </button> </td></tr>";
			}
			else{
				html += "<tr><td> " + names [i] +  " </td> <td>";
				html += "<button value = " + names [i] + " name = add >" + "add" + " </button> </td></tr>";
			}
		}
		html += "</table>";
		System.out.println(html);
		message.setText(html);
		Gson gson = new Gson(); 
    	String content = gson.toJson(message);
    	response.setContentType("text/json");
    	response.getWriter().print(content);
		
	}
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		String groupName =request.getParameter("groupName");
		System.out.println("groupName: " +groupName );
		String friend = request.getParameter("add");
		System.out.println("friendName: " + friend);
		if (friend == null){
			friend = request.getParameter("delete");
			System.out.println("friendName: " + friend);
			if (friend != null){
				GroupDB.deleteGroupUsers(groupName, friend);
				
			}
			RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView");
		    Dispatcher.forward(request, response);
		}else{
			GroupDB.addGroupUsers(groupName, friend);
			
			
			RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView");
		    Dispatcher.forward(request, response);
			
		}
		
		   
		
	}
	
	
}