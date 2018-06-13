package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BunsekiVo;

public class BunsekiDao extends Dao{

    private static final String SELECT = "SELECT " +
            "categoryName " +
            ",m.Kingaku - SUM(e.Kingaku) "+
            ",SUM(e.Kingaku) " +
            ",m.Kingaku "+
            "FROM " +
            "kakeibo.expenses e " +
            ",kakeibo.category c " +
            ",kakeibo.mokuhyou m " +
            "WHERE " +
            "DATE_FORMAT(e.expenseDate, '%Y%m') = ? " +
            "AND m.Month = ? " +
            "AND e.category_categoryId = c.categoryId " +
            "AND e.user_userid = m.user_userid " +
            "AND e.user_userid = ? " +
            "AND c.categoryId = m.category_categoryId " +
            "GROUP BY e.category_categoryId " +
            "ORDER BY e.category_categoryId ;";

    public BunsekiDao( Connection con )
    {
        super( con );
    }

    public List<BunsekiVo> getBunseki( String month,String userId ) throws SQLException
    {

        ResultSet rset = null;

        try (
                PreparedStatement stmt = con.prepareStatement( SELECT );
                )
        {
            List<BunsekiVo> list = new ArrayList<BunsekiVo>();

            String[] YearAndMonth = month.split("/");
            String expensesMonth=YearAndMonth[0]+YearAndMonth[1];

            //String testMonth = "201805";
            /* Statementの作成 */
            stmt.setString( 1, expensesMonth );
            stmt.setString( 2, month );
            stmt.setString( 3, userId );

            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                BunsekiVo bv = new BunsekiVo();

                bv.setCategoryName( rset.getString( 1 ) );
                bv.setDifference( rset.getInt( 2 ) );
                bv.setSumSpending( rset.getInt( 3 ) );
                bv.setMokuhyouKingaku( rset.getInt( 4 ) );
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
