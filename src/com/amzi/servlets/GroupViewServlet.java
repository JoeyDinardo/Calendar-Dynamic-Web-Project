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
@WebServlet ("/groupView")
public class GroupViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("GroupViewServlet");
		response.setContentType("text/html");  
		   int charLocation = 0;
		   HttpSession session = request.getSession();
		   String name = (String)session.getAttribute("name");
	       PrintWriter out = response.getWriter(); 
	       String groupName =request.getParameter("add");
	       System.out.println("Add groupName " + groupName);
	       if (groupName == null){
	    	   groupName =request.getParameter("delete");
	    	   System.out.println("groupName " + groupName);
	    	   if (groupName!= null){
	    		   if (GroupDB.checkAdmin(groupName, name)){
	    			   GroupDB.deleteGroup(name, groupName);
	    			   RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/viewFriends");
	    		       Dispatcher.forward(request, response);
	    		   }
	    		   
	    	   }
	       }
	       System.out.println("GroupName " + groupName);
	       StringBuilder myName = new StringBuilder (groupName);
	       while (charLocation != -1){
	    	   charLocation = myName.indexOf ("+");
	    	   if (charLocation != -1){
	    		   myName.setCharAt (charLocation,' ');
	    	   }
	    	   
	       }
	       
	       String calendarNames [] = calendarDB.getCalendarGroup(groupName);
	      
	      
	       String userNames [] = GroupDB.getGroupNames (name, myName.toString());
	       groupName = myName.toString();
	       System.out.println("GroupName " + groupName);
	       request.setAttribute("groupName", groupName);
	       request.setAttribute("userNames", userNames);
	       request.setAttribute("calendarNames", calendarNames);
	       request.setAttribute ("Admin",GroupDB.checkAdmin (groupName,name));
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView.jsp");
	       Dispatcher.forward(request, response);
	}
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("GroupViewServlet");
		response.setContentType("text/html");  
		   int charLocation = 0;
		   HttpSession session = request.getSession();
		   String name = (String)session.getAttribute("name");
	       PrintWriter out = response.getWriter(); 
	       String groupName =request.getParameter("groupName");
	       System.out.println("Add groupName " + groupName);
	       
	       if (groupName == null){
	    	   groupName =request.getParameter("add");
	    	   if (groupName == null){
	    		   groupName =request.getParameter("group"); 
	    		   if (groupName == null){
	    			   
	    		  
	    	   
	    	   groupName =request.getParameter("delete");
	    	   System.out.println("groupName " + groupName);
	    	   if (groupName!= null){
	    		   if (GroupDB.checkAdmin(groupName, name)){
	    			   GroupDB.deleteGroup(name, groupName);
	    			   RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/viewFriends");
	    		       Dispatcher.forward(request, response);
	    		   }
	    		   
	    	   }
	    		   }
	    	  }
	       }
	       System.out.println("GroupName " + groupName);
	       StringBuilder myName = new StringBuilder (groupName);
	       while (charLocation != -1){
	    	   charLocation = myName.indexOf ("+");
	    	   if (charLocation != -1){
	    		   myName.setCharAt (charLocation,' ');
	    	   }
	    	   
	       }
	       
	       String calendarNames [] = calendarDB.getCalendarGroup(groupName);
	      
	      
	       String userNames [] = GroupDB.getGroupNames (name, myName.toString());
	       groupName = myName.toString();
	       System.out.println("GroupName " + groupName);
	       request.setAttribute("groupName", groupName);
	       request.setAttribute("userNames", userNames);
	       request.setAttribute("calendarNames", calendarNames);
	       request.setAttribute ("Admin",GroupDB.checkAdmin (groupName,name));
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView.jsp");
	       Dispatcher.forward(request, response);
	}
	
}