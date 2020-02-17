package com.amzi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;
import com.amzi.dao.Message;
import com.amzi.dao.ToDoListDB;
@WebServlet ("/todolistViewGroup")

public class todolistViewGroupServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	response.setContentType("text/html");  
	       PrintWriter out = response.getWriter(); 
	       String todolistName =request.getParameter("add");
	       
	       String name  =request.getParameter("groupName");
	       
	       if (todolistName == null){
	    	   System.out.println("fdsgsdgdsgfdsgsafaf");
	    	   todolistName =request.getParameter("delete");
	    	   System.out.println(todolistName + name);
	    	   ToDoListDB.deleteToDoListGroup(name, todolistName);
	    	   RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView");
		       Dispatcher.forward(request, response);
	       }
	     
	       System.out.println ("name " + name);
			System.out.println("todolistName " + todolistName);
	       request.setAttribute("add", todolistName);
	       
	       request.setAttribute("groupName", name);
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/todolistGroup.jsp");
	       Dispatcher.forward(request, response);
	       
    }
}