package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.ExpenseVo;

public class ExpenseDao {

    Connection con;

    private static final String ADD =
            "insert into expences ( "
            + " ,category "
            + " ,expensename "
            + " ,kingaku "
            + " ,userid "
            + " ,expensedate"
            + " ) "
            + " values ( "
            + "  ?,?,?,?,? ) ";

    private static final String UPDATE =
             "update expenses "
            + " set "
            + " ,category "
            + " = ?"
            + " ,expensename "
            + " = ?"
            + " ,kingaku "
            + " = ?"
            + "where "
            + "  expenseid "
            + "   =? ";

    private static final String DELETE =
            "delete from expenses "
            + " where "
            + " expenceid";

    public ExpenseDao(Connection con) {
        this.con = con;
    }

    //-------------------------------------------------------
    // 会員登録
    public void addExpense(ExpenseVo ev) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try{

            stmt = con.prepareStatement(ADD);

            stmt.setString(1, ev.getUserId());
            stmt.setString(2,ev.getCategoryName());
            stmt.setInt(3, ev.getExpenseKingaku());
            stmt.setString(4, ev.getUserId());
            stmt.setDate(5, ev.getExpenseDate());


            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw ex;
        }
        finally{

            if(stmt != null){
                stmt.close();
                stmt = null;
            }
            if(rset != null){
                rset.close();
                rset = null;
            }
        }
    }


    //-------------------------------------------------------
    // 会員取得
    public void updateExpense(ExpenseVo ev) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rset = null;

        try{

            /* Statementの作成 */
            stmt = con.prepareStatement(UPDATE);

            stmt.setString(1, ev.getUserId());
            stmt.setString(2,ev.getCategoryName());
            stmt.setInt(3, ev.getExpenseKingaku());
            stmt.setString(4, ev.getUserId());

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        }

        catch (SQLException e) {
            throw e;
        }
        finally{

            if(stmt != null){
                stmt.close();
                stmt = null;
            }
            if(rset != null){
                rset.close();
                rset = null;
            }
        }

    }


    public void deleteExpense() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try{

            /* Statementの作成 */
            stmt = con.prepareStatement(DELETE);

            /* ｓｑｌ実行 */
            rset = stmt.executeQuery();

        }

        catch (SQLException e) {
            throw e;
        }
        finally{

            if(stmt != null){
                stmt.close();
                stmt = null;
            }
            if(rset != null){
                rset.close();
                rset = null;
            }
        }
    }
}