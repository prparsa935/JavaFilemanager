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
            connection= DriverManager.getConnection ("jdbc:sqlite:Filemanager.sqlite");
            connection.setAutoCommit (false);
        }

        public void insert(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("INSERT INTO files(name ,path  ,lastdatemodified,createddate,in_folder,fileformat) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setString (2,fileenti.getPath());
            preparedStatement.setDate (3,fileenti.getLastdatemodified());
            preparedStatement.setDate (4,fileenti.getDatecreated());
            preparedStatement.setLong (5,fileenti.getIn_Folder());
            preparedStatement.setString (6,fileenti.getFormat());
            preparedStatement.executeUpdate ();
        }

        public void update (Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("UPDATE files  SET name =?,lastdatemodified=? WHERE id=? ");
            preparedStatement.setString (1,fileenti.getName());
            preparedStatement.setDate (2,fileenti.getLastdatemodified());
            preparedStatement.setLong (3,fileenti.getId());
            preparedStatement.executeUpdate ();
        }

        public void delete(Fileenti fileenti) throws Exception{
            preparedStatement=connection.prepareStatement ("DELETE FROM  files WHERE id=?");
            preparedStatement.setLong (1,fileenti.getId());
            preparedStatement.executeUpdate ();
        }
        public void createtable() throws Exception{
            preparedStatement=connection.prepareStatement ("CREATE table files (name varchar2(20),fileformat varchar2(10),path varchar2(40),lastdatemodified date,createddate date,id integer\n" +
                    "        constraint file_pk\n" +
                    "            primary key autoincrement,in_folder integer )");
            preparedStatement.executeUpdate ();
        }

        public List<Fileenti> search(String name,Long id) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM files where name LIKE ? and in_folder=?");
            preparedStatement.setString(1,'%'+name+'%');
            preparedStatement.setLong(2,id);
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
                Fileenti fileenti=new Fileenti();
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("createddate"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                fileentiList.add(fileenti);
            }
            return fileentiList;
        }

        public List<Fileenti> open_folder(Long id) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM files where in_folder=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
                Fileenti fileenti=new Fileenti();
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("createddate"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                fileentiList.add(fileenti);
            }
            return fileentiList;
        }
        public Fileenti open_folder_path(String path) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM files where path=?");
            preparedStatement.setString(1,path);
            ResultSet resultSet=preparedStatement.executeQuery ();
            while(resultSet.next()){
                Fileenti fileenti=new Fileenti();
//                System.out.println(resultSet.getString("path"));
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("createddate"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                return fileenti;

            }
            return null;

        }
        public Fileenti open_folder_id(Long id) throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM files where id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery ();
            while(resultSet.next()){
                Fileenti fileenti=new Fileenti();
//                System.out.println(resultSet.getString("path"));
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("createddate"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                return fileenti;

            }
            return null;

        }
        public List<Fileenti> Getdrives() throws Exception{
            preparedStatement=connection.prepareStatement ("SELECT * FROM files where fileformat=?");
            preparedStatement.setString(1,"Drive");
            ResultSet resultSet=preparedStatement.executeQuery ();
            List<Fileenti> fileentiList=new ArrayList <> ();
            while (resultSet.next ()){
                Fileenti fileenti=new Fileenti();
                fileenti.setName (resultSet.getString ("name"));
                fileenti.setPath(resultSet.getString ("path"));
                fileenti.setLastdatemodified (resultSet.getDate ("lastdatemodified"));
                fileenti.setDatecreated(resultSet.getDate("createddate"));
                fileenti.setId(resultSet.getLong("id"));
                fileenti.setIn_Folder(resultSet.getLong("in_folder"));
                fileenti.setFormat(resultSet.getString("fileformat"));
                fileentiList.add(fileenti);
            }
            return fileentiList;
        }
        public void droptable() throws Exception{
            preparedStatement=connection.prepareStatement ("Drop Table files");
            preparedStatement.executeUpdate();

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
