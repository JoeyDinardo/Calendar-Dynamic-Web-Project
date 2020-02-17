package com.amzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDB {
	
	
	
	public static boolean checkAdmin (String groupName, String userId){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     int userid;
	     
	     try{
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("Select GroupID_PK FROM Groups WHERE GroupName = ?");
	            pst.setString(1,groupName);
	            rs = pst.executeQuery();
	            if (rs.next ()){
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement("Select UserID_PK FROM Users WHERE UserName = ?");
		            pst.setString(1,userId);
		            rs = pst.executeQuery();
		            if (rs.next ()){
		            	userid =rs.getInt("UserID_PK");
		            	pst = conn.prepareStatement("Select Admin FROM GroupUsers WHERE GroupID_FK = ? AND UserID_FK =?");
		            	pst.setInt(1, id);
		            	pst.setInt(2, userid);
		            	rs = pst.executeQuery ();
		            	if (rs.next ()){
		            		if (rs.getBoolean("Admin")){
		            			return true;
		            		}
		            		else{
		            			return false;
		            		}
		       
		            	}
		            	
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
	     return false;
		
	}
	
	public static boolean checkUsersGroup (String groupName, String userId){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     try{
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            	pst = conn.prepareStatement("SELECT GroupID_PK FROM groups WHERE GroupName =?");
		            pst.setString(1, groupName);
		            rs = pst.executeQuery();
		            if (rs.next()){
		            	
		            	int groupId = rs.getInt ("GroupID_PK");
		            	System.out.println(id +  groupId);
		            	pst = conn.prepareStatement("SELECT Admin FROM groupusers WHERE GroupID_FK =? and UserID_FK=?");
			            pst.setInt(1, groupId);
			            pst.setInt(2, id);
			            rs = pst.executeQuery();
			            if (rs.next ()){
			            	return true;
			            }
			            
		            	
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
	     return false;
		
	}
	public static void deleteGroupUsers (String groupName, String userId){
		{
			
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
		            	queryId = "SELECT GroupID_PK FROM groups WHERE GroupName =?";
			            pst = conn.prepareStatement(queryId);
			            pst.setString(1, groupName);
			            rs = pst.executeQuery();
			            
			            if(rs.next()){
			            	int groupID = rs.getInt ("GroupID_PK");
			            	pst = conn.prepareStatement("DELETE FROM groupusers where GroupID_FK =? and UserID_FK =?");
			            	pst.setInt(1, groupID);
			            	pst.setInt(2, id);
			            	pst.execute ();
		                    pst.close ();
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
	public static void addGroupUsers (String groupName, String userId){
		
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
	            	queryId = "SELECT GroupID_PK FROM groups WHERE GroupName =?";
		            pst = conn.prepareStatement(queryId);
		            pst.setString(1, groupName);
		            rs = pst.executeQuery();
		            
		            if(rs.next()){
		            	int groupID = rs.getInt ("GroupID_PK");
		            	pst = conn.prepareStatement("INSERT INTO groupusers (GroupID_FK,UserID_FK)VALUES (?,?)");
		            	pst.setInt(1, groupID);
		            	pst.setInt(2, id);
		            	pst.execute ();
	                    pst.close ();
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
	
	public static String [] getGroupNames (String userId, String groupName){
		 ArrayList<String> groupMembers = new ArrayList<String>();
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     ResultSet rsOne = null;
	     int id;
	     int groupID;
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            
	            if(rs.next()){
	            	id = rs.getInt ("UserID_PK");
	            	pst = conn.prepareStatement("SELECT GroupID_PK FROM groups WHERE GroupName =?");
	            	pst.setString(1, groupName);
	            	rs = pst.executeQuery();
	            	if (rs.next()){
	            		groupID = rs.getInt("GroupID_PK");
	            		pst = conn.prepareStatement("SELECT UserID_FK FROM groupusers WHERE GroupID_FK =?");
		            	pst.setInt(1, groupID);
		            	rs = pst.executeQuery();
		            	while (rs.next()){
		            		pst = conn.prepareStatement("Select UserName FROM users WHERE UserID_PK =?");
		            		pst.setInt(1, rs.getInt("UserID_FK"));
		            		rsOne = pst.executeQuery();
		            		while (rsOne.next ()){
		            			groupMembers.add(rsOne.getString ("UserName"));
		            		}
		            		
		            	}
	            		
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
	     return groupMembers.toArray(new String[groupMembers.size()]);   
	}
	public static FriendGroups[] getGroups (String userId){
		ArrayList<FriendGroups> friendGroupList = new ArrayList<FriendGroups>();
		 FriendGroups friendGroup;
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     int userIdOne;
	     int groupID = 0;
	    
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     ResultSet rsOne = null;
	     int id;
	     try {
	    	 Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            String queryId = "SELECT UserID_PK FROM USERS WHERE UserName =?";
	            pst = conn.prepareStatement(queryId);
	            pst.setString(1, userId);
	            rs = pst.executeQuery();
	            
	            if(rs.next()){
	            	friendGroup = new FriendGroups ();
	            	id = rs.getInt ("UserID_PK");
	            	 pst = conn.prepareStatement("SELECT Admin, Creator, GroupID_FK FROM groupusers WHERE UserID_FK =?");
	 	            pst.setInt(1, id);
	 	            rs = pst.executeQuery();
	 	            while (rs.next ()){
	 	            	boolean role = rs.getBoolean ("Admin");
	 	            	friendGroup.admin = role;
	 	            	role = rs.getBoolean("Creator");
	 	            	friendGroup.creator = role;
	 	            	
	 	            	groupID = rs.getInt("GroupID_FK");
	 	            	
	 	            
	 	            	pst = conn.prepareStatement("Select GroupName FROM groups WHERE GroupID_PK =?");
	 	            	pst.setInt(1,groupID);
	 	            	rsOne = pst.executeQuery();
	 	            	if (rsOne.next()){
	 	            		
	 	            		friendGroup.groupName = rsOne.getString("GroupName");
	 	            		
	 	            		friendGroupList.add (new FriendGroups (friendGroup.groupName,friendGroup.admin,friendGroup.creator,friendGroup.generalUser));
	 	            	}
	 	            	
	 	            	
	 	            	
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
	      
	     return friendGroupList.toArray(new FriendGroups[friendGroupList.size()]);     
		
	}

	public static void deleteGroup (String userId, String groupName){
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     int id;
	    
			 try {
		    	 Class.forName(driver).newInstance();
		            conn = DriverManager.getConnection(url + dbName, userName, password);
		            pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName=?");
		            pst.setString(1,groupName);
		            ResultSet rs = pst.executeQuery();
		            if (rs.next()){
		            	id = rs.getInt("GroupID_PK");
		            	pst = conn.prepareStatement("DELETE FROM Documents WHERE GroupID_FK =?");
			            pst.setInt(1, id);
			            pst.execute();
			            pst = conn.prepareStatement("DELETE FROM GroupUsers WHERE GroupID_FK =?");
			            pst.setInt(1, id);
			            pst.execute();
			            pst = conn.prepareStatement("DELETE FROM ShareTodoListGroup WHERE GroupID_FK =?");
			            pst.setInt(1, id);
			            pst.execute();
			            pst = conn.prepareStatement("SELECT SharCalendar_PK FROM ShareCalenderGroup WHERE GroupID_FK=?");
			            pst.setInt(1,id);
			            rs = pst.executeQuery();
			            if (rs.next()){
			            	int idCal = rs.getInt("SharCalendar_PK");
			            	pst = conn.prepareStatement("DELETE FROM Calender WHERE SharCalendar_FK =?");
				            pst.setInt(1, idCal);
				            pst.execute();
				            pst = conn.prepareStatement("DELETE FROM ShareCalenderGroup WHERE GroupID_FK =?");
				            pst.setInt(1, id);
				            pst.execute();
			            }
			            
		            	
		            }
		            pst = conn.prepareStatement("DELETE FROM Groups WHERE GroupName =?");
		            pst.setString(1, groupName);
		            pst.execute();
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
		
	
	public static void createGroup (String userId, String groupName){
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
		            	int count = 0;
		            	id = rs.getInt ("UserID_PK");
		            	 pst = conn.prepareStatement("SELECT count(*) from Groups WHERE GroupName=?");
		 	            pst.setString(1, groupName);
		 	            rs = pst.executeQuery();
		 	            if (rs.next()){
		 	            	count = rs.getInt(1);
		 	            }
		 	           
		 	            System.out.println("Count " + count);
		 	            if (count == 0){
		 	            	
		 	            
		            	pst = conn.prepareStatement ("INSERT INTO Groups (GroupName,UserID_FK) VALUES (?,?)");
		            	pst.setString(1, groupName);
		            	pst.setInt(2, id);
		            	pst.execute ();
		            	pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
		            	pst.setString(1, groupName);
		            	rs = pst.executeQuery();
		            	if (rs.next ()){
		            		int groupID = rs.getInt("GroupID_PK");
		            		
			            	pst = conn.prepareStatement ("INSERT INTO GroupUsers (GroupID_FK,UserID_FK,Creator,Admin) VALUES (?,?,?,?)");
			            	pst.setInt(1, groupID);
			            	pst.setInt(2, id);
			            	pst.setBoolean(3,true);
			            	pst.setBoolean(4,true);
			            	pst.execute ();
		                    pst.close ();
		            	}
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
