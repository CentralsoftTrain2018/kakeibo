package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import bean.RegistBean;
import dao.RegistDao;

public class UserDBManeger
{
    public static void passRegistDara( RegistBean rb )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            RegistDao rdao = new RegistDao( con );
            rdao.kaiinInsert(rb);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

}
