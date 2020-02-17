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
import com.amzi.dao.ToDoListDB;

@WebServlet ("/todolist")

public class TodolistServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int amount = 0;
		int num = 0;
		
		boolean delete = true;
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		String[] day;
		String[] month;
		String[] year;
		String[] time;
		String[] event;
		String paramNameThree = "";
		String paramNameFour = "";
		String paramNameFive  ="";
		String paramName = "";
		String paramNameTwo = "";
		
		response.setContentType("text/plain");
		
		 
		
		        Enumeration<String> parameterNames = request.getParameterNames();
		        
		     
		
		        if (parameterNames.hasMoreElements()) {
		        	paramName = parameterNames.nextElement();
		        	
		        	paramName = parameterNames.nextElement();
		        	
		        	paramName = parameterNames.nextElement();
		        	
		            
		            while (parameterNames.hasMoreElements()){
		            					num ++;

		            		        	

					            		day = request.getParameterValues("dayForm");
							        	

								        month = request.getParameterValues("monthForm");
							        	

								        year = request.getParameterValues("yearForm");
							        	

								        System.out.println(num);
								        time = request.getParameterValues(Integer.toString (num));
								        num ++;
								      
								        System.out.println (time[0]);
								        
								        System.out.println("number " + num);
								        event = request.getParameterValues(Integer.toString (num));
								        System.out.println (event[0]);
								        
								        paramName = parameterNames.nextElement();
								        paramName = parameterNames.nextElement();
								     
								        
								        if (num == 2){
								        	ToDoListDB.addToDoList (name,time [0], event [0],day [0],month [0], year [0],true);
								        }
								        else{
								        	
								        
								        	ToDoListDB.addToDoList (name,time [0], event [0],day[0],month [0], year [0],false);
								        }
					            	}
			            		}
			            		
			            		
			            	

		
		        }
		
		       
		
		    }

        
		
		
		
		
	
