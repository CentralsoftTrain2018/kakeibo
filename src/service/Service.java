package service;

import java.sql.Date;

import vo.ExpenseVo;

public class Service {
    public static void addExpense(int kingaku, String categoryName, String expenseName, String userId) {
        ExpenseVo ev = new ExpenseVo();
    }

    public static void updateExpense(int expenseId, int kingaku, String categoryName, String expenseName, Date expenseDate, String userId) {
        ExpenseVo ev = new ExpenseVo();
    }

    public static void deleteExpense(int expenseId) {
        ExpenseVo ev = new ExpenseVo();
    }
}

enum Category{
    yachin,
    kounetsuhi,
    syokuhi,
    hifukuhi,
    gorakuhi,
    kousaihi,
    tuusinhi,
    biyouhi,
    koutuuhi,
    sonota;
}