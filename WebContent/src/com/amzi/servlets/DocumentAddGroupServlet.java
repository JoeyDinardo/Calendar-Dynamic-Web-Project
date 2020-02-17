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
@WebServlet ("/documentAddGroup")

public class DocumentAddGroupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String documentName = request.getParameter("addDoc");
    	
    	String name = request.getParameter("groupName");
    	System.out.println("groupName " + name + " documentName " + documentName);
    	DocumentDB.createDocumentGroup(name, documentName);
    }
}
