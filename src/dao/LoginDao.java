package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;

public class LoginDao extends Dao
{

    public LoginDao( Connection con )
    {
        super( con );
    }

    final static String ID_PASS_CHECK = "select "
            + "userId "
            + "from "
            + "user "
            + "where "
            + "userId = ? "
            + "and Passward = ?;";

    /**
     *ID・パスワードの正しいユーザーが存在するか
     * @param lb
     * @return ログインできる true、ログインできない false
     * @throws SQLException
     */
    public boolean idPassCheck( LoginBean lb ) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( ID_PASS_CHECK ); )
        {
            String result = null;

            stmt.setString( 1, lb.getUserId() );
            stmt.setString( 2, lb.getPassword() );

            /* ｓｑｌ実行 */
            rset = stmt.executeQuery();

            while ( rset.next() )
            {
                result = (rset.getString( 1 ));
            }

            //ユーザーが存在しない場合false
            if ( result == null )
            {
                return false;
            } else
            {
                return true;
            }
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

}
