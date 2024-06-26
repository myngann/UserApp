/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class Oracleconn {
    public static Connection getConn(){
        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String uname="C##CHU_TRO";
        String upass="mn";
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e){
            System.out.print("OracleDriver not found");

        }
        Connection c=null;
        try{
            c=DriverManager.getConnection(url,uname,upass);
        } catch (SQLException e){
            System.out.print("Connect failed. Check output console");
            e.printStackTrace();

        }
        if (c!=null){
            System.out.println("Connection Success");

        } else {
            System.out.println("Connection failed");

        }
        return c ;
    }
    public static void closeConn(Connection c){
        try{
            if (c!=null)
                c.close();
        } catch (SQLException e){
            System.out.print("Connect closed");
            e.printStackTrace();
        }
    }
    
    
}

