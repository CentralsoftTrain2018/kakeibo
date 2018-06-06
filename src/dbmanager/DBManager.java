package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.ConanDao;
import dao.KakeiboDao;
import vo.ConanVo;
import vo.KakeiboVo;



public class DBManager {
    public static void addExpense(KakeiboVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.addExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void updateExpense(KakeiboVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.updateExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void deleteExpense(KakeiboVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            KakeiboDao edao = new KakeiboDao(con);
            edao.deleteExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //アドバイスに必要なやつを取ってくる
    public static List<ConanVo> selectAdvice() {
        try
        (
            Connection con = getConnection();
        )
        {
            ConanDao cdao = new ConanDao(con);
            List<ConanVo> list = cdao.advice();
           return list;
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException
     {
        String user = "train2018";
        String pass = "train2018";
        String servername = "localhost:3306";
        String dbname = "kakeibo";

        Class.forName("com.mysql.jdbc.Driver");

        Connection c = DriverManager.getConnection(
                    "jdbc:mysql://"
                    + servername
                    + "/"
                    + dbname,
                    user,
                    pass);

        return c;
     }
}
