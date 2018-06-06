package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ConanDao;
import vo.ConanVo;
import vo.ExpenseVo;;

public class DBManager {
    public static void addExpense(ExpenseVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            //ExpensesDao edao = new ExpensesDao(con);
            //edao.addExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void updateExpense(ExpenseVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            //ExpensesDao edao = new ExpensesDao(con);
            //edao.updateExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void deleteExpense(ExpenseVo ev) {
        try
        (
            Connection con = getConnection();
        )
        {
            //ExpensesDao edao = new ExpensesDao(con);
            //edao.deleteExpense(ev);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //アドバイスに必要なやつを取ってくる
    public static void selectAdvice(ConanVo cv) {
        try
        (
            Connection con = getConnection();
        )
        {
            ConanDao cdao = new ConanDao(con);
            cdao.deleteExpense(cv);
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
