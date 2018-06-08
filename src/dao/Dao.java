package dao;

import java.sql.Connection;

public class Dao {
    Connection con;

    public Dao(Connection con) {
        super();
        this.con = con;
    }
}
