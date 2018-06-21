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

    /**
     * メールアドレスを取得
     * @param userId
     * @return
     */
    public static String getMailAddress(String userId)
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {

            ConanDao cdao = new ConanDao( con );
            String mailAddress = cdao.getMailAddress(userId);
            return mailAddress;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 自白に必要なデータを取得
     * @param nengetsu
     * @param userId
     * @return
     */
    public static List<AdviceVo> selectJihakuAdvice( String nengetsu, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            JihakuDao jdao = new JihakuDao( con );
            List<AdviceVo> list = jdao.JihakuAdvice( nengetsu, userId );
            return list;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 月全体の支出合計を取得
     * @param nengetsu
     * @param userId
     * @return
     */
    public static int sumGoukei( String nengetsu, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            JihakuDao jdao = new JihakuDao( con );
            int goukei = jdao.getSisyutuGoukei( nengetsu, userId );
            return goukei;
        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * 月全体の目標金額を取得
     * @param nengetsu
     * @param userId
     * @return
     */
    public static int sumMokuhyou( String nengetsu, String userId )
    {
        try (
                Connection con = PoolConnection.getConnection(); )
        {
            JihakuDao jdao = new JihakuDao( con );
            int mokuhyou = jdao.getTsukiMokuhyou( nengetsu, userId );
            return mokuhyou;

        } catch ( SQLException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }

    /**
     * ユーザーIDと該当の年月に対応する分析情報の取得
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
