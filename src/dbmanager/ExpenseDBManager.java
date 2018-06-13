package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.BungyDao;
import dao.ExpenseDao;
import vo.BungyVo;
import vo.CategoryVo;
import vo.ExpenseVo;

public class ExpenseDBManager
{
    public static void addExpense( ExpenseVo ev )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            ExpenseDao edao = new ExpenseDao( con );
            edao.addExpense( ev );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 支出編集
     * @param ev
     */
    public static void updateExpense( ExpenseVo ev )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            ExpenseDao edao = new ExpenseDao( con );
            edao.updateExpense( ev );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 支出削除
     * @param ev
     */
    public static void deleteExpense( ExpenseVo ev )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            ExpenseDao edao = new ExpenseDao( con );
            edao.deleteExpense( ev );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 目標、支出取得
     * @param userid
     * @param month
     * @return
     */
    public static BungyVo getMokuhyouAndExpenses( String userid, String month )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            BungyDao bdao = new BungyDao( con );
            BungyVo bv = bdao.getMokuhyouAndExpenses( userid, month );

            //BungyVo bv = new BungyVo();
            //bv.setMokuhyou(10000);
            //bv.setTotalexpenses(5000);

            return bv;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

    /**
     *
     * @param calendar
     * @param userId
     * @return
     */
    public static ArrayList<ExpenseVo> getAllSumOfDay( Calendar calendar, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            ExpenseDao edao = new ExpenseDao( con );
            ArrayList<ExpenseVo> expenseList = edao.getAllSumOfDay( calendar, userId );
            return expenseList;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * カテゴリ取得
     * @return List<CategoryVo>
     */
    public static List<CategoryVo> getCategory()
    {
        try (
                Connection con = PoolConnection.getConnection();
                )
        {
            ExpenseDao edao = new ExpenseDao( con );
            List<CategoryVo> categoryList = edao.getCategory();
            return categoryList;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
