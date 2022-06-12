package filemodel.fileservice;

import filemodel.fileentity.Fileenti;
import filemodel.filerepo.Filerepo;

import java.util.List;

public class Fileserv {
    private Fileserv () {
    }

    private static Fileserv usersserv = new Fileserv ();

    public static Fileserv getInstance () {
        return usersserv;
    }
    public  void createtable() throws Exception {
        try (Filerepo filerepo=new Filerepo()) {
            filerepo.createtable();
            filerepo.commit ();
        }


    }

    public void save (Fileenti fileenti) throws Exception {
        try (Filerepo filerepo=new Filerepo()) {
            filerepo.insert(fileenti);
            filerepo.commit ();

        }
    }

    public void edit (Fileenti fileenti) throws Exception{
        try (Filerepo filerepo=new Filerepo()){
            filerepo.update (fileenti);
            filerepo.commit ();
        }
    }

    public void remove(Fileenti fileenti) throws Exception{
        try (Filerepo filerepo=new Filerepo()){
            filerepo.delete(fileenti);
            filerepo.commit();
        }
    }
    public List<Fileenti> search(String name,Long id) throws Exception{
        List<Fileenti> Fileenti;
        try (Filerepo filerepo=new Filerepo()){
            Fileenti=filerepo.search(name,id);
        }
        return Fileenti;
    }
    public List<Fileenti> open_Folder(Long id) throws Exception{
        List<Fileenti> Fileenti;
        try (Filerepo filerepo=new Filerepo()){
            Fileenti=filerepo.open_folder(id);
        }
        return Fileenti;
    }
    public Fileenti open_Folder_path(String path) throws Exception{
        Fileenti Fileenti;
        try (Filerepo filerepo=new Filerepo()){
            Fileenti=filerepo.open_folder_path(path);
        }
        return Fileenti;
    }
    public Fileenti open_Folder_id(Long id) throws Exception{
        Fileenti Fileenti;
        try (Filerepo filerepo=new Filerepo()){
            Fileenti=filerepo.open_folder_id(id);
        }
        return Fileenti;
    }
    public List<Fileenti> Getdrives() throws Exception{
        List<Fileenti> Fileenti;
        try (Filerepo filerepo=new Filerepo()){
            Fileenti=filerepo.Getdrives();
        }
        return Fileenti;
    }
    public void uninstall()throws Exception{
        try (Filerepo filerepo=new Filerepo()){
           filerepo.droptable();
           filerepo.commit();
        }

    }

}


