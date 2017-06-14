package test.seeyoui.kensite.sqlGeneration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
    public static Connection oracleConn(String name, String pass, String ip, String port, String dbname) {
       Connection c = null;
       try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       try {
           c = DriverManager.getConnection(
                  "jdbc:oracle:thin:@"+ip+":"+port+":"+dbname, name, pass);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return c;
    }
    
    public static Connection mysqlConn(String name, String pass, String ip, String port, String dbname) {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(
                   "jdbc:mysql://"+ip+":"+port+"/"+dbname+"?characterEncoding=utf-8&failOverReadOnly=false&autoReconnect=true&roundRobinLoadBalance=true", name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
     }
}