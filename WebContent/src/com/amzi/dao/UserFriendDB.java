package com.amzi.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFriendDB {
	
	public static String [] getFriend (String userID){
		Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    ResultSet rsOne = null;
	    int userIDNum;
	    int userIDNumTwo;
	    ArrayList<String> names = new ArrayList<String>();
	    try {
	    	 Class.forName(dbAccess.driver);
	         conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	         pst = conn.prepareStatement("select UserID_PK from Users where UserName=?");
	         pst.setString(1, userID);
	         rs = pst.executeQuery();
	         if(rs.next()) {
	        	 userIDNum = rs.getInt("UserID_PK");
	        	 pst = conn.prepareStatement("select UserIDTwo_FK from friendLists where UserID_FK=?");
	             pst.setInt(1, userIDNum);
	             rs = pst.executeQuery();
	             while (rs.next()){
	            	 pst = conn.prepareStatement("select UserName from Users where UserID_PK=?");
	    	         pst.setString(1, rs.getString("UserIDTwo_FK"));
	    	         rsOne = pst.executeQuery();
	    	         if(rsOne.next()){
	    	        	 names.add ( rsOne.getString("UserName"));
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
	    
	    return names.toArray(new String[names.size()]);
	}
	
	@SuppressWarnings("resource")
	public static void deleteFriend (String userID, String name){
		System.out.println("\ndelete1");
	Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int userIDNum;
    int userIDNumTwo;
    try {
    	 Class.forName(dbAccess.driver);
         conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
         pst = conn.prepareStatement("select UserID_PK from Users where UserName=?");
         pst.setString(1, userID);
         rs = pst.executeQuery();
         if(rs.next()) {
        	 userIDNum = rs.getInt("UserID_PK");
        	 pst = conn.prepareStatement("select UserID_PK from Users where UserName=?");
             pst.setString(1, name);
             rs = pst.executeQuery();
             if (rs.next()){
            	 userIDNumTwo = rs.getInt("UserID_PK");
            	 System.out.println("deleteing");
            	 pst = conn.prepareStatement("DELETE FROM FriendLists where UserID_FK =? AND UserIDTwo_FK =? ");
            	 pst.setInt(1, userIDNum);
 	             pst.setInt(2, userIDNumTwo);
 	             pst.execute();
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
    
}
	@SuppressWarnings("resource")
	public static void saveFriend (String userID, String name){
		Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int userIDNum;
        int userIDNumTwo;
        try {
        	 Class.forName(dbAccess.driver);
             conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
             pst = conn.prepareStatement("select UserID_PK from Users where UserName=?");
             pst.setString(1, userID);
             rs = pst.executeQuery();
             if(rs.next()) {
            	 userIDNum = rs.getInt("UserID_PK");
            	 pst = conn.prepareStatement("select UserID_PK from Users where UserName=?");
                 pst.setString(1, name);
                 rs = pst.executeQuery();
                 if (rs.next()){
                	 userIDNumTwo = rs.getInt("UserID_PK");
                	 pst = conn.prepareStatement("insert into FriendLists (UserID_FK, UserIDTwo_FK)" +  "VALUES (?,?)");
                	 pst.setInt(1, userIDNum);
     	             pst.setInt(2, userIDNumTwo);
     	             pst.execute();
                	 
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
		
	}
	public static String [] searchUser (String userID, String searchName ){
		 	Connection conn = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;
	        String name;
	        ArrayList<String> names = new ArrayList<String>();
	        try {
	        	Class.forName(dbAccess.driver).newInstance();
	            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
	            pst = conn.prepareStatement("select * from Users where UserName LIKE ?");
	            pst.setString(1, "%" + searchName + "%");
	            rs = pst.executeQuery();
	            while(rs.next()) {
	            	name = rs.getString("UserName");
	            	if (!name.equals(userID)){
	            		names.add(name);
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
		
		
		return names.toArray(new String[names.size()]);
	}
	public static boolean [] searchFriends (String userID, String [] names ){
		boolean [] friends = new boolean [names.length];
		Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int [] id = new int [names.length];
        int userId =0 ;
        try {
        	Class.forName(dbAccess.driver);
            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
            pst = conn.prepareStatement("SELECT UserID_PK FROM Users WHERE UserName =? ");
            for (int i = 0; i < names.length;i++){
            	pst.setString(1, names [i]);
                rs = pst.executeQuery();
                if (rs.next ()){
                	id [i] = rs.getInt("UserID_PK");
                	System.out.println(id [i]);
                }
            }
           
            pst.setString(1, userID);
            rs = pst.executeQuery();
         
            if (rs.next ()){
            	userId = rs.getInt ("UserID_PK");
            	
            }
            
            for (int i =0; i < names.length;i++){
            	
                pst = conn.prepareStatement("SELECT count(*) UserIDTwo_FK FROM FriendLists WHERE UserID_FK =? and UserIDTwo_FK =? ");
               
                pst.setInt(1,userId);
                pst.setInt(2,id [i]);
               
                rs = pst.executeQuery();
                
                if (rs.next ()){
                	if (rs.getInt(1) == 1){
                		friends [i] = true;
                	}
                	else{
                		friends [i] = false;
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
		return friends;
		
		
	}
}
