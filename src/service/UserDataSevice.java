package service;

import bean.RegistBean;
import dbmanager.UserDBManeger;

public class UserDataSevice
{
    public static void passRegistDara(RegistBean rb)
    {
        UserDBManeger.passRegistDara( rb );
    }

}
