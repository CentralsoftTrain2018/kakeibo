package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.RegistVo;

public class RegistDao extends Dao
{
    public RegistDao( Connection con )
    {
        super( con );
    }

    final static String INSERT = "INSERT INTO " +
            "user " +
            "( userId " +
            ",Passward " +
            ",Email " +
            ",Income " +
            ",registMonth ) " +
            "VALUES (?, ?, ?, ?, ?);";

    final static String ID_CHECK = "select userId from user where userId = ?;";

    /**
     * 会員登録
     * @param rb 登録する会員情報
     */
    public void kaiinInsert( RegistVo rv ) throws SQLException
    {
        try ( PreparedStatement stmt = con.prepareStatement( INSERT ); )
        {

            stmt.setString( 1, rv.getUserId() );
            stmt.setString( 2, rv.getPassword() );
            stmt.setString( 3, rv.getMail() );
            stmt.setInt( 4, rv.getIncome() );
            stmt.setString(5, rv.getRegistMonth());

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    /**
     * userIDの重複チェック
     * @param rb
     * @return 重複していない true、重複している false
     * @throws SQLException
     */
    public boolean idCheck( String userId ) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( ID_CHECK ); )
        {
            String result = null;

            stmt.setString( 1, userId );

            /* ｓｑｌ実行 */
            rset = stmt.executeQuery();

            while ( rset.next() )
            {
                result = (rset.getString( 1 ));
            }

            //IDが存在していた場合false
            if ( result != null )
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
