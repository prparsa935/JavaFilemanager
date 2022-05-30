package usermodel.userservice;

import usermodel.userent.UserEnt;
import usermodel.userrepo.UserRepo;

import java.util.List;

public class UserService {
    private UserService () {
    }

    private static UserService usersserv = new UserService ();

    public static UserService getInstance () {
        return usersserv;
    }
    public void setup() throws Exception {
        try (UserRepo personRepo = new UserRepo ()) {
            personRepo.createtabel ();
            personRepo.commit ();
        }


    }

    public void save (UserEnt usersenti) throws Exception {
        try (UserRepo personRepo = new UserRepo ()) {
            personRepo.insert (usersenti);
            personRepo.commit ();
        }
    }
    public void edit (UserEnt usersenti) throws Exception{
        try (UserRepo usersrepo=new UserRepo ()){
            usersrepo.update (usersenti);
            usersrepo.commit ();
        }
    }

    public void remove(UserEnt usersenti) throws Exception{
        try (UserRepo usersrepo=new UserRepo ()){
            usersrepo.delete (usersenti);
            usersrepo.commit();
        }
    }
    public UserEnt authenticate(String username,String password) throws Exception{
        UserEnt UserEntites;
        try (UserRepo usersrepo=new UserRepo ()){
            UserEntites=usersrepo.select(username,password);
        }
        return UserEntites;
    }

}