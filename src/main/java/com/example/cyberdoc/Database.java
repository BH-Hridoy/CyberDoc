package com.example.cyberdoc;

import java.sql.*;


public class Database {

    public static Connection connectDB(){

        try{

            Class.forName("com.mysql.jdbc.Driver");

           // DATABASE LINK
           // Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/(DATABASE NAME)", "DATABASE USERNAME", "DATABASE PASSWORD");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", ""); // here ROOT IS OUR DEFAULT USERNAME AND BLANK OR NULL IS OUR DEFAULT DATABASE PASSWORD

            return connect;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

}
