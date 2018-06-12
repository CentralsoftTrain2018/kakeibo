package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ConanDao;
import dao.JihakuDao;
import vo.AdviceVo;
/**
* アドバイス機能のマネージャー
* コナン・自白の２機能の処理を行う
*/
public class AdviceDBManager {
     /**
       * selectConanAdvice
       * アドバイス（コナン）に必要なやつを取ってくる
       * @param nengetsu 年月
       * @param userId ユーザーID
       * @return カテゴリごとの名前、目標-支出合計が格納されたAdviceVo型のリスト
       */
    public static List<AdviceVo> selectConanAdvice(String nengetsu, String userId) {

        try
        (
            Connection con = PoolConnection.getConnection();
        )
        {

            ConanDao cdao = new ConanDao(con);
            List<AdviceVo> list = cdao.getAdvice(nengetsu, userId);
           return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //アドバイス(自白)に必要なデータを取得する
    public static List<AdviceVo> selectJihakuAdvice(String date, String userId) {
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
