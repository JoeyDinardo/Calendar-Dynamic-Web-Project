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
import com.amzi.dao.UserFriendDB;

@WebServlet ("/saveFriend")
public class SaveFriendServlet extends HttpServlet  {
	 private static final long serialVersionUID = 1L;
	 public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException { 
		 	response.setContentType("text/html");  
	       PrintWriter out = response.getWriter(); 
	       HttpSession session = request.getSession();
	       String name = (String)session.getAttribute("name");
	       if (request.getParameter("Add") == null){
	    	  String userName = request.getParameter("Delete");
	    	   System.out.println(userName);
	    	   UserFriendDB.deleteFriend(name, userName);
	       }
	       else{
	    	   String userName =request.getParameter("Add");
	    	   
		       
		       UserFriendDB.saveFriend(name, userName);
	       }
	       RequestDispatcher rd = request.getRequestDispatcher("viewFriends");
	       rd.forward(request,response);
	       
	 }

}
