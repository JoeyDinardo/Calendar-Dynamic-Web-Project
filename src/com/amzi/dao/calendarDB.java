package com.amzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class calendarDB {
	
	public static void saveCalendar (String userId, String [] calendarValues, String month, String year){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     try {
	            Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            	queryId = "DELETE FROM Calender WHERE TableID_FK =? and Month = ? and Year = ?";
		            pst = conn.prepareStatement(queryId);
		            pst.setInt(1, id);
		            pst.setString(2, month);
		            pst.setString(3, year);
		            pst.execute ();
		            for (int i = 0; i < calendarValues.length;i++){
		            	if (calendarValues [i] != null){
		            		pst = conn.prepareStatement("INSERT INTO Calender(Day,Month,Year, TextCalender, TableID_FK, Share)" +  "VALUES (?,?,?,?,?,?)");
		            		 pst.setString (1,Integer.toString (i + 1));
		            		 pst.setString (2,month);
		            		 pst.setString (3,year);
		            		 pst.setString (4,calendarValues [i]);
		            		 pst.setInt (5,id);
		            		 pst.setBoolean (6,false);
		            		 pst.execute ();
		                     
		                     pst.close ();
		            	}
		            	
		            }
	            }
	            
	     } catch (Exception e) {
        System.out.println(e);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	}
	
	
	public static String [] getCalendar (String userId, String month, String year){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     String [] calendarValues = new String [33];
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     try {
	    	 	Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);

	    	 	pst = conn.prepareStatement("SELECT UserID_PK FROM USERS WHERE UserName =?");
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            	
	          
		           
		            for (int i= 1; i < 33;i++){
		            	pst = conn.prepareStatement("SELECT TextCalender FROM Calender WHERE TableID_FK =? AND Day = ? AND Month = ? and Year = ?");
			            pst.setInt(1, id);
			            pst.setString(2, Integer.toString (i));
			            pst.setString(3, month);
			            pst.setString(4, year);
			            rs = pst.executeQuery();
			            if(rs.next()){
			            	calendarValues [i] = rs.getString ("TextCalender");
			            	if (calendarValues [i] == null){
			            		calendarValues [i] = " ";
			            	}
			            	
			            }
			            else{
			            	calendarValues [i] = "";
			            }
		            }
		            return calendarValues;
	            }
	            
	            
		 
	     } catch (Exception e) {
       System.out.println(e);
   } finally {
       if (conn != null) {
           try {
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
	}

	     return calendarValues;	
	}
	
	public static void saveCalendarGroup (String group, String calendarName, String [] calendarValues, String month, String year){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     try {
	            Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, group);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement("SELECT SharCalendar_PK FROM ShareCalenderGroup WHERE GroupID_FK =? and CalendarName=?");
	            	pst.setInt(1,id);
	            	pst.setString(2,calendarName);
	            	rs = pst.executeQuery ();
	            	if (rs.next()){
	            		id = rs.getInt("SharCalendar_PK");
	            		 pst = conn.prepareStatement("DELETE FROM Calender WHERE SharCalendar_FK =? and Month = ? and Year = ?");
	 		            pst.setInt(1, id);
	 		            pst.setString(2, month);
	 		            pst.setString(3, year);
	 		            pst.execute ();
	 		            for (int i = 0; i < calendarValues.length;i++){
	 		            	if (calendarValues [i] != null){
	 		            		pst = conn.prepareStatement("INSERT INTO Calender(Day,Month,Year, TextCalender, SharCalendar_FK, Share)" +  "VALUES (?,?,?,?,?,?)");
	 		            		 pst.setString (1,Integer.toString (i + 1));
	 		            		 pst.setString (2,month);
	 		            		 pst.setString (3,year);
	 		            		 pst.setString (4,calendarValues [i]);
	 		            		 pst.setInt (5,id);
	 		            		 pst.setBoolean (6,false);
	 		            		 pst.execute ();
	 		                     
	 		                     pst.close ();
	 		            	}
	 		            	
	 		            }
	            		
	            	}
	            	
	            	
		           
	            }
	            
	     } catch (Exception e) {
        System.out.println(e);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	}
	public static String [] getCalendarGroupValues (String group, String calendarName, String month, String year){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     String [] calendarValues = new String [33];
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     try {
	    	 	Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);

	    	 	pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, group);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement("SELECT SharCalendar_PK FROM ShareCalenderGroup WHERE GroupID_FK =? AND CalendarName=?");
		            pst.setInt(1, id);
		            pst.setString(2,calendarName);
		            rs = pst.executeQuery();
		            if (rs.next ()){
		            	id = rs.getInt("SharCalendar_PK");
		            	for (int i= 1; i < 33;i++){
			            	pst = conn.prepareStatement("SELECT TextCalender FROM Calender WHERE SharCalendar_FK =? AND Day = ? AND Month = ? and Year = ?");
				            pst.setInt(1, id);
				            pst.setString(2, Integer.toString (i));
				            pst.setString(3, month);
				            pst.setString(4, year);
				            rs = pst.executeQuery();
				            if(rs.next()){
				            	calendarValues [i] = rs.getString ("TextCalender");
				            	if (calendarValues [i] == null){
				            		calendarValues [i] = " ";
				            	}
				            	
				            }
				            else{
				            	calendarValues [i] = "";
				            }
		            }
	            }
	          
		           
		            
		            
	            
	            
	            
		 
	     } }catch (Exception e) {
       System.out.println(e);
   } finally {
       if (conn != null) {
           try {
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
	}
	     

	     return calendarValues;	
	}
	
	public static String [] getCalendarGroup (String groupName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     ArrayList <String> calendarNames = new ArrayList <String> ();
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, groupName);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("GroupID_PK");
	            	
	            pst = conn.prepareStatement("SELECT CalendarName FROM ShareCalenderGroup WHERE GroupID_FK =?");
	            pst.setInt(1, id);
	            rs = pst.executeQuery();
	            while(rs.next()){
	            	calendarNames.add(rs.getString ("CalendarName"));
	            }
	            }
	     }catch (Exception e) {
	         System.out.println(e);
	     } finally {
	         if (conn != null) {
	             try {
	                 conn.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }
	     }
	     return (calendarNames.toArray(new String[calendarNames.size()]));
	}
	public static void deleteCalendarGroup (String groupName, String calendarName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int groupId;
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, groupName);
	            rs = pst.executeQuery();
	            if (rs.next ()){
	            	groupId = rs.getInt("GroupID_PK");
	            	 pst = conn.prepareStatement("SELECT SharCalendar_PK FROM ShareCalenderGroup WHERE CalendarName =? AND GroupID_FK=?");
	 	            pst.setString(1, calendarName);
	 	           pst.setInt(2, groupId);
	 	            rs = pst.executeQuery();
	 	            if (rs.next()){
	 	            	int calendarPk = rs.getInt("SharCalendar_PK");
		            	 pst = conn.prepareStatement("Delete FROM Calender WHERE SharCalendar_FK =?");
		            	 pst.setInt(1, calendarPk);
		            	 pst.execute();
		            	 pst = conn.prepareStatement("Delete FROM ShareCalenderGroup WHERE CalendarName =? AND GroupID_FK=?");
			 	            pst.setString(1, calendarName);
			 	           pst.setInt(2, groupId);
			 	          pst.execute();
		            	 

	 	            }
	            	
	            }
	     } catch (Exception e) {
	         System.out.println(e);
	     } finally {
	         if (conn != null) {
	             try {
	                 conn.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }
	     }
		
	}
	public static void addCalendargroup (String userID, String groupName, String calendarName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     int groupID;
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);

	    	 	pst = conn.prepareStatement("SELECT UserID_PK FROM USERS WHERE UserName =?");
	            pst.setString(1, userID);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            	pst = conn.prepareStatement("SELECT GroupID_PK FROM GROUPS WHERE GroupName =?");
	            	pst.setString(1, groupName);
	            	rs = pst.executeQuery();
	            	if (rs.next ()){
	            		groupID = rs.getInt("GroupID_PK");
	            		pst = conn.prepareStatement("INSERT INTO ShareCalenderGroup(GroupID_FK,CalendarName)" +  "VALUES (?,?)");
	            		 pst.setInt(1,groupID);
	            		 pst.setString (2,calendarName);
	            		 pst.execute ();
	            		
	            		
	            	}
	            }
	    	 
	     }
	     catch (Exception e) {
	         System.out.println(e);
	     } finally {
	         if (conn != null) {
	             try {
	                 conn.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }
	     }
		
	}
	
}
