package usermodel.userrepo;


import usermodel.userent.UserEnt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepo implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserRepo() throws Exception{
//        Class.forName ("oracle.jdbc.driver.OracleDriver");
        connection= DriverManager.getConnection ("jdbc:sqlite:Filemanager.sqlite");
        connection.setAutoCommit (false);
    }
    public void createtabel() throws SQLException {
        preparedStatement=connection.prepareStatement ("create table filemanageruser(id integer not null\n" +
                "        constraint table_name_pk\n" +
                "            primary key autoincrement,name varchar(45),username varchar(45) ,password varchar(45),email varchar(45))");
        preparedStatement.executeUpdate();

    }


    public void insert(UserEnt usersenti) throws Exception{
        preparedStatement=connection.prepareStatement ("INSERT INTO filemanageruser(id,name ,username ,password ,email) VALUES (?,?,?,?,?)");
        preparedStatement.setLong (1,usersenti.getId());
        preparedStatement.setString (2,usersenti.getName());
        preparedStatement.setString (3,usersenti.getUsername());
        preparedStatement.setString (4,usersenti.getPassword());
        preparedStatement.setString (5,usersenti.getEmail());
        preparedStatement.executeUpdate ();
    }

    public void update (UserEnt usersenti) throws Exception{
        preparedStatement=connection.prepareStatement ("UPDATE filemanageruser  SET id=? username =?, password=?,email=? WHERE id=? ");
        preparedStatement.setLong (1,usersenti.getId ());
        preparedStatement.setString (2,usersenti.getUsername ());
        preparedStatement.setString (3,usersenti.getPassword ());
        preparedStatement.setString (4,usersenti.getEmail ());
        preparedStatement.setString (5,usersenti.getName ());
        preparedStatement.executeUpdate ();
    }

    public void delete(UserEnt usersenti) throws Exception{
        preparedStatement=connection.prepareStatement ("DELETE * FROM  filemanageruser WHERE name=?");
        preparedStatement.setString (1,usersenti.getName());
        preparedStatement.executeUpdate ();
    }

    public UserEnt select(String username,String password) throws Exception{
        preparedStatement=connection.prepareStatement ("SELECT * FROM filemanageruser where username=? and password=?");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        UserEnt UserEn=new UserEnt();

        if(resultSet.next ()){
            UserEn.setId (resultSet.getLong ("id"));
            UserEn.setName (resultSet.getString ("name"));
            UserEn.setUsername (resultSet.getString ("username"));
            UserEn.setPassword (resultSet.getString ("password"));
            UserEn.setEmail (resultSet.getString ("email"));
            return UserEn;

        }
        else
            return null;

    }
    public void commit() throws Exception{
        connection.commit ();
    }
    public void rollback() throws Exception{
        connection.rollback ();
    }
    public void close() throws Exception{
        preparedStatement.close ();
        connection.close ();

    }

}