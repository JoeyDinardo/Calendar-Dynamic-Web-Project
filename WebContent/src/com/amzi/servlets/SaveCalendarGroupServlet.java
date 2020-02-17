package com.amzi.servlets;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.Message;
import com.google.gson.Gson;
import com.amzi.dao.calendarDB;


@WebServlet ("/saveCalendarGroup")
public class SaveCalendarGroupServlet  extends HttpServlet {
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String [] calendarValues =  new String [31];
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		String calendarName = request.getParameter("calendarName");
		String group = request.getParameter("group");
		
			
		
			for (int i = 2; i < 33;i++){
				String [] values = request.getParameterValues (String.valueOf (i));
				if (values != null){
					calendarValues [i-2] = values [0];
				}
				
			}
			String [] month = request.getParameterValues ("monthForm");
			String [] year = request.getParameterValues ("yearForm");
			calendarDB.saveCalendarGroup(group, calendarName, calendarValues, month [0], year [0]);
			

	}
	

}
