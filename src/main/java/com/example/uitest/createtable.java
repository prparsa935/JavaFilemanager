package com.example.uitest;

import java.io.File;
import java.sql.*;
import java.util.List;

import Controller.Controller;
import filemodel.fileentity.Fileenti;
import filemodel.fileservice.Fileserv;


public class createtable{
    public static void re(File fileent,Fileserv fileserv) throws Exception {
        for(File file:fileent.listFiles()){
            System.out.println(file.getPath());
            Fileenti fileenti=new Fileenti();

            Fileenti folder_in=fileserv.open_Folder_path(file.getParentFile().getPath());

            String filename=file.getName();
            fileenti.setPath(file.getPath());
            fileenti.setName(filename);
            if(file.isFile()){
                try{
                    System.out.println(filename);
                    fileenti.setFormat(filename.substring(filename.lastIndexOf('.'),filename.length()-1));

                }
                catch (Exception e){}

            }
            else{
                fileenti.setFormat("");
            }
            fileenti.setIn_Folder(folder_in.getId());
            fileserv.save(fileenti);
            if(file.isDirectory()){
                re(file,fileserv);

            }

        }

    }
    public static void init(File fileent,Fileserv fileserv) throws Exception {
        Fileenti fileenti=new Fileenti();
//        System.out.println(fileent.getParentFile().getPath());
        Fileenti folder_in=fileserv.open_Folder_path(fileent.getParentFile().getPath());
//        System.out.println(folder_in.getName());

        String filename=fileent.getName();
        fileenti.setName(filename);
        fileenti.setPath(fileent.getPath());
        if(fileent.isFile())
            fileenti.setFormat(filename.substring(filename.lastIndexOf('.')+1,filename.length()));
        else{

        }
        fileenti.setId(0);
        fileenti.setIn_Folder(folder_in.getId());
        fileserv.save(fileenti);



    }
    public static void main(String args[]) throws Exception {

//        File file1=new File("D:\\New folder (2)\\ex");
//        File file2=new File("D:\\New folder (2)");
        Fileserv fileserv=Fileserv.getInstance();
//        fileserv.createtable();
        List<Fileenti> drives=fileserv.Getdrives();
        System.out.println(drives.get(2).getPath());
        for(Fileenti drive:drives){
            if(!drive.getPath().equals("C:\\"))
                re(new File(drive.getPath()),fileserv);
        }


//        init(file2,fileserv);
//        init(file1,fileserv);

//        re(file1,fileserv);
//        Connection con;
//        con= DriverManager.getConnection ("jdbc:sqlite:Filemanager.sqlite");
//        PreparedStatement pr=con.prepareStatement("drop table files");
//        pr.executeUpdate();

//        con= DriverManager.getConnection ("jdbc:sqlite:Filemanager.sqlite");
//        PreparedStatement pr=con.prepareStatement("select * from files");
//        ResultSet rs=pr.executeQuery();
//        while(rs.next()){
//            System.out.println("here");
//            System.out.println(rs.getString("id"));
//            System.out.println(rs.getString("path"));
//        }


//        Controller controller=new Controller();
//        Fileserv service=Fileserv.getInstance();
//        try{
//            service.createtable();
//
//        }
//        catch (Exception e){
//
//        }
//
//        Fileenti root=new Fileenti();
//        root.setFormat("root");
//        root.setPath("root");
//        root.setId(0);
//        root.setName("root");
//        root.setIn_Folder(-1);
//        service.save(root);
//        List<File> list=controller.scan_files("");
//        for(File file:list){
//            Fileenti fileent=new Fileenti();
//            fileent.setName(file.getName());
//            fileent.setIn_Folder(0);
//            fileent.setFormat("Drive");
//            fileent.setPath(file.getPath());
//            service.save(fileent);
//        }
////
////
        }


//        try {
//            FileUtils.deleteDirectory(new File("F:\\folder"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scanner Sc=new Scanner(System.in);
//
//        if(Sc.next().equals("g")){
//            File file=new File("D:\\Games\\Dark Souls Remastered\\sound");
//            for(Object o:file.list()){
//                System.out.println(o);
//            }
//
//        }
//
//
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
