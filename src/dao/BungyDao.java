package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.BungyVo;

public class BungyDao extends Dao
{

    private static final String GETDATA = "SELECT "
            + " totalexpenses ,"
            + " mokuhyou "
            + " FROM "
            + " history "
            + " WHERE "
            + " userid "
            + " = ? "
            + " and "
            + " month "
            + " = ? ";

    public BungyDao( Connection con )
    {
        super( con );
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標

    public BungyVo getMokuhyouAndExpenses( String userid, String month ) throws SQLException
    {

        try (
                PreparedStatement stmt = con.prepareStatement( GETDATA ); )
        {
            ResultSet rset = null;
            BungyVo bv = new BungyVo();

            /* Statementの作成 */

            stmt.setString( 1, userid );
            stmt.setString( 2, month );

            /* SQL実行 */

            rset = stmt.executeQuery();

            while ( rset.next() )

            {
                bv.setTotalexpenses( rset.getInt( 1 ) );
                bv.setMokuhyou( rset.getInt( 2 ) );
            }



            //github.com/CentralsoftTrain2018/kakeibo

            /* 取得したデータを表示します。 */
            return bv;
        }

    }

}
