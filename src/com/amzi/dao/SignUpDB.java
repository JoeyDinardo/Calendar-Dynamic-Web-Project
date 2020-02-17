package com.amzi.dao;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDB {
	public static boolean validate(String name, String pass) {        
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            Class.forName(dbAccess.driver);
            conn = DriverManager.getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);
            String query = "select * from Users where UserName=? and Password=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            status = rs.next();
            final String queryCheck = "SELECT count(*) from Users WHERE UserName=?";
            pst = conn.prepareStatement(queryCheck);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
            pst.close ();
            rs.close ();
            if (count == 0){
            	byte[] bytePass = pass.getBytes("UTF-8");
            	
            	MessageDigest md = MessageDigest.getInstance("MD5");
            	byte[] password = md.digest(bytePass);
            	pass = "";
            	for (int i =0; i < password.length; i++){
            		pass += password [i];
            	}
	            query = "insert into Users (UserName, Password)" +  "VALUES (?,?)";
	            pst = conn.prepareStatement(query);
	            pst.setString(1, name);
	            pst.setString(2, pass);
	            pst.execute();
	            status = true;
            }
            else{
            	status = false;
            }
            
            

        } catch (Exception e) {
            System.out.println("Got exception");
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
}



