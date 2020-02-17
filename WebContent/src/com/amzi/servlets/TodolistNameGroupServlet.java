package com.amzi.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.GroupDB;
import com.amzi.dao.Message;
import com.amzi.dao.ToDoListDB;
import com.google.gson.Gson;

@WebServlet ("/todolistNameGroup")

public class TodolistNameGroupServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("TodolistNameGroupServlet");
		Message message = new Message ();
		PrintWriter out = response.getWriter();
		String groupName = request.getParameter("groupName");
		String htmlText = "";
		HttpSession session = request.getSession();
	       String name = (String)session.getAttribute("name");
		String [] names = ToDoListDB.getToDoListNameGroup(groupName);
		for (int i = 0; i < names.length;i++){
			
			htmlText += "<input type = submit id =add name = add value = " + names [i] +"  >";
			if (GroupDB.checkAdmin(groupName, name)){
				
			
			htmlText += "<button type = submit id =delete name = delete value = "+ names [i] +"  > Delete</button>";
			}
			}
			
		System.out.println("Html Text " + htmlText + "Html Text ");
		message.setText (htmlText);
    	Gson gson = new Gson(); 
    	String content = gson.toJson(message);
    	response.setContentType("text/json");
    	response.getWriter().print(content);
		
	}
	
}
