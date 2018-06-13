package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.BungyBean;
import bean.ExpenseBean;
import dbmanager.ExpenseDBManager;
import vo.BungyVo;
import vo.CategoryVo;
import vo.ExpenseVo;

public class ExpenseService
{
    public static void addExpense( int kingaku, int categoryId, String expenseName, String userId )
    {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseKingaku( kingaku );
        ev.setCategoryId( categoryId );
        ev.setExpenseName( expenseName );
        Date expenseDate = new Date( new java.util.Date().getTime() );
        ev.setExpenseDate( expenseDate );
        ev.setUserId( userId );
        ExpenseDBManager.addExpense( ev );
    }

    public static void updateExpense( int expenseId, int kingaku, int categoryId, String expenseName )
    {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId( expenseId );
        ev.setExpenseKingaku( kingaku );
        ev.setCategoryId( categoryId );
        ev.setExpenseName( expenseName );
        ExpenseDBManager.updateExpense( ev );
    }

    public static void deleteExpense( int expenseId )
    {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId( expenseId );
        ExpenseDBManager.deleteExpense( ev );
    }

    public static BungyBean getMokuhyouAndExpenses( String userid, String month )
    {
        BungyVo bv = ExpenseDBManager.getMokuhyouAndExpenses( userid, month );
        BungyBean bb = new BungyBean();
        bb.setMokuhyou( bv.getMokuhyou() );
        bb.setSisyutu( bv.getTotalexpenses() );
        bb.setMonth( month );

        return bb;
    }

    public static ExpenseBean getAllSumOfDay(Calendar calendar, String userId) {
        ExpenseBean eb = new ExpenseBean();
        ArrayList<ExpenseVo> expenseList = ExpenseDBManager.getAllSumOfDay(calendar, userId);
        int[] expenses = new int[31];

        for(int i = 0; i < 31; i++) {
            expenses[i] = 0;
        }

        for(ExpenseVo ev : expenseList) {
            String dateStr = ev.getExpenseDate().toString().split("-")[2];
            int date = Integer.parseInt(dateStr) - 1;
            expenses[date] = ev.getExpenseKingaku();
        }

        eb.setExpenses(expenses);
        return eb;
    }

    public static ExpenseBean getExpensesOfDay(Date date, String userId) {

        ExpenseBean eb = new ExpenseBean();
        List<ExpenseVo> expenseOfDayList = new ArrayList<ExpenseVo>();

        //List<ExpenseVo> expenseOfDayList = ExpenseDBManager.getExpenseOfDay(date, userId);

        for(int i = 0; i < 10; i++) {
            ExpenseVo ev = new ExpenseVo();
            ev.setExpenseKingaku(100);
            ev.setExpenseName("banana");
            ev.setCategoryId(1);

            expenseOfDayList.add(ev);
        }

        eb.setExpenseOfDay(expenseOfDayList);

        return eb;
    }
    /**
     *
     * @return
     */
    public static List<CategoryVo> getCategory()
    {
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        list = ExpenseDBManager.getCategory();
        return list;
    }
}