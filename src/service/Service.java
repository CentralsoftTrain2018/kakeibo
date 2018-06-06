package service;

import java.sql.Date;

import dbmanager.DBManager;
import vo.ExpenseVo;

public class Service {
    public static void addExpense(int kingaku, int categoryId, String expenseName, String userId) {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        Date expenseDate = new Date(new java.util.Date().getTime());
        ev.setExpenseDate(expenseDate);
        ev.setUserId(userId);
        DBManager.addExpense(ev);
    }


    public static void updateExpense(int expenseId, int kingaku, int categoryId, String expenseName) {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId(expenseId);
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryId(categoryId);
        ev.setExpenseName(expenseName);
        DBManager.updateExpense(ev);
    }

    public static void deleteExpense(int expenseId) {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId(expenseId);
        DBManager.deleteExpense(ev);
    }
}