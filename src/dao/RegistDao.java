package dao;

import java.sql.Connection;

import bean.RegistBean;

public class RegistDao extends Dao
{
    public RegistDao( Connection con )
    {
        super( con );
    }
    private static final String SELECT = "select ";

    public void kaiinInsert(RegistBean rb)
    {
        // TODO 自動生成されたメソッド・スタブ

    }
}
