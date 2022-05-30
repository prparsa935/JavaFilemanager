package com.example.uitest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class createtable{
    public static void main(String args[]) throws SQLException {
        //Registering the Driver
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //Getting the connection
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "system", "university");
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Query to create a table
        String query = "CREATE TABLE DISPATCHES("
                + "ProductName VARCHAR (20) NOT NULL, "
                + "CustomerName VARCHAR (20) NOT NULL, "
                + "DispatchDate date, "
                + "DeliveryTime timestamp, "
                + "Price INT, "
                + "Location varchar(20))";
        stmt.execute(query);
        System.out.println("Table Created......");
    }
}