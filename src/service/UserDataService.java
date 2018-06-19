package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
    public static void updateCategory( int categoryId, String categoryName )
    {
        UserDBManeger.updateCategory( categoryId, categoryName );
    }

    //カテゴリーの削除
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static void deleteCategory( int categoryId)
    {
        UserDBManeger.deleteCategory(categoryId);
    }

    //目標をつめたbeanを返す
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDBManager
    public static  SetteiBean settei( String userId)
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

}