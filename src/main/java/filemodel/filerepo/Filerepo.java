package filemodel.filerepo;
import filemodel.fileentity.Fileenti;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


    public class Filerepo implements AutoCloseable{
        private Connection connection;
        private PreparedStatement preparedStatement;

        public Filerepo() throws Exception{
            Class.forName ("oracle.jdbc.driver.OracleDriver");
            connection= DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:xe", "????", "????");
            connection.setAutoCommit (false);
        }

        public void insert(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("INSERT INTO ????(name ,path  ,lastdatemodified,datecreated,id,in_folder,fileformat) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setString (2,fileenti.getPath());
            preparedStatement.setDate (3,fileenti.getLastdatemodified());
            preparedStatement.setDate (4,fileenti.getDatecreated());
            preparedStatement.setLong (5,fileenti.getId());
            preparedStatement.setLong (6,fileenti.getIn_Folder());
            preparedStatement.setString (7,fileenti.getFormat());
            preparedStatement.executeUpdate ();
        }

        public void update (Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("UPDATE ????  SET name =?,lastdatemodified=? WHERE id=? ");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setDate (2,fileenti.getLastdatemodified());
            preparedStatement.setLong (3,fileenti.getId());
            preparedStatement.executeUpdate ();
        }

        public void delete(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("DELETE * FROM  ???? WHERE id=?");
            preparedStatement.setLong (1,fileenti.getId());
            preparedStatement.executeUpdate ();
        }
        public void createtable() throws Exception{
            preparedStatement=connection.prepareStatement ("CREATE table ??? (name varchar2(20),fileformat varchar2(10),path varchar2(40),lastdatemodified date,createddate date,id number )");
            preparedStatement.executeUpdate ();
        }

        public List<Fileenti> search(String name) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM ???? where name=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
               Fileenti fileenti=new Fileenti();
               fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("datecreated"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
               fileentiList.add(fileenti);
            }
            return fileentiList;
        }

        public List<Fileenti> open_folder(Fileenti Folder) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM ???? where folder_in=?");
            preparedStatement.setLong(1,Folder.getId());
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
                Fileenti fileenti=new Fileenti();
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("datecreated"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                fileentiList.add(fileenti);
            }
            return fileentiList;
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
