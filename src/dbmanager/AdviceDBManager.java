package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConanDao;
import dao.JihakuDao;
import vo.AdviceVo;

public class AdviceDBManager {
    //アドバイス（コナン）に必要なやつを取ってくる
    public static List<AdviceVo> selectConanAdvice() {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {
            ConanDao cdao = new ConanDao(con);
            List<AdviceVo> list = cdao.advice();
           return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //アドバイス(自白)に必要なデータを取得する
    public static List<AdviceVo> selectJihakuAdvice() {
        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {

            JihakuDao jdao = new JihakuDao();
            List<AdviceVo> list = new ArrayList<>() /**jdao.jihakuAdvice()**/;
            return list;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
