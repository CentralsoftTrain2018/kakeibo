package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import dbmanager.AdviceDBManager;
import dbmanager.ExpenseDBManager;
import vo.ConanVo;
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

    //ConanVo型のListをConanBean型のListに変換
    public static ConanListBean selectAdvice() {
        List<ConanVo> resultList = AdviceDBManager.selectConanAdvice();
        ConanListBean clb = new ConanListBean();

        int totalGoal = 0;		//月の目標
        int totalSpending = 0;	//月の支出合計
        int totalDifference = 0;	//月の目標ー支出
        List<ConanBean> list = new ArrayList<ConanBean>();
        for( ConanVo cv: resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName(cv.getCategoryName());

            int spanding = cv.getSumSpending();
            totalSpending += spanding;
            cb.setSumSpending(spanding);

            int goal = cv.getMokuhyouKingaku();
            totalGoal += goal;
            cb.setMokuhyouKingaku(goal);

            int difference = cv.getDifference();
            totalDifference += difference;
            cb.setDifference(difference);

            list.add(cb);
        }
        //ConanListBeanに入れる
        clb.setList(list);
        clb.setThisMonth(5);
        clb.setTotalGoal(totalGoal);
        clb.setTotalSpending(totalSpending);
        clb.setTotalDifference(totalDifference);

        return clb;
    }
}