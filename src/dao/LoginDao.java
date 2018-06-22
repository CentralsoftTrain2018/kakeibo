package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends Dao
{

    public LoginDao( Connection con )
    {
        super( con );
    }

    final static String ID_PASS_CHECK = "select "
            + "userId "
            + "from user "
            + "where userId = ? "
            + "and Passward = ?;";

    final static String MOKUHYOU_CHECK = "select mokuhyouId " +
            "from mokuhyou " +
            "where user_userid = ? " +
            "limit 1 ;";

    /**
     *ID・パスワードの正しいユーザーが存在するか
     * @param lb
     * @return ログインできる true、ログインできない false
     * @throws SQLException
     */
    public boolean idPassCheck( String userId, String password ) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( ID_PASS_CHECK ); )
        {
            String result = null;

            stmt.setString( 1, userId );
            stmt.setString( 2, password );

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

    /**
     * ユーザーに目標が設定されているか
     * @param userId
     * @return 設定されている：true 設定されていない：false
     */
    public boolean hasMokuhyou( String userId ) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( MOKUHYOU_CHECK ); )
        {
            Integer result = null;

            stmt.setString( 1, userId );

            /* ｓｑｌ実行 */
            rset = stmt.executeQuery();

            while ( rset.next() )
            {
                result = (rset.getInt( 1 ));
            }

            //目標が存在しない場合false
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
