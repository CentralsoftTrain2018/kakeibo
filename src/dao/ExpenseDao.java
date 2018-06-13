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

    private static final String SELECT_SUM = "select " +
            "	expensedate, " +
            "	sum(kingaku) " +
            "from " +
            "	expenses " +
            "where " +
            "	user_userid = ? " +
            "AND	expensedate between ? AND ? " +
            "group by " +
            "	expensedate " +
            "order by " +
            "	expensedate asc";

    private static final String SELECT_CATEGORY = "SELECT " +
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
        try ( PreparedStatement stmt = con.prepareStatement( ADD ); )
        {

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
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE ); )
        {

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

        try ( PreparedStatement stmt = con.prepareStatement( DELETE ); )
        {

            /* Statementの作成 */
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
    public ArrayList<ExpenseVo> getAllSumOfDay( Calendar calendar, String userId ) throws SQLException
    {
        ResultSet rset = null;

        Calendar lastDay = ( Calendar ) calendar.clone();
        lastDay.set( Calendar.DATE, calendar.getActualMaximum( Calendar.DATE ) );
        String startDayStr = calendar.get( Calendar.YEAR ) + "-" +
                (calendar.get( Calendar.MONTH ) + 1) + "-" +
                calendar.get( Calendar.DATE );
        String endDayStr = lastDay.get( Calendar.YEAR ) + "-" +
                (lastDay.get( Calendar.MONTH ) + 1) + "-" +
                lastDay.get( Calendar.DATE );

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_SUM ); )
        {
            stmt.setString( 1, userId );
            stmt.setString( 2, startDayStr );
            stmt.setString( 3, endDayStr );

            rset = stmt.executeQuery();

            ArrayList<ExpenseVo> expenseList = new ArrayList<ExpenseVo>();
            while ( rset.next() )
            {
                ExpenseVo ev = new ExpenseVo();
                ev.setExpenseDate( rset.getDate( 1 ) );
                ev.setExpenseKingaku( rset.getInt( 2 ) );
                expenseList.add( ev );
            }
            return expenseList;
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    /**
     * カテゴリ取得
     */
    public void getCategory()
    {

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_CATEGORY ); )
        {

            /* Statementの作成 */

            // stmt.setInt( 1, ev.getExpenseId() );

            stmt.executeUpdate();

        }

        catch ( SQLException e )
        {
            throw null;
        }
    }
}