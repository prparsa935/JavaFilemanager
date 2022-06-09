package Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.util.List;

import filemodel.fileentity.Fileenti;
import filemodel.fileservice.Fileserv;
import org.apache.commons.io.FileUtils;

public class Controller {
    public static String current_loc = "";
    private int files_here;

    //getter setter
//    public int getN() {
//        return files_here;
//    }

    //scan operation
    public List<File> scan_files(String dirpath)throws IOException {
        List<File> result = new ArrayList<>();
        if (dirpath == null || dirpath.length() < 3){
            File[] drives = File.listRoots();
            if (drives != null && drives.length > 0) {
                for (File aDrive : drives) {
                    System.out.println(aDrive.toString());
                    result.add(aDrive);
                }
            }
            return result;
        }

        return null;
    }
    public void setupdrives() throws Exception {
    Fileserv service=Fileserv.getInstance();
    try{
        service.uninstall();

    }
    catch (Exception e){}
    service.createtable();
    Fileenti root=new Fileenti();
        root.setFormat("root");
        root.setPath("root");
        root.setId(1);
        root.setName("root");
        root.setIn_Folder(-1);
        service.save(root);
    List<File> list=scan_files("");
        for(File file:list){
            Fileenti fileent=new Fileenti();
            fileent.setName(file.getPath());
            fileent.setIn_Folder(1);
            fileent.setFormat("Drive");
            fileent.setPath(file.getPath());
            service.save(fileent);
    }
    }
    public void renamefile(String oldfilepth,String newfilename){
        File oldfile=new File(oldfilepth);
        int lastoccuranceofbackslash=oldfile.getPath().lastIndexOf("\\");
        int lastoccuranceofdot;
        File newfile;
        if(oldfile.isFile()){
            lastoccuranceofdot=oldfile.getPath().lastIndexOf(".");

            String oldfilename=oldfile.getAbsolutePath().substring(lastoccuranceofbackslash+1,lastoccuranceofdot);
            newfile=new File(oldfile.getPath().replace(oldfilename,newfilename));
        }
        else{

            String oldfilename=oldfile.getAbsolutePath().substring(lastoccuranceofbackslash+1);
            newfile=new File(oldfile.getPath().replace(oldfilename,newfilename));

        }


        if(oldfile.exists()){
            oldfile.renameTo(newfile);
        }else{
            System.out.println("File dosent exist");
        }
    }
    public void runfile(String path){
        File file=new File(path);
        if(file.exists()){
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void deleteDir(String path){
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deletefile(String path){
        File file=new File(path);
        file.delete();


    }
    public void createfile(String path) throws IOException {
        File file=new File(path);
        file.createNewFile();

    }
    public void createfolder(String path) throws IOException {
        File file=new File(path);
        file.mkdir();

    }
}
