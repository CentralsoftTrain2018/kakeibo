package service;

import bean.RegistBean;
import bean.SetteiBean;
import dbmanager.UserDBManeger;

public class UserDataSevice
{
    public static void passRegistDara(RegistBean rb)
    {
        UserDBManeger.passRegistDara( rb );
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
