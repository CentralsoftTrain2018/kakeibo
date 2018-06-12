package service;

import java.sql.Date;
import java.util.Calendar;

import bean.BungyBean;
import bean.ExpenseBean;
import dbmanager.ExpenseDBManager;
import vo.BungyVo;
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
        ExpenseBean ev = new ExpenseBean();
        return null;
    }

}