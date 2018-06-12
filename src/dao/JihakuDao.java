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

    private static final String SELECT =
            "		select" +
            "			k.cname" +
            "			,k.sum1 - m.kingaku" +
            "		from" +
            "			mokuhyou m" +
            "			,(select" +
            "				c.categoryid as cid" +
            "				,c.categoryname as cname" +
            "				,sum(e.kingaku) as sum1" +
            "			from" +
            "				expenses e" +
            "				,category c" +
            "			where" +
            "				e.category_categoryid = c.categoryid" +
            "				AND e.expensedate between '2018-5-1' AND '2018-5-31'" +
            "				AND e.user_userid = ?"+
            "			group by" +
            "				c.categoryid" +
            "			) as k" +
            "		where" +
            "			m.category_categoryid = k.cid" +
            "			AND m.month like '2018/05'" +
            "			AND k.sum1 - m.kingaku > 0" +
            "		order by" +
            "			k.sum1 - m.kingaku desc;";

    private static final String GOUKEI =
            "			SELECT" +
            "			SUM(e.Kingaku)" +
            "			FROM" +
            "			expenses as e" +
            "			WHERE" +
            "			e.expenseDate BETWEEN '2018-5-01' AND '2018-5-31'" +
            "			AND e.user_userid = ?;";

    private static final String MOKUHYOU =
            "			SELECT" +
            "			SUM(m.Kingaku)" +
            "			FROM" +
            "			mokuhyou as m" +
            "			WHERE" +
            "			m.Month = '2018/05'" +
            "			AND m.user_userid = ?;";
    public JihakuDao( Connection con )
    {
        super( con );
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標
    public List<AdviceVo> JihakuAdvice( String date, String userId ) throws SQLException
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
                cv.setDifference( rset.getInt( 2 )  );
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
    public int JihakuGoukei( String month, String userId ) throws SQLException
    {

        //System.out.println( month.toString() );
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try
        {
            int goukei;

            /* Statementの作成 */
            stmt = con.prepareStatement( GOUKEI );
            //stmt.setString(1, date.toString().substring(7));
            //stmt.setString(2, date.toString().substring(7));
            stmt.setString( 1, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */

                rset.next();
                goukei=( rset.getInt( 1 ) );
            return goukei;
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
    public int  JihakuMokuhyou( String month, String userId ) throws SQLException
    {

        //System.out.println( month.toString() );
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try
        {
            int mokuhyou;

            /* Statementの作成 */
            stmt = con.prepareStatement( MOKUHYOU );
            //stmt.setString(1, date.toString().substring(7));
            //stmt.setString(2, date.toString().substring(7));
            stmt.setString( 1, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
                rset.next();
                mokuhyou=(rset.getInt( 1 ) );
            return mokuhyou;
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