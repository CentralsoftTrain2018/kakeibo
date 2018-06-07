package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ConanDao;
import vo.ConanVo;

public class AdviceDBManager {
    //アドバイスに必要なやつを取ってくる
    public static List<ConanVo> selectAdvice() {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            ConanDao cdao = new ConanDao(con);
            List<ConanVo> list = cdao.advice();
           return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
