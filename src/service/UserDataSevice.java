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

    public static void addCategory(String categoryName) {
        UserDBManeger.addCategory(categoryName);
    }

    public static void updateCategory(int categoryId, String categoryName) {
        UserDBManeger.updateCategory(categoryId, categoryName);
    }

    public static void deleteCategory(int categoryId) {
        UserDBManeger.deleteCategory(categoryId);
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
