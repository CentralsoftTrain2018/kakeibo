package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BunsekiVo;

public class BunsekiDao extends Dao
{

    private static final String SELECT = "select" +
            " k.kcname" +
            "    ,k.kmkingaku" +
            " ,case " +
            "  when k.kmkingaku - sum(e.kingaku) is null then k.kmkingaku" +
            "        else k.kmkingaku - sum(e.kingaku)" +
            "  end" +
            " ,case" +
            "  when sum(e.kingaku) is null then 0" +
            "        else sum(e.kingaku)" +
            "        end " +
            " ,k.kccolor " +
            " from" +
            "  (select" +
            "   c.categoryid kccategoryid" +
            "   ,c.categoryname kcname" +
            "   ,c.color kccolor" +
            "   ,m.month kmmonth" +
            "   ,m.user_userid kmuserid" +
            "   ,m.kingaku kmkingaku" +
            "  from" +
            "   kakeibo.mokuhyou m" +
            "   ,kakeibo.category c" +
            "  where" +
            "   m.category_categoryid = c.categoryid" +
            "   AND m.user_userid = ? " +
            "   AND m.month = ? ) as k" +
            " left join" +
            "  kakeibo.expenses e " +
            " on" +
            "  k.kccategoryid = e.category_categoryid " +
            "  AND e.user_userid = k.kmuserid " +
            "  AND DATE_FORMAT(e.expensedate, '%Y/%m') = ? " +
            " group by " +
            "  k.kccategoryid " +
            " order by " +
            "  k.kccategoryid;";

    public BunsekiDao( Connection con )
    {
        super( con );
    }

    public List<BunsekiVo> getBunseki( String nengetsu, String userId ) throws SQLException
    {

        ResultSet rset = null;

        try (
                PreparedStatement stmt = con.prepareStatement( SELECT ); )
        {
            List<BunsekiVo> list = new ArrayList<BunsekiVo>();

            /* Statementの作成 */
            stmt.setString( 1, userId );
            stmt.setString( 2, nengetsu );
            stmt.setString( 3, nengetsu );

            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                BunsekiVo bv = new BunsekiVo();

                bv.setCategoryName( rset.getString( 1 ) );
                bv.setMokuhyouKingaku( rset.getInt( 2 ) );
                bv.setDifference( rset.getInt( 3 ) );
                bv.setSumSpending( rset.getInt( 4 ) );
                bv.setColor( rset.getString( 5 ) );
                list.add( bv );
            }

            return list;
        }

        catch ( SQLException e )
        {
            throw e;
        }

    }
}
