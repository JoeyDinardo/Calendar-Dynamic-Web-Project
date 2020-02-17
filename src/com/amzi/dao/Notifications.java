package com.amzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Notifications {
	public static boolean validate(String name, String pass) {        
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName(dbAccess.driver);
            conn = DriverManager
                    .getConnection(dbAccess.url + dbAccess.dbName, dbAccess.userName, dbAccess.password);

            pst = conn.prepareStatement("SELECT `todolist`.`Day`, `todolist`.`Month`,`todolist`.`Year`,`todolist`.`Hour`,`todolist`.`Minute`,`todolist`.`ToDoListtext`, FROM `webtestone`.`todolist`;");
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
