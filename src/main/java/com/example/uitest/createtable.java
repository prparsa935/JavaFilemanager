package com.example.uitest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class createtable{
    public static void main(String args[]) throws SQLException {
        Scanner Sc=new Scanner(System.in);

        if(Sc.next().equals("g")){
            File file=new File("D:\\Games\\Dark Souls Remastered\\sound");
            for(Object o:file.list()){
                System.out.println(o);
            }

        }

//        //Registering the Driver
//        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//        //Getting the connection
//        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
//        Connection con = DriverManager.getConnection(oracleUrl, "system", "university");
//        System.out.println("Connection established......");
//        //Creating the Statement
//        Statement stmt = con.createStatement();
//        //Query to create a table
//        String query = "CREATE TABLE DISPATCHES("
//                + "ProductName VARCHAR (20) NOT NULL, "
//                + "CustomerName VARCHAR (20) NOT NULL, "
//                + "DispatchDate date, "
//                + "DeliveryTime timestamp, "
//                + "Price INT, "
//                + "Location varchar(20))";
//        stmt.execute(query);
//        System.out.println("Table Created......");
    }
}