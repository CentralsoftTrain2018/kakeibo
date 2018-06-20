package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolConnection {
    /*プーリングなし
     * public static Connection getConnection() throws SQLException
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
    */
    public static Connection getConnection()
            throws SQLException
    {
        InitialContext context;
        DataSource ds = null;
        try
        {
            context = new InitialContext();
            ds = (DataSource) context.lookup( "java:comp/env/jdbc/myapp" );
        } catch (NamingException e)
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

        Connection con = ds.getConnection();

        return con;
    }
}
