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
@WebServlet ("/documentGet")

public class DocumentGetServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {
	    	Message message = new Message();
	    	String html = "";
	    	HttpSession session = request.getSession();
	    	String name = (String)session.getAttribute("name");
	    	String [] names = DocumentDB.getDocument(name);
	    	for (int i = 0; i < names.length;i++){
	    		html += "<button id =  button value =  "  + names [i] + " name =  add > " + names [i] + "</button><td>";
	    	}
	    	
	    	message.setText (html);
	    	Gson gson = new Gson(); 
	    	String content = gson.toJson(message);
	    	response.setContentType("text/json");
	    	response.getWriter().print(content);
	    }
}
