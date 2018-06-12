package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import vo.ExpenseVo;

public class ExpenseDao extends Dao
{

    private static final String ADD = "insert into expenses ( "
            + " kingaku "
            + " ,category_categoryid "
            + " ,expensename "
            + " ,expensedate "
            + " ,user_userid "
            + " ) "
            + " values ( "
            + "  ?,?,?,?,? ) ";

    private static final String UPDATE = "update expenses "
            + " set "
            + " kingaku "
            + " = ? "
            + " ,category_categoryid "
            + " = ? "
            + " ,expensename "
            + " = ? "
            + "where "
            + "  expenseid "
            + "   = ? ";

    private static final String DELETE = "delete from expenses "
            + " where "
            + " expenseid = ?";

    private static final String SELECT_ALL =
            "select " +
            "	* " +
            "from " +
            "	expenses " +
            "where " +
            "	user_userid = ? " +
            "	expensedate between '?' AND '?'";

    private static final String SELECT_CATEGORY =
            "SELECT " +
            "categoryId, categoryName " +
            "FROM " +
            "kakeibo.category " +
            "WHERE " +
            "useflag = 1;";

    public ExpenseDao( Connection con )
    {
        super( con );
    }

    /**
     * 支出登録
     * @param ev
     * @throws SQLException
     */
    public void addExpense( ExpenseVo ev ) throws SQLException
    {
        PreparedStatement stmt = null;
        try
        {

            stmt = con.prepareStatement( ADD );

            stmt.setInt( 1, ev.getExpenseKingaku() );
            stmt.setInt( 2, ev.getCategoryId() );
            stmt.setString( 3, ev.getExpenseName() );
            stmt.setDate( 4, ev.getExpenseDate() );
            stmt.setString( 5, ev.getUserId() );

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    /**
     * 支出編集
     * @param ev
     * @throws SQLException
     */
    public void updateExpense( ExpenseVo ev ) throws SQLException
    {
        PreparedStatement stmt = null;
        try
        {

            /* Statementの作成 */
            stmt = con.prepareStatement( UPDATE );

            stmt.setInt( 1, ev.getExpenseKingaku() );
            stmt.setInt( 2, ev.getCategoryId() );
            stmt.setString( 3, ev.getExpenseName() );
            stmt.setInt( 4, ev.getExpenseId() );

            /* ｓｑｌ実行 */
            stmt.executeUpdate();

        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

/**
 * 支出削除
 * @param ev
 * @throws SQLException
 */
    public void deleteExpense( ExpenseVo ev ) throws SQLException
    {
        PreparedStatement stmt = null;

        try
        {

            /* Statementの作成 */
            stmt = con.prepareStatement( DELETE );
            stmt.setInt( 1, ev.getExpenseId() );

            stmt.executeUpdate();

        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

    /**
     * 日ごとの支出合計をリストで取得
     * @param calendar
     * @param userId
     * @return
     * @throws SQLException
     */
    public ArrayList<ExpenseVo> getAllSumOfDay(Calendar calendar, String userId) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rset = null;

        Calendar lastDay = (Calendar)calendar.clone();
        lastDay.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        String startDayStr =
                calendar.get(Calendar.YEAR) + "-" +
                (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DATE);
        String endDayStr =
                lastDay.get(Calendar.YEAR) + "-" +
                (lastDay.get(Calendar.MONTH) + 1) + "-" +
                lastDay.get(Calendar.DATE);

        try {
            stmt = con.prepareStatement(SELECT_ALL);
            stmt.setString(1, userId);
            stmt.setString(2, startDayStr);
            stmt.setString(3, endDayStr);

            rset = stmt.executeQuery();

            ArrayList<ExpenseVo> expenseList = new ArrayList();
            while(rset.next()) {
                ExpenseVo ev = new ExpenseVo();
                ev.setExpenseId(rset.getInt(1));
            }
            return expenseList;
        }
        catch(SQLException ex) {
            throw ex;
        }
    }
    /**
     * カテゴリ取得
     */
    public void getCategory()
    {
        PreparedStatement stmt = null;

        try
        {

            /* Statementの作成 */
            stmt = con.prepareStatement( SELECT_CATEGORY );
//            stmt.setInt( 1, ev.getExpenseId() );

            stmt.executeUpdate();

        }

        catch ( SQLException e )
        {
            throw null;
        }
    }
}