package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ConanDao;
import vo.ConanVo;

public class AdviceDBManager {
    //アドバイス（コナン）に必要なやつを取ってくる
    public static List<ConanVo> selectConanAdvice(int month) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            ConanDao cdao = new ConanDao(con);
            List<ConanVo> list = cdao.advice(month);
           return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
