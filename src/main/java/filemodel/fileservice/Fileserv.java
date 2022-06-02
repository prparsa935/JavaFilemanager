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
    public List<Fileenti> report() throws Exception{
        List<Fileenti> UserEntites;
        try (Filerepo filerepo=new Filerepo()){
            UserEntites=filerepo.select();
        }
        return UserEntites;
    }

}


