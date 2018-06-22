package dbmanager;

import java.sql.Connection;
import java.sql.Date;
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
    public static BungyVo makeBungyBean( String userid, String month )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            BungyDao bdao = new BungyDao( con );
            BungyVo bv = new BungyVo();

            int totalexpenses = bdao.getSisyutuGoukei(month, userid);
            int mokuhyou = bdao.getTsukiMokuhyou(month, userid);
            String registMonth = bdao.getRegistMonth(userid);

            bv.setTotalexpenses(totalexpenses);
            bv.setMokuhyou(mokuhyou);
            bv.setRegistMonth(registMonth);

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
    public static List<CategoryVo> getCategory(String userId)
    {
        try (
                Connection con = PoolConnection.getConnection();
                )
        {
            ExpenseDao edao = new ExpenseDao( con );
            List<CategoryVo> categoryList = edao.getCategory(userId);
            return categoryList;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static List<ExpenseVo> getExpenseOfDay(Date date, String userId) {

        try (
                Connection con = PoolConnection.getConnection();
                )
        {
            ExpenseDao edao = new ExpenseDao( con );

            List<ExpenseVo> eList = edao.getExpenseOfDay(date, userId);

            return eList;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static String getRegistMonth(String userId) {

        try (
                Connection con = PoolConnection.getConnection();
                )
        {
            ExpenseDao edao = new ExpenseDao( con );

            String registMonth = edao.getRegistMonth(userId);

            return registMonth;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }
}
