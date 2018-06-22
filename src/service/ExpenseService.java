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
    public static void addExpense( int kingaku, int categoryId, String expenseName, String userId, Date expenseDate )
    {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseKingaku( kingaku );
        ev.setCategoryId( categoryId );
        ev.setExpenseName( expenseName );
        //Date expenseDate = new Date( new java.util.Date().getTime() );
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
        bb.setNengetsu( month );

        return bb;
    }

    public static ExpenseBean makeExpenseBean(Calendar calendar, Date expenseDate, String userId) {
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

        // 1日の支出一覧の取得
        List<ExpenseVo> expenseOfDayList = ExpenseDBManager.getExpenseOfDay(expenseDate, userId);
        eb.setExpenseOfDay(expenseOfDayList);

        // カテゴリー一覧の取得
        List<CategoryVo> categoryList = ExpenseDBManager.getCategory(userId);

        eb.setCategoryList(categoryList);

        //ユーザーの登録月の取得
        String registMonth[] = ExpenseDBManager.getRegistMonth(userId).split("/", 0);
        int year = Integer.parseInt(registMonth[0]);
        int month = Integer.parseInt(registMonth[1]);

        eb.setRegistYear(year);
        eb.setRegistMonth(month);

        return eb;
    }
}

