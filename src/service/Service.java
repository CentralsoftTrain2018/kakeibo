package service;

import java.sql.Date;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import dbmanager.DBManager;
import vo.ConanVo;
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

    //ConanVo型のListをConanBean型のListに変換
    public static ConanListBean selectAdvice() {
        List<ConanVo> resultList = DBManager.selectAdvice();
        ConanListBean clb = new ConanListBean();

        for( ConanVo cv: resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName(cv.getCategoryName());
            cb.setSumSpending(cv.getSumSpending());
            cb.setMokuhyouKingaku(cv.getMokuhyouKingaku());
            clb.getList().add(cb);
        }

        return clb;
    }
}