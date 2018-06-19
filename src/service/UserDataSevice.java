package service;

import bean.RegistBean;
import bean.SetteiBean;
import dbmanager.UserDBManeger;

public class UserDataSevice
{
    /**
     * 会員登録に必要なデータを渡す
     * @param rb
     */
    public static void passRegistDara(RegistBean rb)
    {
        UserDBManeger.passRegistDara( rb );
    }

    /**
     * ID重複チェック
     * @param userId
     * @return
     */
    public static boolean isUnique( String userId)
    {
        boolean result = UserDBManeger.isUnique( userId );
        return result;
    }

    public  SetteiBean settei( String userId)
    {
        int syunyuu = UserDBManeger.getSyunyuu( userId );
        System.out.println(syunyuu);

        SetteiBean sb = new SetteiBean();

        sb.setSyunyuu(syunyuu);


        return sb;
    }

}
