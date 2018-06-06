package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.ConanVo;

public class ConanDao {
    Connection con;

    private static final String SELECT = "SELECT " +
            "categoryName " +
            ",SUM(e.Kingaku) " +
            ",m.Kingaku " +
            "FROM " +
            "kakeibo.expenses e " +
            ",kakeibo.category c" +
            ",kakeibo.mokuhyou m" +
            "WHERE " +
            "e.expenseDate BETWEEN '2018-05-01' AND '2018-05-31' " +
            "AND e.category_categoryId = c.categoryId " +
            "AND e.user_userid = m.user_userid " +
            "AND c.categoryId = m.category_categoryId " +
            "GROUP BY e.category_categoryId;";

    public ConanDao(Connection con) {
        this.con = con;
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標
    public List<ConanVo> advice() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            List<ConanVo> list = new ArrayList<ConanVo>();

            /* Statementの作成 */
            stmt = con.prepareStatement(SELECT);
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */
            while (rset.next()) {
                ConanVo cv = new ConanVo();

                cv.setCategoryName(rset.getString(1));
                cv.setSumSpending(rset.getInt(2));
                cv.setMokuhyouKingaku(rset.getInt(3));
                list.add(cv);
            }

            return list;
        }

        catch (SQLException e) {
            throw e;
        } finally {

            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (rset != null) {
                rset.close();
                rset = null;
            }
        }

    }

}
