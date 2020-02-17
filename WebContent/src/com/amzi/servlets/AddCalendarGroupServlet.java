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
import com.amzi.dao.calendarDB;

@WebServlet ("/addCalendarGroup")
public class AddCalendarGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		
		String calendarName =request.getParameter("addCal");
		String groupName =request.getParameter("groupName");
		HttpSession session = request.getSession();
	    String name = (String)session.getAttribute("name");
		calendarDB.addCalendargroup(name, groupName, calendarName);
		
		
	}
	
}