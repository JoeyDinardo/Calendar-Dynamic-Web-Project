package com.amzi.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToDoListDB {
	@SuppressWarnings("resource")
	public static String [] getToDoList (String userId, String day, String month, String year){
		
	     Connection conn = null;
	     int id = 0;
	     ArrayList <String> dataInfo = new ArrayList <String> ();
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     try {
	            Class.forName(dbAccess.driver);
	            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            }
	            
	            
	            queryId = "SELECT ToDoListtext, Minute, Hour FROM ToDoList WHERE TableID_FK =? AND Day =? AND Month =? AND Year =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setInt(1, id);
	            pst.setString(2, day);
	            pst.setString(3, month);
	            pst.setString(4, year);
	            rs = pst.executeQuery();
	            if (!rs.next()) {                           
	            	return null;
	            }
	            else{
	            	dataInfo.add (rs.getString("ToDoListtext"));
	            	dataInfo.add(rs.getString("Minute"));
	            	dataInfo.add(rs.getString("Hour"));
	            	
	            
	            
	            while(rs.next()){
	            	dataInfo.add (rs.getString("ToDoListtext"));
	            	dataInfo.add(rs.getString("Minute"));
	            	dataInfo.add(rs.getString("Hour"));
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
	            if (pst != null) {
	                try {
	                    pst.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		return dataInfo.toArray(new String[dataInfo.size()] );
	}
	public static boolean addToDoList(String userId, String time, String event, String day, String month, String year, boolean delete) {  
		boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        int id = 0;
        String hour;
        String minute;
  
        if (time.indexOf(":") >=1){
        	hour = time.substring (0,time.indexOf(":") );
        	
        	if (time.length() >=time.indexOf(":") ){
        		minute = time.substring(time.indexOf(":") + 1, time.length());
        		System.out.println(minute);
        	}
        	else{
        		minute = "";
        	}
        }
        else{
        	hour = "";
        	minute = "";
        }
        try {
        	
            Class.forName(dbAccess.driver).newInstance();
            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
           
            
            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
            pst = conn.prepareStatement(queryId);
            pst.setString(1, userId);
            rs = pst.executeQuery();
           
            
            if(rs.next()){
            	id = rs.getInt(1);
            	}
            if (delete == true){
        		String queryDelete = "DELETE FROM ToDoList WHERE TableID_FK= ? AND Day = ? AND Month = ? AND Year = ?";
        		pst = conn.prepareStatement(queryDelete);
                pst.setInt(1,id );
                pst.setString(2,day );
                pst.setString(3,month );
                pst.setString(4,year);
                
               
                pst.execute ();
        	}
            

            String query = "INSERT INTO ToDoList(Day,Month,Year, ToDoListtext, TableID_FK,Minute,Hour)" +  "VALUES (?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1,day);
            pst.setString(2,month);
            pst.setString(3,year);
            pst.setString(4,event);
            pst.setInt(5,id);
            pst.setString(6,minute);
            pst.setString(7,hour);
            pst.execute ();
            
            pst.close ();
            rs.close ();
            

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
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
	}
	
	public static void saveToDoListNameGroup (String groupId, String toDoList, String time, String event, String day, String month, String year, boolean delete){
		boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        int id = 0;
        String hour;
        String minute;
  
        if (time.indexOf(":") >=1){
        	hour = time.substring (0,time.indexOf(":") );
        	
        	if (time.length() >=time.indexOf(":") ){
        		minute = time.substring(time.indexOf(":") + 1, time.length());
        		System.out.println(minute);
        	}
        	else{
        		minute = "";
        	}
        }
        else{
        	hour = "";
        	minute = "";
        }
        try {
        	
            Class.forName(dbAccess.driver).newInstance();
            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
           
            
            
            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
            pst.setString(1, groupId);
            rs = pst.executeQuery();
           
            
            if(rs.next()){
            	id = rs.getInt("GroupID_PK");
                pst = conn.prepareStatement("SELECT SharTodoList_PK FROM ShareTodoListGroup WHERE GroupID_FK =? AND TodoListName=?");
                pst.setInt(1, id);
                pst.setString(2, toDoList);
                rs = pst.executeQuery();
                if (rs.next()){
                	id = rs.getInt("SharTodoList_PK");
                }
            	
            	}
            if (delete == true){
        		String queryDelete = "DELETE FROM ToDoList WHERE SharTodoList_FK= ? AND Day = ? AND Month = ? AND Year = ?";
        		pst = conn.prepareStatement(queryDelete);
                pst.setInt(1,id );
                pst.setString(2,day );
                pst.setString(3,month );
                pst.setString(4,year);
                
               
                pst.execute ();
        	}
            

            String query = "INSERT INTO ToDoList(Day,Month,Year, ToDoListtext, SharTodoList_FK,Minute,Hour)" +  "VALUES (?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1,day);
            pst.setString(2,month);
            pst.setString(3,year);
            pst.setString(4,event);
            pst.setInt(5,id);
            pst.setString(6,minute);
            pst.setString(7,hour);
            pst.execute ();
            
            pst.close ();
            rs.close ();
            

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
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
	}
	public static void addToDoListNameGroup (String group, String toDoListName){
		 Connection conn = null;
	     int id = 0;
	    
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     try {
	            Class.forName(dbAccess.driver);
	            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, group);
	            rs = pst.executeQuery();
	            if (rs.next ()){
	            	id = rs.getInt("GroupID_PK");
	            	pst = conn.prepareStatement("INSERT INTO sharetodolistgroup (GroupID_FK, TodoListName) VALUES (?,?)");
	            	pst.setInt(1,id );
	                pst.setString(2,toDoListName );

	               
	                pst.execute ();
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
	            if (pst != null) {
	                try {
	                    pst.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	}
	public static String [] getToDoListNameGroup (String group){
		 Connection conn = null;
	     int id = 0;
	     ArrayList <String> toListNames = new ArrayList <String> ();
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     try {
	            Class.forName(dbAccess.driver);
	            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	           
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, group);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement("SELECT TodoListName FROM ShareTodoListGroup WHERE GroupID_FK =?");
		            pst.setInt(1, id);
		            rs = pst.executeQuery();
		            while (rs.next ()){
		            	toListNames.add(rs.getString("TodoListName"));
		            	
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
	            if (pst != null) {
	                try {
	                    pst.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	     return toListNames.toArray(new String[toListNames.size()] );
	}
	public static String [] getToDoListGroup (String groupId, String todolist, String day, String month, String year){
		
	     Connection conn = null;
	     int id = 0;
	     ArrayList <String> dataInfo = new ArrayList <String> ();
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     try {
	            Class.forName(dbAccess.driver);
	            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, groupId);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement("SELECT SharTodoList_PK FROM ShareTodoListGroup WHERE GroupID_FK =? AND TodoListName=?");
		            pst.setInt(1, id);
		            pst.setString(2, todolist);
		            rs = pst.executeQuery();
		            if (rs.next ()){
		            	id = rs.getInt("SharTodoList_PK");
		            }
	            }
	            
	            
	            String queryId = "SELECT ToDoListtext, Minute, Hour FROM ToDoList WHERE SharTodoList_FK =? AND Day =? AND Month =? AND Year =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setInt(1, id);
	            pst.setString(2, day);
	            pst.setString(3, month);
	            pst.setString(4, year);
	            rs = pst.executeQuery();
	            if (!rs.next()) {                           
	            	return null;
	            }
	            else{
	            	dataInfo.add (rs.getString("ToDoListtext"));
	            	dataInfo.add(rs.getString("Minute"));
	            	dataInfo.add(rs.getString("Hour"));
	            	
	            
	            
	            while(rs.next()){
	            	dataInfo.add (rs.getString("ToDoListtext"));
	            	dataInfo.add(rs.getString("Minute"));
	            	dataInfo.add(rs.getString("Hour"));
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
	            if (pst != null) {
	                try {
	                    pst.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		return dataInfo.toArray(new String[dataInfo.size()] );
	}
	public static void deleteToDoListGroup (String groupName, String toDoListName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int groupId;
	     System.out.println("made it to");
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	            pst.setString(1, groupName);
	            rs = pst.executeQuery();
	            if (rs.next ()){
	            	System.out.println("made it frou");
	            	groupId = rs.getInt("GroupID_PK");
	            	System.out.println(groupId + toDoListName);
	            	 pst = conn.prepareStatement("SELECT SharTodoList_PK FROM ShareTodoListGroup WHERE GroupID_FK =? AND TodoListName =?");
	            	 pst.setInt(1, groupId);
	            	 pst.setString(2, toDoListName);
	 	          
	 	            rs = pst.executeQuery();
	 	            if (rs.next()){
	 	            	System.out.println("made it three");
	 	            	int todolistPk = rs.getInt("SharTodoList_PK");
		            	 pst = conn.prepareStatement("Delete FROM ToDoList WHERE SharTodoList_FK =?");
		            	 pst.setInt(1, todolistPk);
		            	 pst.execute();
		            	 System.out.println("made it");
		            	 pst = conn.prepareStatement("Delete FROM ShareTodoListGroup WHERE TodoListName =? AND GroupID_FK=?");
			 	            pst.setString(1, toDoListName);
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
	
	
}
