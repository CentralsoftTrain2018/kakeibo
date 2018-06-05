package service;

import java.sql.Date;

import dbmanager.DBManager;
import vo.ExpenseVo;

public class Service {
    public static void addExpense(int kingaku, String categoryName, String expenseName, String userId) {

        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryName(categoryName);
        ev.setExpenseName(expenseName);
        ev.setUserId(userId);
        Date expenseDate = new Date(new java.util.Date().getTime());
        ev.setExpenseDate(expenseDate);
        DBManager.addExpense(ev);
    }

    public static void updateExpense(int expenseId, int kingaku, String categoryName, String expenseName, Date expenseDate, String userId) {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId(expenseId);
        ev.setExpenseKingaku(kingaku);
        ev.setCategoryName(categoryName);
        ev.setExpenseName(expenseName);
        ev.setExpenseDate(expenseDate);
        ev.setUserId(userId);
        DBManager.updateExpense(ev);
    }

    public static void deleteExpense(int expenseId) {
        ExpenseVo ev = new ExpenseVo();
        ev.setExpenseId(expenseId);
        DBManager.deleteExpense(ev);
    }
}