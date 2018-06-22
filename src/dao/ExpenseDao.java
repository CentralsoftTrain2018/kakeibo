package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vo.CategoryVo;
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
            "categoryId " +
            ",categoryName " +
            "FROM " +
            "kakeibo.category " +
            "WHERE " +
            "user_userid = ? " +
            "AND useflag = 1;";

    private static final String GET_EXPENSES = "SELECT"
            + " expenseId "
            + " ,category_categoryId "
            + " ,expenseName "
            + " ,kingaku "
            + " FROM "
            + " expenses "
            + " WHERE "
            + " user_userId = ? "
            + " AND "
            + " expenseDate = ? ";

    private static final String GET_REGISTMONTH = "SELECT"
            + " registmonth "
            + " FROM "
            + " user "
            + " WHERE "
            + " userId = ? ";




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
     * カテゴリID, カテゴリ名を取得
     * @throws SQLException
     * @return CategoryVo型のList
     */
    public List<CategoryVo> getCategory(String userId) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_CATEGORY ); )
        {
            stmt.setString( 1, userId );
            rset = stmt.executeQuery();

            List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
            while ( rset.next() )
            {
                CategoryVo cv = new CategoryVo();
                cv.setCategoryid( rset.getInt( 1 ) );
                cv.setCategoryname( rset.getString( 2 ) );
                categoryList.add( cv );
            }
            return categoryList;
        }
        catch ( SQLException e )
        {
            throw e;
        }
    }

    public List<ExpenseVo> getExpenseOfDay( Date date, String userId ) throws SQLException
    {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( GET_EXPENSES ); )
        {
            stmt.setString( 1, userId );
            stmt.setDate( 2, date );

            rset = stmt.executeQuery();

            List<ExpenseVo> eList = new ArrayList<>();

            while(rset.next()) {
                ExpenseVo ev = new ExpenseVo();

                ev.setExpenseId(rset.getInt(1));
                ev.setCategoryId(rset.getInt(2));
                ev.setExpenseName(rset.getString(3));
                ev.setExpenseKingaku(rset.getInt(4));

                eList.add(ev);
            }

            return eList;
        }
        catch ( SQLException e )
        {
            throw e;
        }
    }

    public String getRegistMonth(String userId) throws SQLException {

        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( GET_REGISTMONTH ); )
        {
            stmt.setString( 1, userId );

            rset = stmt.executeQuery();

            String registMonth = null;

            rset.next();
            registMonth = rset.getString(1);

            return registMonth;
        }
        catch ( SQLException e )
        {
            throw e;
        }

    }
}