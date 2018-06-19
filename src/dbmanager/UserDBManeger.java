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

            //IDが重複していない場合、会員登録
            while ( rdao.idCheck( rb ) )
            {
                rdao.kaiinInsert( rb );
            }
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

}
