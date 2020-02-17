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

@WebServlet ("/viewFriends")
public class ViewFriendsServlet extends HttpServlet  {
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException { 
		HttpSession session = request.getSession();
	    String name = (String)session.getAttribute("name");
	    System.out.println("names" + name);
		String [] names = UserFriendDB.getFriend(name);
	    request.setAttribute("names", names);
	    RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/viewFriend.jsp");
	    Dispatcher.forward(request, response);
		
	}

}
