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
public class ConanDao extends Dao{

    private static final String SELECT = "SELECT " +
            "categoryName " +
            ",m.Kingaku - SUM(e.Kingaku) "+
            "FROM " +
            "kakeibo.expenses e " +
            ",kakeibo.category c " +
            ",kakeibo.mokuhyou m " +
            "WHERE " +
            "DATE_FORMAT(e.expenseDate, '%Y%m') = ? " +
            "AND DATE_FORMAT(m.Month, '%Y%m') = ? " +
            "AND e.category_categoryId = c.categoryId " +
            "AND e.user_userid = m.user_userid " +
            "AND e.user_userid = ? " +
            "AND c.categoryId = m.category_categoryId " +
            "GROUP BY e.category_categoryId "+
            "ORDER BY m.Kingaku - e.Kingaku DESC;";

    public ConanDao(Connection con) {
        super(con);
    }

    /**
     * getAdvice
     * カテゴリ名、目標-支出合計
     * @param month 年月
     * @param userId ユーザーID
     * @return カテゴリごとの名前、目標-支出合計が格納されたAdviceVo型のリスト
     */

    public List<AdviceVo> getAdvice(String month, String userId) throws SQLException
    {

        ResultSet rset = null;

        try(
                PreparedStatement stmt = con.prepareStatement(SELECT);
            )
            {
            List<AdviceVo> list = new ArrayList<AdviceVo>();

            String testMonth = "201805";
            /* Statementの作成 */
            stmt.setString(1, testMonth );
            stmt.setString(2, month);
            stmt.setString(3, userId);

            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while (rset.next())
            {
                AdviceVo cv = new AdviceVo();

                cv.setCategoryName(rset.getString(1));
                cv.setDifference(rset.getInt(2));
                list.add(cv);
            }

            return list;
        }

        catch (SQLException e)
        {
            throw e;
        }

    }

}
