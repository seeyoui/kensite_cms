package com.seeyoui.kensite.sqlGeneration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
    public static Connection oracleConn(String name, String pass, String ip) {
       Connection c = null;
       try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       try {
           c = DriverManager.getConnection(
                  "jdbc:oracle:thin:@"+ip+":1521:orcl", name, pass);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return c;
    }
}