package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.AdviceVo;

/**
* コナン機能のDao
*/
public class ConanDao extends Dao
{

    private static final String SELECT = "select " +
            " k.kcname " +
            " ,case " +
            "  when k.kmkingaku - sum(e.kingaku) is null then k.kmkingaku " +
            "        else k.kmkingaku - sum(e.kingaku) " +
            "  end as sagaku " +
            " from " +
            "  (select " +
            "   c.categoryid kccategoryid " +
            "   ,c.categoryname kcname " +
            "   ,m.month kmmonth " +
            "   ,m.user_userid kmuserid " +
            "   ,m.kingaku kmkingaku " +
            "  from " +
            "   kakeibo.mokuhyou m " +
            "   ,kakeibo.category c " +
            "  where " +
            "   m.category_categoryid = c.categoryid " +
            "   AND m.user_userid = ? " +
            "   AND m.month = ? ) as k " +
            " left join " +
            "  kakeibo.expenses e " +
            " on " +
            "  k.kccategoryid = e.category_categoryid " +
            "  AND e.user_userid = k.kmuserid " +
            "  AND DATE_FORMAT(e.expensedate, '%Y/%m') = ? " +
            " group by " +
            "  k.kccategoryid " +
            " order by " +
            "  sagaku desc;";

    private static final String GET_MAILADDRESS =
            "select " +
            "	email " +
            "from " +
            "	user " +
            "where " +
            "	userid = ?";

    public ConanDao( Connection con )
    {
        super( con );
    }

    /**
     * getAdvice
     * カテゴリ名、目標-支出合計
     * @param month 年月
     * @param userId ユーザーID
     * @return カテゴリごとの名前、目標-支出合計が格納されたAdviceVo型のリスト
     */

    public List<AdviceVo> getAdvice( String month, String userId ) throws SQLException
    {

        ResultSet rset = null;

        try (
                PreparedStatement stmt = con.prepareStatement( SELECT );
                )
        {
            List<AdviceVo> list = new ArrayList<AdviceVo>();

            /* Statementの作成 */
            stmt.setString( 1, userId );
            stmt.setString( 2, month );
            stmt.setString( 3, month );

            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                AdviceVo cv = new AdviceVo();

                cv.setCategoryName( rset.getString( 1 ) );
                cv.setDifference( rset.getInt( 2 ) );
                list.add( cv );
            }

            return list;
        }

        catch ( SQLException e )
        {
            throw e;
        }

    }

    public String getMailAddress(String userId) throws SQLException {
        ResultSet rset = null;

        try (
                PreparedStatement stmt = con.prepareStatement( GET_MAILADDRESS );
                )
        {
            /* Statementの作成 */
            stmt.setString( 1, userId );

            /* SQL実行 */
            rset = stmt.executeQuery();

            String mailAddress = "";
            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                mailAddress = rset.getString(1);
            }

            return mailAddress;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

}
