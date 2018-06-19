package dao;

import java.sql.Connection;

import bean.LoginBean;

public class LoginDao extends Dao
{

    public LoginDao( Connection con )
    {
        super( con );
    }

    public boolean idPassCheck( LoginBean lb )
    {
        return false;
    }

}
