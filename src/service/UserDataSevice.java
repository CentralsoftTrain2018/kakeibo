package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import bean.RegistBean;
import bean.SetteiBean;
import dbmanager.UserDBManeger;
import vo.SetteiVo;

public class UserDataSevice
{
    /**
     * 会員登録に必要なデータを渡す
     * @param rb
     */
    public static void passRegistDara( RegistBean rb )
    {
        UserDBManeger.passRegistDara( rb );
    }

    /**
     * ID重複チェック
     * @param userId
     * @return
     */
    public static boolean isUnique( String userId )
    {
        boolean result = UserDBManeger.isUnique( userId );
        return result;
    }

    public static void addCategory( String categoryName )
    {
        UserDBManeger.addCategory( categoryName );
    }

    public static void updateCategory( int categoryId, String categoryName )
    {
        UserDBManeger.updateCategory( categoryId, categoryName );
    }

    public static void deleteCategory( int categoryId )
    {
        UserDBManeger.deleteCategory( categoryId );
    }


    public static  SetteiBean settei( String userId)
    {

        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

        int syunyuu = UserDBManeger.getSyunyuu( userId );
        List<SetteiVo> mokuhyou = UserDBManeger.getMokuhyou(userId,nengetsu);


        System.out.println( syunyuu );

        SetteiBean sb = new SetteiBean();

        sb.setSyunyuu(syunyuu);
        sb.setCategoryMokuhyouList(mokuhyou);


        return sb;
    }

}
