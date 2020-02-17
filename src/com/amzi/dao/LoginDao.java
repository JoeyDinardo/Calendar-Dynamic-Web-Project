package com.amzi.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static boolean validate(String name, String pass) {        
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        
        try {
            Class.forName(dbAccess.driver);
            conn = DriverManager
                    .getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);

            pst = conn.prepareStatement("select * from Users where UserName=? and Password=?");
            byte[] bytePass = pass.getBytes("UTF-8");
        	
        	MessageDigest md = MessageDigest.getInstance("MD5");
        	byte[] password = md.digest(bytePass);
        	pass = "";
        	for (int i =0; i < password.length; i++){
        		pass += password [i];
        	}
            pst.setString(1, name);
            pst.setString(2, pass);

            rs = pst.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println(e);
            return false;
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
}
