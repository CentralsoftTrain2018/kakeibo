package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.LoginBean;
import dao.LoginDao;
import dao.RegistDao;
import dao.SetteiDao;
import vo.RegistVo;
import vo.SetteiVo;

public class UserDBManeger
{
    //ユーザーの登録
    //呼び出し元
    //UserDataService
    //呼び出し先
    //RegistDao
    public static void passRegistDara( RegistVo rv )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            RegistDao rdao = new RegistDao( con );

            rdao.kaiinInsert( rv );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

    /**
     * ID重複チェック
     * @param userId
     * @return
     */
    //呼び出し元
    //UserDataService
    //呼び出し先
    //RegistDao
    public static boolean isUnique( String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            RegistDao rdao = new RegistDao( con );

            boolean result = rdao.idCheck( userId );
            return result;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }
    /**
     * ID・パスワードチェック
     * @param lb
     * @return
     */
    public static boolean isLogin( LoginBean lb )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            LoginDao ldao = new LoginDao( con );

            boolean result = ldao.idPassCheck( lb );
            return result;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }

    /**
     * ユーザーに目標が設定されているか
     * @param userId
     * @return 設定されている：true 設定されていない：false
     */
    public static boolean hasMokuhyou( String userId)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            LoginDao ldao = new LoginDao( con );

            boolean result = ldao.hasMokuhyou( userId );
            return result;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //カテゴリーの追加
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void addCategory( String categoryName, String categoryColor,String userId, String nengetu, int kingaku )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.addCategory( categoryName, categoryColor,userId,nengetu,kingaku );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static void addNewMokuhyou( String categoryName, String userId, String nengetu, int kingaku )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.addNewMokuhyou( categoryName, userId,nengetu,kingaku );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //カテゴリーの変更
     //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void updateCategory(String newCategoryName, String userId, String oldCategoryName )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.updateCategory(newCategoryName, userId, oldCategoryName);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //カテゴリーの削除
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void deleteCategory(String userId, String categoryName)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.deleteCategory(userId, categoryName);
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //収入の取得
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
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

    //目標の取得
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static List<SetteiVo> getMokuhyou( String userId ,String nengetsu)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            List<SetteiVo> mokuhyou = sdao.getMokuhyou( userId, nengetsu );
            return mokuhyou;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //パスワードの取得
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static String getPassword( String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            String password = sdao.getPassword( userId );
            return password;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //パスワードの変更
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void updatePassword(String userId, String password)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.updatePassword( userId, password );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //収入の変更
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void updateSyunyuu(String userId, int newIncome)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.updateSyunyuu( userId, newIncome );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //目標の変更
    //呼び出し元
    //UserDataService
    //呼び出し先
    //SetteiDao
    public static void updateMokuhyou(String userId, int newMokuhyoukingaku, String categoryName, String nengetsu)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            SetteiDao sdao = new SetteiDao( con );
            sdao.updateMokuhyou( userId, newMokuhyoukingaku, categoryName, nengetsu );
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }


}
