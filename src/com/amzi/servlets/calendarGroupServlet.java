package com.amzi.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amzi.dao.calendarDB;


	@WebServlet ("/calendarGroup")

	public class calendarGroupServlet extends HttpServlet{
		 private static final long serialVersionUID = 1L;

		    public void doGet(HttpServletRequest request, HttpServletResponse response)  
		            throws ServletException, IOException {
		    	String name = request.getParameter("add");
		    	if (name == null){
		    		
		    		name = request.getParameter("delete");
		    		String Groupname = request.getParameter("group");
		    		System.out.println("Name " + name);
		    		System.out.println("Groupname " + Groupname);
		    		calendarDB.deleteCalendarGroup(Groupname, name);
		    		request.setAttribute("add", name);
			    	RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView");
				    Dispatcher.forward(request, response);
		    	}
		    	
		    	RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/calendarGroup.jsp");
			    Dispatcher.forward(request, response);
		    }
	}


