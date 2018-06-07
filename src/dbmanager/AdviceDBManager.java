package dbmanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.ConanDao;
import dao.JihakuDao;
import vo.AdviceVo;

public class AdviceDBManager {

    //アドバイス（コナン）に必要なやつを取ってくる
    public static List<AdviceVo> selectConanAdvice(int month) {


        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {

            ConanDao cdao = new ConanDao(con);
            List<AdviceVo> list = cdao.getAdvice(month);
           return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //アドバイス(自白)に必要なデータを取得する
    public static List<AdviceVo> selectJihakuAdvice(Date date, String userId) {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {

            JihakuDao jdao = new JihakuDao(con);
            List<AdviceVo> list = jdao.JihakuAdvice(date, userId) /**jdao.jihakuAdvice()**/;
            return list;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
