package Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.util.List;

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
//        File f = new File(dirpath);
//        if (f.exists()) {
//            String arr[] = f.list();
//            files_here = arr.length;
//            for (int i = 0; i < files_here; i++) {
//                File[] f1 = new File(dirpath).listFiles();
//                if (f1[i].isDirectory())
//                    result.add(f1[i]);
//                else
//                    result.put(arr[i].toString(),"File");
//            }
//            return result;
//        }
        return null;
    }
//    public HashMap<String, String> open_dir(String to_open_dir)throws IOException{
//        if (!current_loc.equals("")&&current_loc.charAt(current_loc.length()-1) == '\\'){
//            current_loc = current_loc + to_open_dir;
//            System.out.println(current_loc);
//
//        }
//
//        else{
//            current_loc = current_loc + "\\" + to_open_dir;
//            System.out.println(current_loc);
//        }
//        return scan_files(current_loc);
//    }
//    public HashMap<String, String> back_dir()throws IOException{
//        StringBuilder current_dir = new StringBuilder(current_loc);
//        int i = 0;
//        boolean deleted=false;
////        current_dir.deleteCharAt(i);
//        if(current_loc.equals("")){
//            System.out.println(current_loc);
//            return scan_files(current_loc);
//        }
//
//        for (i = current_loc.length()-1 ; current_loc.charAt(i) != '\\' ; i--){
//            deleted=true;
//            current_dir.deleteCharAt(i);
//        }
//        current_dir.deleteCharAt(i);
//        try {
//            if(current_dir.toString().charAt(current_dir.length()-1)==':'&&!deleted){
//                current_loc="";
//                System.out.println(current_loc);
//                return scan_files(current_loc);
//            }
//
//        }catch (Exception e){
//
//        }
//
//        try{
//            if(getClass().getProtectionDomain().getCodeSource().getLocation().toString().charAt(6)==current_dir.charAt(1)){
//                current_loc=current_dir.toString();
//                System.out.println(current_loc);
//                return scan_files(current_dir.toString()+"\\") ;
//
//            }
//
//        }
//        catch (Exception e){
//
//        }
//        current_loc=current_dir.toString();
//        System.out.println(current_loc);
//        return scan_files(current_loc);
//    }
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
