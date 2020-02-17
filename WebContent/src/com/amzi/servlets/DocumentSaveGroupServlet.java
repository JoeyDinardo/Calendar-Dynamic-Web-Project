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
@WebServlet ("/documentSaveGroup")
public class DocumentSaveGroupServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {
	    	String name =request.getParameter("groupName");
			String docName =request.getParameter("name");
			String docValue =request.getParameter("document");
	    	DocumentDB.saveDocumentValueGroup(name, docName, docValue);

	    	
	    }
}