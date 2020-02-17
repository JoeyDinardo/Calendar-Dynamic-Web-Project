package com.amzi.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.Message;
import com.amzi.dao.ToDoListDB;
import com.google.gson.Gson;

@WebServlet ("/addTodolistGroup")

public class AddTodolistGroupServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		System.out.println("AddTodolistGroupServlet");
		String groupName = request.getParameter("groupName");
		String toDoListName = request.getParameter ("addDoc");
		System.out.println(groupName  );
		System.out.println("ToDoList " + toDoListName);
		ToDoListDB.addToDoListNameGroup(groupName, toDoListName);
		
	}
}
	
	