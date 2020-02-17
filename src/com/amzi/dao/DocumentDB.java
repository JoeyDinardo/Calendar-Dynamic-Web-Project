package com.amzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentDB {
	
	public static void createDocument (String userId, String name){
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     ArrayList<String> documents = new ArrayList<String> ();
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
	            	pst = conn.prepareStatement ("INSERT INTO documents (Name, UserID_FK) VALUES (?,?)");
	            	pst.setString(1, name);
	            	pst.setInt(2, id);
	            	pst.execute ();
                    pst.close ();
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
	public static String [] getDocument (String userId){
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     ArrayList<String> documents = new ArrayList<String> ();
	     Connection conn = null;
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
	        	 pst = conn.prepareStatement("Select Name from Documents WHERE UserID_FK =?");
		         pst.setInt(1, id);
		         rs = pst.executeQuery();
		         while (rs.next ()){
		        	 documents.add(rs.getString("Name"));
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
	     return (documents.toArray(new String[documents.size()]));
	}
	public static String getDocumentValue (String userId, String docName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     String text = "";
	     
	     
	     try {
	    	 Class.forName(driver).newInstance();
	         conn = DriverManager.getConnection(url + dbName, userName, password);
	         pst = conn.prepareStatement("SELECT UserID_PK FROM USERS WHERE UserName =?");
	         pst.setString(1, userId);
	         rs = pst.executeQuery();
	         
	        if (rs.next ()){
	        	id = rs.getInt ("UserID_PK");
	        	pst = conn.prepareStatement("Select Documents from Documents WHERE UserID_FK =? AND Name =?");
		         pst.setInt(1, id);
		         pst.setString(2, docName);
		         rs = pst.executeQuery();
		         while (rs.next ()){
		        	 text = rs.getString("Documents");
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
	     return text;
	}
	public static void saveDocumentValue (String userId, String docName, String docValue){
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
	         pst = conn.prepareStatement("SELECT UserID_PK FROM USERS WHERE UserName =?");
	         pst.setString(1, userId);
	         rs = pst.executeQuery();
	         if(rs.next()){
	        	
	        	 id = rs.getInt ("UserID_PK");
	        	 pst = conn.prepareStatement ("UPDATE documents set Documents = ? where UserID_FK =? AND Name =?");
	            	pst.setString(1, docValue);
	            	pst.setInt(2, id);
	            	pst.setString(3, docName);
	            	pst.execute ();
                 pst.close ();
		         
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
	}
	public static void deleteDocumentGroup (String groupId, String docName){
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
	            pst.setString(1, groupId);
	            rs = pst.executeQuery();
	            
	            if(rs.next()){
	            	
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement ("DELETE FROM Documents WHERE GroupID_FK=? AND Name =?");
	            	pst.setInt(1, id);
	            	pst.setString(2,docName);
	            	pst.execute ();
	            	pst.close ();
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
	public static void createDocumentGroup (String groupId, String name){
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
	            pst.setString(1, groupId);
	            rs = pst.executeQuery();
	            
	            if(rs.next()){
	            	
	            	id = rs.getInt ("GroupID_PK");
	            	pst = conn.prepareStatement ("INSERT INTO documents (Name, GroupID_FK) VALUES (?,?)");
	            	pst.setString(1, name);
	            	pst.setInt(2, id);
	            	pst.execute ();
                   pst.close ();
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
	public static String [] getDocumentGroup (String groupId){
		 String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     ArrayList<String> documents = new ArrayList<String> ();
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     
	     
	     try {
	    	 Class.forName(driver).newInstance();
	         conn = DriverManager.getConnection(url + dbName, userName, password);
	         pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	         pst.setString(1, groupId);
	         rs = pst.executeQuery();
	      
	         if(rs.next()){
	        	 id = rs.getInt ("GroupID_PK");
	        	 pst = conn.prepareStatement("Select Name from Documents WHERE GroupID_FK =?");
		         pst.setInt(1, id);
		         rs = pst.executeQuery();
		         while (rs.next ()){
		        	 documents.add(rs.getString("Name"));
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
	     return (documents.toArray(new String[documents.size()]));
	}
	public static String getDocumentValueGroup (String groupId, String docName){
		String url = "jdbc:mysql://localhost:3306/";
	     String dbName = "webtestOne";
	     String driver = "com.mysql.jdbc.Driver";
	     String userName = "root";
	     String password = "password";
	     Connection conn = null;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     int id;
	     String text = "";
	     
	     
	     try {
	    	 Class.forName(driver).newInstance();
	         conn = DriverManager.getConnection(url + dbName, userName, password);
	         pst = conn.prepareStatement("SELECT GroupID_PK FROM Groups WHERE GroupName =?");
	         pst.setString(1, groupId);
	         rs = pst.executeQuery();
	         
	        if (rs.next ()){
	        	id = rs.getInt ("GroupID_PK");
	        	pst = conn.prepareStatement("Select Documents from Documents WHERE GroupID_FK =? AND Name =?");
		         pst.setInt(1, id);
		         pst.setString(2, docName);
		         rs = pst.executeQuery();
		         while (rs.next ()){
		        	 text = rs.getString("Documents");
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
	     return text;
}
	
	public static void saveDocumentValueGroup (String groupId, String docName, String docValue){
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
	         pst.setString(1, groupId);
	         rs = pst.executeQuery();
	         if(rs.next()){
	        	
	        	 id = rs.getInt ("GroupID_PK");
	        	 pst = conn.prepareStatement ("UPDATE documents set Documents = ? where GroupID_FK =? AND Name =?");
	            	pst.setString(1, docValue);
	            	pst.setInt(2, id);
	            	pst.setString(3, docName);
	            	pst.execute ();
                 pst.close ();
		         
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
	}
}
