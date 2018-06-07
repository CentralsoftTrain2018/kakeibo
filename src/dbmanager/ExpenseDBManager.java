package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BungyDao;
import dao.KakeiboDao;
import vo.BungyVo;
import vo.KakeiboVo;



public class ExpenseDBManager {
    public static void addExpense(KakeiboVo ev) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.addExpense(ev);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void updateExpense(KakeiboVo ev) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.updateExpense(ev);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void deleteExpense(KakeiboVo ev) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.deleteExpense(ev);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static BungyVo getMokuhyouAndExpenses(String userid, String month) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            BungyDao bdao = new BungyDao(con);
            BungyVo bv = bdao.getMokuhyouAndExpenses(userid, month);

            //BungyVo bv = new BungyVo();
            //bv.setMokuhyou(10000);
            //bv.setTotalexpenses(5000);

            return bv;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
