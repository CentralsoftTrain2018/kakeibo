package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import bean.LoginBean;
import bean.RegistBean;
import bean.SetteiBean;
import dbmanager.UserDBManeger;
import vo.SetteiVo;

public class UserDataService
{
    /**
     * 会員登録に必要なデータを渡す
     * @param rb
     */
    //呼び出し元
    //RegistServlet
    //呼び出し先
    //UserDBManager
    public static void passRegistDara( RegistBean rb )
    {
        UserDBManeger.passRegistDara( rb );
    }

    /**
     * ID重複チェック
     * @param userId
     * @return
     */
    //呼び出し元
    //RegistServlet
    //呼び出し先
    //UserDBManager
    public static boolean isUnique( String userId )
    {
        boolean result = UserDBManeger.isUnique( userId );
        return result;
    }
    /**
     * ID・パスワードチェック
     * @param lb
     * @return
     */
    public static boolean isLogin( LoginBean lb )
    {
        boolean result = UserDBManeger.isLogin( lb );
        return result;
    }
    /**
     * ユーザーに目標が設定されているか
     * @param lb
     * @return 設定されている：true 設定されていない：false
     */
    public static boolean hasMokuhyou( String userId )
    {
        boolean result = UserDBManeger.hasMokuhyou( userId);
        return result;
    }

    //カテゴリーの追加
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void addCategory( String categoryName )
    {
        UserDBManeger.addCategory( categoryName );
    }

    //カテゴリーの変更
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void updateCategory( String newCategoryName, String userId, String oldCategoryName )
    {
        UserDBManeger.updateCategory( newCategoryName, userId, oldCategoryName );
    }

    //カテゴリーの削除
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void deleteCategory(String userId, String categoryName)
    {
        UserDBManeger.deleteCategory(userId, categoryName);
    }

    //目標をつめたbeanを返す
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static  SetteiBean settei( String userId )
    {
        //現在時刻の取得
        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

        //収入の取得
        int syunyuu = UserDBManeger.getSyunyuu( userId );
        //目標の取得
        List<SetteiVo> mokuhyou = UserDBManeger.getMokuhyou(userId,nengetsu);


        //beanの生成
        SetteiBean sb = new SetteiBean();
        sb.setSyunyuu(syunyuu);
        sb.setCategoryMokuhyouList(mokuhyou);

        return sb;
    }

    //パスワードの取得
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static String getPassword(String userId)
    {
        String password = UserDBManeger.getPassword(userId);
        return password;
    }

    //パスワードの変更
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void updatePassword(String userId, String password)
    {
        UserDBManeger.updatePassword(userId, password);
    }

    //収入の変更
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void updateSyunyuu(String userId, int newIncome)
    {
        UserDBManeger.updateSyunyuu(userId, newIncome);
    }

    //目標の変更
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void updateMokuhyou(String userId, int newMokuhyoukingaku, String categoryName)
    {
        //現在時刻の取得
        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

        UserDBManeger.updateMokuhyou(userId, newMokuhyoukingaku, categoryName, nengetsu);
    }

}
