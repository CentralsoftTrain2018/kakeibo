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
            "categoryName " +
            ",SUM(e.Kingaku)  - m.Kingaku " +
            "FROM " +
            "kakeibo.expenses e " +
            ",kakeibo.category c " +
            ",kakeibo.mokuhyou m " +
            "WHERE " +
            "DATE_FORMAT(e.expenseDate, '%Y/%m') = ? " +
            "AND m.Month = ? " +
            "AND e.category_categoryId = c.categoryId " +
            "AND e.user_userid = m.user_userid " +
            "AND e.user_userid = ? " +
            "AND c.categoryId = m.category_categoryId " +
            "GROUP BY e.category_categoryId " +
            "ORDER BY SUM(e.Kingaku)  - m.Kingaku DESC limit 3;";

    private static final String GOUKEI = "SELECT" +
            "			SUM(e.Kingaku)" +
            "			FROM" +
            "			expenses as e" +
            "			WHERE" +
            "           DATE_FORMAT(e.expenseDate, '%Y/%m') = ? " +
            "			AND e.user_userid = ?;";

    private static final String MOKUHYOU = "SELECT" +
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

    /**
     * ユーザーIDと年月に該当する
     * カテゴリ名・カテゴリごとの差額を取得(上位三件まで）
     * @param nengetsu
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<AdviceVo> JihakuAdvice( String nengetsu, String userId ) throws SQLException
    {

        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT ) )
        {
            List<AdviceVo> list = new ArrayList<AdviceVo>();

            stmt.setString( 1, nengetsu );
            stmt.setString( 2, nengetsu );
            stmt.setString( 3, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                AdviceVo av = new AdviceVo();

                av.setCategoryName( rset.getString( 1 ) );
                av.setDifference( rset.getInt( 2 ) );
                list.add( av );
            }
            return list;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }
/**
 * 月全体の支出合計を取得
 * @param nengetsu
 * @param userId
 * @return　月全体の支出合計
 * @throws SQLException
 */
    public int getSisyutuGoukei( String nengetsu, String userId ) throws SQLException
    {

        //System.out.println( month.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( GOUKEI ); )
        {
            int goukei;

            stmt.setString( 1, nengetsu );
            stmt.setString( 2, userId );
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
/**
 * その月全体の目標額を取得
 * @param nengetsu
 * @param userId
 * @return　月全体の目標額
 * @throws SQLException
 */
    public int getTsukiMokuhyou( String nengetsu, String userId ) throws SQLException
    {

        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( MOKUHYOU ) )
        {
            int mokuhyou;

            stmt.setString( 1, nengetsu );
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