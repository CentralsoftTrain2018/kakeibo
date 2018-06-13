package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PoolConnection {
    public static Connection getConnection() throws SQLException
    {
       String user = "user";
       String pass = "user";
       String servername = "localhost:3306";
       String dbname = "kakeibo";

       try {
           Class.forName("com.mysql.jdbc.Driver");
       }
       catch(ClassNotFoundException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
       Connection c = DriverManager.getConnection(
                   "jdbc:mysql://"
                   + servername
                   + "/"
                   + dbname
                   + "?useUnicode=true&characterEncoding=utf8",
                   user,
                   pass);

       return c;
    }
}
