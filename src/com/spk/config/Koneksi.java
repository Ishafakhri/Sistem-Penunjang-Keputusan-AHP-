package com.spk.config;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Koneksi {
    private static Connection conn;
    
    public static Connection getConnection(){
        if(conn == null){
            try{
                String url = "jdbc:mysql://localhost:3306/db_spk_ahp";
                String username = "root";
                String password = "";
                DriverManager.registerDriver(new Driver());
                conn = (Connection) DriverManager.getConnection(url, username, password);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
