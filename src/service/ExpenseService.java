package service;

import java.sql.Date;

import bean.BungyBean;
import dbmanager.ExpenseDBManager;
import vo.BungyVo;
import vo.KakeiboVo;


public class ExpenseService {
    public static void addExpense(int kingaku, int categoryId, String expenseName, String userId) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        Date expenseDate = new Date(new java.util.Date().getTime());
        ev.setExpenseDate(expenseDate);
        ev.setUserId(userId);
        ExpenseDBManager.addExpense(ev);
    }


    public static void updateExpense(int expenseId, int kingaku, int categoryId, String expenseName) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseId(expenseId);
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        ExpenseDBManager.updateExpense(ev);
    }

    public static void deleteExpense(int expenseId) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseId(expenseId);
        ExpenseDBManager.deleteExpense(ev);
    }

    public static BungyBean getMokuhyouAndExpenses(String userid, String month) {
        BungyVo bv = ExpenseDBManager.getMokuhyouAndExpenses(userid, month);
        BungyBean bb=new BungyBean();
        bb.setMokuhyou(bv.getMokuhyou());
        bb.setSisyutu(bv.getTotalexpenses());
        bb.setMonth(month);

        return bb;
    }

}