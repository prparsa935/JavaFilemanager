package Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    public static String current_loc = "";
    private int files_here;

    //getter setter
//    public int getN() {
//        return files_here;
//    }

    //scan operation
    public HashMap<String,String> scan_files(String dirpath)throws IOException {
        HashMap<String,String> result = new HashMap<>();
        if (dirpath == null || dirpath.length() < 3){
            File[] drives = File.listRoots();
            if (drives != null && drives.length > 0) {
                for (File aDrive : drives) {
                    result.put(aDrive.toString(),"drive");
                }
            }
            return result;
        }
        File f = new File(dirpath);
        if (f.exists()) {
            String arr[] = f.list();
            files_here = arr.length;
            for (int i = 0; i < files_here; i++) {
                File[] f1 = new File(dirpath).listFiles();
                if (f1[i].isDirectory())
                    result.put(arr[i].toString(),"DIR");
                else
                    result.put(arr[i].toString(),"File");
            }
            return result;
        }
        return null;
    }
    public HashMap<String, String> open_dir(String to_open_dir)throws IOException{
        if (!current_loc.equals("")&&current_loc.charAt(current_loc.length()-1) == '\\'){
            current_loc = current_loc + to_open_dir;
            System.out.println(current_loc);

        }

        else{
            current_loc = current_loc + "\\" + to_open_dir;
            System.out.println(current_loc);
        }
        return scan_files(current_loc);
    }
//    public String back_dir()throws IOException{
//        StringBuilder current_dir = new StringBuilder(current_loc);
//        int i = 0;
//        //current_dir.deleteCharAt(i);
//        for (i = current_loc.length()-1 ; current_loc.charAt(i) != '\\' ; i--){
//            current_dir.deleteCharAt(i);
//        }
//        current_dir.deleteCharAt(i);
//        current_loc = current_dir.toString();
//        return scan_files(current_loc);
//    }
}
