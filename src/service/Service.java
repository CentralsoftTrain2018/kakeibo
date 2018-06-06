package service;

import java.sql.Date;

import dbmanager.DBManager;
import vo.KakeiboVo;

public class Service {
    public static void addExpense(int kingaku, int categoryId, String expenseName, String userId) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        Date expenseDate = new Date(new java.util.Date().getTime());
        ev.setExpenseDate(expenseDate);
        ev.setUserId(userId);
        DBManager.addExpense(ev);
    }


    public static void updateExpense(int expenseId, int kingaku, int categoryId, String expenseName) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseId(expenseId);
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        DBManager.updateExpense(ev);
    }

    public static void deleteExpense(int expenseId) {
        KakeiboVo ev = new KakeiboVo();
        ev.setExpenseId(expenseId);
        DBManager.deleteExpense(ev);
    }
}