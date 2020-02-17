package com.amzi.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.Message;
import com.google.gson.Gson;
import com.amzi.dao.GroupDB;

@WebServlet ("/createGroup")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		String groupName = request.getParameter ("addGroup");
		GroupDB.createGroup(name, groupName);
	}

}
