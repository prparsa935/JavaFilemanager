package filemodel.filerepo;
import filemodel.fileentity.Fileenti;

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
            preparedStatement=connection.prepareStatement ("INSERT INTO ????(name ,path ,size ,lastdatemodified,datecreated,id,infolder) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setString (2,fileenti.getPath());
            preparedStatement.setDouble (3,fileenti.getSize());
            preparedStatement.setDate (4,fileenti.getLastdatemodified());
            preparedStatement.setDate (5,fileenti.getDatecreated());
            preparedStatement.setLong (6,fileenti.getId());
            preparedStatement.setLong (7,fileenti.getIn_Folder());
            preparedStatement.executeUpdate ();
        }

        public void update (Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("UPDATE ????  SET name =?, password=?,email=?, WHERE id=? ");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setString (2,fileenti.getPath());
            preparedStatement.setDouble (3,fileenti.getSize());
            preparedStatement.setDate (4,fileenti.getLastdatemodified());
            preparedStatement.setDate (5,fileenti.getDatecreated());
            preparedStatement.setLong (6,fileenti.getId());
            preparedStatement.setLong (7,fileenti.getIn_Folder());
            preparedStatement.executeUpdate ();
        }

        public void delete(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("DELETE * FROM  ???? WHERE id=?");
            preparedStatement.setLong (1,fileenti.getId());
            preparedStatement.executeUpdate ();
        }
        public void createtable(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("CREATE table ??? (name varchar2(20),path varchar2(40),size number,lastdatemodified date,createddate date,id number )");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setString (2,fileenti.getPath());
            preparedStatement.setDouble (3,fileenti.getSize());
            preparedStatement.setDate (4,fileenti.getLastdatemodified());
            preparedStatement.setDate (5,fileenti.getDatecreated());
            preparedStatement.setLong (6,fileenti.getId());
            preparedStatement.executeUpdate ();
        }


        public List<Fileenti> select() throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM ????");
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
               Fileenti fileenti=new Fileenti();
               fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setSize (resultSet.getDouble ("size"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("datecreated"));
                fileenti.setId(resultSet.getLong("id"));
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
