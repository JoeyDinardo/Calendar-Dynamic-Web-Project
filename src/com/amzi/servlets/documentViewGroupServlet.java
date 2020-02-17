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
import com.amzi.dao.DocumentDB;
@WebServlet ("/documentViewGroup")

public class documentViewGroupServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	response.setContentType("text/html");  
	       PrintWriter out = response.getWriter(); 
	       String docName =request.getParameter("add");
	       String name  =request.getParameter("groupName");
	       if (docName == null){
	    	   System.out.println ("GorupName " + name);
	    	   System.out.println("deletes");
	    	   docName =request.getParameter("delete");
	    	   DocumentDB.deleteDocumentGroup(name, docName);
	    	   
	    	   request.setAttribute("add", name);
	    	   RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/groupView");
		       Dispatcher.forward(request, response);
	       }
	       
	       String docValue = DocumentDB.getDocumentValueGroup(name, docName);
	       if (docValue == null){
	    	   docValue = "";
	       }
	       request.setAttribute("docName", docName);
	       request.setAttribute("docText", docValue);
	       request.setAttribute("groupName", name);
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/documentViewGroup.jsp");
	       Dispatcher.forward(request, response);
	       
    }
}