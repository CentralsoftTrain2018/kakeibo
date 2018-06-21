package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.BunsekiBean;
import bean.BunsekiListBean;
import bean.ConanBean;
import bean.ConanListBean;
import bean.JihakuBean;
import bean.JihakuListBean;
import dbmanager.AdviceDBManager;
import vo.AdviceVo;
import vo.BunsekiVo;

public class AdviceService
{

    /**
     * jihaku
     * ID指定したユーザーの当月の自白を取得する
     * @param ユーザID、年月
     * @return 現在の月の犯人の自白
     */
    public JihakuListBean jihaku( String userId, String nengetsu )

    {

        List<AdviceVo> resultList = AdviceDBManager.selectJihakuAdvice( nengetsu, userId );
        int goukei = AdviceDBManager.sumGoukei( nengetsu, userId );
        int mokuhyou = AdviceDBManager.sumMokuhyou( nengetsu, userId );

        JihakuListBean jlb = new JihakuListBean();
        List<JihakuBean> list = new ArrayList<JihakuBean>();

        //計算結果と表示するメッセージを入れ物（bean)にセットする
        for ( AdviceVo av : resultList )
        {
            JihakuBean jb = new JihakuBean();
            if ( av.getDifference() > 0 )
            {
                jb.setCategoryname( av.getCategoryName() );
                jb.setExcess( av.getDifference() );
                list.add( jb );
            }
        }
        jlb.setJihakulist( list );
        jlb.setGoukei( goukei );
        jlb.setMokuhyou( mokuhyou );
        jlb.setNengetsu( nengetsu );

        return jlb;
    }

    /**
     * selectConanAdvice
     * ID指定したユーザーの当月のコナン君アドバイスを取得する
     * @param userId ユーザーID
     * @return 現在の月のコナン君のアドバイス
     */
    public static ConanListBean selectConanAdvice( String userId, String nengetsu )
    {
        ConanListBean clb = new ConanListBean();
        List<ConanBean> list = new ArrayList<ConanBean>();
        List<AdviceVo> resultList = AdviceDBManager.selectConanAdvice( nengetsu, userId );
        String[] YearAndMonth = nengetsu.split( "/" );

        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.YEAR, Integer.parseInt( YearAndMonth[0] ) );
        calendar.set( Calendar.MONTH, Integer.parseInt( YearAndMonth[1] ) );

        for ( AdviceVo av : resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName( av.getCategoryName() );
            cb.setDifference( av.getDifference() );

            list.add( cb );
        }
        clb.setList( list );
        clb.setNengetsu( nengetsu );
        clb.setDate( calendar );

        return clb;
    }

    /**
     * getNengetsu
     * 現在の年月をyyyy/MMで取得
     * @return 現在の年月
     */
    protected static String getNengetsu()
    {
        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
        return nengetsu;
    }

    public static String getMailAddress( String userId )
    {
        String mailAddress = AdviceDBManager.getMailAddress( userId );
        return mailAddress;
    }

    /**
     * 現在の年月を取得する
     * @param userId
     * @return
     */
    public static BunsekiListBean selectBunseki( String userId )
    {
        Calendar calendar = Calendar.getInstance();
//        calendar.add( Calendar.MONTH, +1 );
        calendar.get( Calendar.YEAR );
        calendar.get( Calendar.MONTH );
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
        System.out.println( "selectBunseki（ページ遷移）" + nengetsu);

        BunsekiListBean blb = setBunsekiList( userId, nengetsu );
        blb.setDate( calendar );

        return blb;
    }

    public static BunsekiListBean selectBunseki( String userId, Calendar calendar )
    {
        //指定された年月を取得
        //Calendar ｄesignationCalendar = calendar;
        //calendar.add( Calendar.MONTH, -1 );
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
        BunsekiListBean blb = setBunsekiList( userId, nengetsu );
        System.out.println( "selectBunseki(プルダウン選択)" + nengetsu );
        blb.setDate( calendar );

        return blb;
    }

    /**
     * 取得した分析情報をBeanにセットする
     * @param userId
     * @param nengetsu
     * @return　
     */
    protected static BunsekiListBean setBunsekiList( String userId, String nengetsu )
    {
        BunsekiListBean blb = new BunsekiListBean();

        List<BunsekiVo> bunsekiList = AdviceDBManager.selectBunseki( nengetsu, userId );
        int allSumSpending = 0;
        for ( BunsekiVo bv : bunsekiList )
        {
            allSumSpending += bv.getSumSpending();
        }

        for ( BunsekiVo bv : bunsekiList )
        {
            BunsekiBean bb = new BunsekiBean();
            bb.setCategoryName( bv.getCategoryName() );
            bb.setDifference( bv.getDifference() );
            bb.setMokuhyouKingaku( bv.getMokuhyouKingaku() );
            bb.setSumSpending( bv.getSumSpending() );
            bb.setColor( bv.getColor() );
            if ( allSumSpending == 0 )
            {
                bb.setWariai( 0 );
            } else
            {
                int wariai = (100 * bb.getSumSpending() / allSumSpending);
                bb.setWariai( wariai );
            }
            blb.setSumSpending( allSumSpending );
            blb.addList( bb );
        }
        return blb;

    }

}
