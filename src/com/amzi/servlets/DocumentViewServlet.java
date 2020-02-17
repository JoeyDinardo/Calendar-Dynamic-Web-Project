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
@WebServlet ("/documentView")

public class DocumentViewServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	response.setContentType("text/html");  
	       PrintWriter out = response.getWriter(); 
	       String docName =request.getParameter("add");
	       HttpSession session = request.getSession();
	       String name = (String)session.getAttribute("name");
	       String docValue = DocumentDB.getDocumentValue(name, docName);
	       if (docValue == null){
	    	   docValue = "";
	       }
	       request.setAttribute("docName", docName);
	       request.setAttribute("docText", docValue);
	       RequestDispatcher Dispatcher = getServletContext().getRequestDispatcher("/documentView.jsp");
	       Dispatcher.forward(request, response);
	       
    }
}