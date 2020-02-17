package com.amzi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.UserFriendDB;

public class UserSearchServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	    	 
	       response.setContentType("text/html");  
	       PrintWriter out = response.getWriter(); 
	       String userName =request.getParameter("username");
	       HttpSession session = request.getSession();
	       String name = (String)session.getAttribute("name");
	       String [] names = UserFriendDB.searchUser (name, userName);
	       boolean friends [] = UserFriendDB.searchFriends (name,names);
	       request.setAttribute("friends", friends);
	       request.setAttribute("names", names);
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/searchUser.jsp");
	       Dispatcher.forward(request, response);
	       
	        
	    }
	        
}
