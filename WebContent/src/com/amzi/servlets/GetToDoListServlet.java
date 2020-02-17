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

import com.amzi.dao.ToDoListDB;
import com.google.gson.Gson;
import com.amzi.dao.Message;
@WebServlet ("/getToDoList")

public class GetToDoListServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	
    	String name = request.getParameter("groupName");
    	String todolist = request.getParameter("todolist");
    	String [] day = request.getParameterValues("dayForm");
        String [] month = request.getParameterValues("monthForm");
        String [] year = request.getParameterValues("yearForm");
        String html = "";
    	Message message = new Message();
    	int id = 0;
    	message.setError(true);
    	System.out.println("GroupNames " + name);
    	System.out.println("Todolists " + todolist);
    	String [] data = ToDoListDB.getToDoListGroup (name, todolist, day [0], month [0], year [0]);
    	
    	if (data == null){
    		html = "<tr><th>Time</th> <th>Activity</th></tr>";
    		html += "<tr><td><input type = time name = 1 id = 1></td>";
    		html += "<td><textarea style= overflow:hidden rows= 4 cols= 50 " + "name = 2 id = 2 form= form1></textarea><td></tr>";
    		message.setNum (2);
    	}
    	
    	else{
    		html = "<tr><th>Time</th> <th>Activity</th></tr>";
    		for (int i = 2; i < data.length;i+=3){
    			System.out.println("The data " +  data [i-2] );
    			id +=2;
        		System.out.println("data" + data [i]);
        		
        		html += "<tr><td><input type = time name = " +  Integer.toString(id -1) + " id = " + Integer.toString(id -1) + " value = " + data [i] +":" +  data [i-1]  +  "> </td>";
        		html += "<td><textarea rows= 4 cols= 50 style= overflow:hidden " + "name = " +  Integer.toString(id) + " id = " + Integer.toString(id) + "form= form1" +  ">" + data [i-2] +  "</textarea></td></tr>";
        		
        		
        	}
    		message.setNum (id);
    	}
    	
    	
    	message.setText (html);
    	Gson gson = new Gson(); 
    	String content = gson.toJson(message);
    	response.setContentType("text/json");
    	response.getWriter().print(content);
    }  
} 

