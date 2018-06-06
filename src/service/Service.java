package service;

import java.sql.Date;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import dbmanager.DBManager;
<<<<<<< HEAD
import vo.ConanVo;
import vo.ExpenseVo;
=======
import vo.KakeiboVo;
>>>>>>> refs/remotes/origin/master

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