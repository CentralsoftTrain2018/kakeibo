package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolConnection {
    public static Connection getConnection() throws SQLException
    {
        InitialContext context;
        DataSource ds =null;
        try
        {
            context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/myapp");
        }
        catch( NamingException e )
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

         Connection con = ds.getConnection();

        return con;
     }
}
