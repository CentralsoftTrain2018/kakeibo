package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.AdviceVo;

public class JihakuDao extends Dao
{

    private static final String SELECT = "select " +
            "	k.cname " +
            "	,k.sum1 - m.kingaku " +
            "from " +
            "	mokuhyou m " +
            "	,(select " +
            "		c.categoryid as cid " +
            "		,c.categoryname as cname " +
            "		,sum(e.kingaku) as sum1 " +
            "        ,e.user_userid as euserid " +
            "	from " +
            "		expenses e " +
            "		,category c " +
            "	where " +
            "		e.category_categoryid = c.categoryid " +
            "        AND e.user_userid = ? " +
            "		AND e.expensedate between ? AND ? " +
            "	group by " +
            "		c.categoryid " +
            "	) as k " +
            "where " +
            "	m.category_categoryid = k.cid " +
            "    AND k.euserid = m.user_userid " +
            "	AND m.month like ? " +
            "	AND k.sum1 - m.kingaku > 0 " +
            "order by " +
            "	k.sum1 - m.kingaku desc limit 3;";

    private static final String GOUKEI = "			SELECT" +
            "			SUM(e.Kingaku)" +
            "			FROM" +
            "			expenses as e" +
            "			WHERE" +
            "			e.expenseDate BETWEEN ? AND ?" +
            "			AND e.user_userid = ?;";

    private static final String MOKUHYOU = "			SELECT" +
            "			SUM(m.Kingaku)" +
            "			FROM" +
            "			mokuhyou as m" +
            "			WHERE" +
            "			m.Month = ?" +
            "			AND m.user_userid = ?;";

    public JihakuDao( Connection con )
    {
        super( con );
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標
    public List<AdviceVo> JihakuAdvice( String mokuhyou, String date, String userId ) throws SQLException
    {

        System.out.println( date.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT ) )
        {
            List<AdviceVo> list = new ArrayList<AdviceVo>();

            //stmt.setString(1, date.toString().substring(7));
            //stmt.setString(2, date.toString().substring(7));
            stmt.setString( 1, userId );
            stmt.setString( 2, date + "-1" );
            stmt.setString( 3, date + "-31" );
            stmt.setString( 4, mokuhyou );
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

    public int JihakuGoukei( String month, String userId ) throws SQLException
    {

        //System.out.println( month.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( GOUKEI ); )
        {
            int goukei;

            //stmt.setString(1, date.toString().substring(7));
            //stmt.setString(2, date.toString().substring(7));
            stmt.setString( 1, month + "-1" );
            stmt.setString( 2, month + "-31" );
            stmt.setString( 3, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */

            rset.next();
            goukei = (rset.getInt( 1 ));
            return goukei;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

    public int JihakuMokuhyou( String month, String userId ) throws SQLException
    {

        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( MOKUHYOU ) )
        {
            int mokuhyou;

            stmt.setString( 1, month );
            stmt.setString( 2, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            rset.next();
            mokuhyou = (rset.getInt( 1 ));
            return mokuhyou;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }
}