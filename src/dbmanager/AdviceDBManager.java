package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BunsekiDao;
import dao.ConanDao;
import dao.JihakuDao;
import vo.AdviceVo;
import vo.BunsekiVo;

/**
* アドバイス機能のマネージャー
* コナン・自白の２機能の処理を行う
*/
public class AdviceDBManager
{
    /**
      * selectConanAdvice
      * アドバイス（コナン）に必要なやつを取ってくる
      * @param nengetsu 年月
      * @param userId ユーザーID
      * @return カテゴリごとの名前、目標-支出合計が格納されたAdviceVo型のリスト
      */
    public static List<AdviceVo> selectConanAdvice( String nengetsu, String userId )
    {

        try (
                Connection con = PoolConnection.getConnection(); )
        {

            ConanDao cdao = new ConanDao( con );
            List<AdviceVo> list = cdao.getAdvice( nengetsu, userId );
            return list;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    //アドバイス(自白)に必要なデータを取得する
    public static List<AdviceVo> selectJihakuAdvice( String mokuhyou, String date, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            JihakuDao jdao = new JihakuDao( con );
            List<AdviceVo> list = jdao.JihakuAdvice( mokuhyou, date, userId ) /**jdao.jihakuAdvice()**/
            ;
            return list;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static int sumGoukei( String date, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            JihakuDao jdao = new JihakuDao( con );
            int goukei = jdao.JihakuGoukei( date, userId ) /**jdao.jihakuAdvice()**/
            ;
            return goukei;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    public static int sumMokuhyou( String date, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            JihakuDao jdao = new JihakuDao( con );
            int mokuhyou = jdao.JihakuMokuhyou( date, userId ) /**jdao.jihakuAdvice()**/
            ;
            return mokuhyou;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * ユーザーIDと該当の年月二対応する分析情報の取得
     * @param nengetsu
     * @param userId
     * @return 分析情報
     */
    public static List<BunsekiVo> selectBunseki( String nengetsu, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            BunsekiDao bdao = new BunsekiDao( con );
            List<BunsekiVo> list = bdao.getBunseki( nengetsu, userId );
            return list;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
