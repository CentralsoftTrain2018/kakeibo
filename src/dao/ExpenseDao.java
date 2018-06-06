package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.ExpenseVo;

public class ExpenseDao {

    Connection con;

    private static final String ADD =
            "insert into expenses ( "
            + " expensekingaku "
            + " ,categoryid "
            + " ,expensename "
            + " ,expensedate "
            + " ,userid "
            + " ) "
            + " values ( "
            + "  ?,?,?,?,? ) ";

    private static final String UPDATE =
             "update expenses "
            + " set "
            + " expensekingaku "
            + " = ? "
            + " ,categoryid "
            + " = ? "
            + " ,expensename "
            + " = ? "
            + "where "
            + "  expenseid "
            + "   = ? ";

    private static final String DELETE =
            "delete from expenses "
            + " where "
            + " expenseid = ?";

    public ExpenseDao(Connection con) {
        this.con = con;
    }

    //-------------------------------------------------------
    // 会員登録
    public void addExpense(ExpenseVo ev) throws SQLException {
        PreparedStatement stmt = null;
        try{

            stmt = con.prepareStatement(ADD);

            stmt.setInt(1, ev.getExpenseKingaku());
            stmt.setInt(2,ev.getCategoryId());
            stmt.setString(3, ev.getExpenseName());
            stmt.setDate(4, ev.getExpenseDate());
            stmt.setString(5, ev.getUserId());



            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw ex;
        }
    }


    //-------------------------------------------------------
    // 会員取得
    public void updateExpense(ExpenseVo ev) throws SQLException {
        PreparedStatement stmt = null;
        try{

            /* Statementの作成 */
            stmt = con.prepareStatement(UPDATE);

            stmt.setInt(1, ev.getExpenseKingaku());
            stmt.setInt(2,ev.getCategoryId());
            stmt.setString(3, ev.getExpenseName());
            stmt.setInt(4, ev.getExpenseId());

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        }

        catch (SQLException e) {
            throw e;
        }
    }


    public void deleteExpense(ExpenseVo ev) throws SQLException {
        PreparedStatement stmt = null;

        try{

            /* Statementの作成 */
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, ev.getExpenseId());

            stmt.executeUpdate();

        }

        catch (SQLException e) {
            throw e;
        }
    }
}