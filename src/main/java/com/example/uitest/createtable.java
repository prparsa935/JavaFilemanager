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
            try{
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

            }catch (Exception e){

            }


        }



    }
    public static void main(String args[]) throws Exception {
        Fileserv fileserv=Fileserv.getInstance();
        Controller controller=new Controller();
        controller.setupdrives();
        List<Fileenti> drives=fileserv.Getdrives();
        System.out.println(drives.get(2).getPath());
        for(Fileenti drive:drives){
                re(new File(drive.getPath()),fileserv);
        }


        }
    }
