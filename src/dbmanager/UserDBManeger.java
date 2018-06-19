package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import bean.RegistBean;
import dao.RegistDao;
import dao.SetteiDao;

public class UserDBManeger
{
    public static void passRegistDara( RegistBean rb )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            RegistDao rdao = new RegistDao( con );
            rdao.kaiinInsert(rb);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

    public static void addCategory(String categoryName) {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.addCategory(categoryName);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static void updateCategory(int categoryId, String categoryName) {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.updateCategory(categoryId, categoryName);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static void deleteCategory(int categoryId) {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.deleteCategory(categoryId);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static int getSyunyuu( String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            int syunyuu = sdao.getSyunyuu( userId );
            return syunyuu;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
