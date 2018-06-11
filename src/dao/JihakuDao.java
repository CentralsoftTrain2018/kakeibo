package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.AdviceVo;

public class JihakuDao extends Dao
{

    private static final String SELECT = "            SELECT" +
            "            c.categoryName" +
            "            ,m.Kingaku - SUM(e.Kingaku)" +
            "            FROM" +
            "            expenses as e " +
            "            ,category as c " +
            "            ,mokuhyou as m " +
            "            WHERE " +
            "            e.expenseDate BETWEEN '2018-5-01' AND '2018-5-31'" +
            "            AND e.category_categoryId = c.categoryId" +
            "            AND e.user_userid = m.user_userid " +
            "            AND c.categoryId = m.category_categoryId" +
            "			 AND e.user_userid = ?" +
            "            GROUP BY e.category_categoryId" +
            "			HAVING SUM(m.Kingaku) - SUM(e.Kingaku) < 0" +
            "            ORDER BY SUM(m.Kingaku) / Count(m.Kingaku) - SUM(e.Kingaku) ASC;";

    public JihakuDao( Connection con )
    {
        super( con );
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標
    public List<AdviceVo> JihakuAdvice( Date date, String userId ) throws SQLException
    {

        System.out.println( date.toString() );
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try
        {
            List<AdviceVo> list = new ArrayList<AdviceVo>();

            /* Statementの作成 */
            stmt = con.prepareStatement( SELECT );
            //stmt.setString(1, date.toString().substring(7));
            //stmt.setString(2, date.toString().substring(7));
            stmt.setString( 1, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                AdviceVo cv = new AdviceVo();

                cv.setCategoryName( rset.getString( 1 ) );
                cv.setDifference( rset.getInt( 2 ) * -1 );
                list.add( cv );
            }

            return list;
        }

        catch ( SQLException e )
        {
            throw e;
        } finally
        {

            if ( stmt != null )
            {
                stmt.close();
                stmt = null;
            }
            if ( rset != null )
            {
                rset.close();
                rset = null;
            }
        }

    }
}