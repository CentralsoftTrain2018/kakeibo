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

    //AdviceVo型のListをConanBean型のListに変換
    public JihakuListBean jihaku( String date, String userId )
    {
        List<AdviceVo> resultList = AdviceDBManager.selectJihakuAdvice( date, userId );
        int goukei = AdviceDBManager.sumGoukei( date, userId );
        int mokuhyou = AdviceDBManager.sumMokuhyou( date, userId );
        JihakuListBean jlb = new JihakuListBean();
        List<JihakuBean> list = new ArrayList<JihakuBean>();

        //現在の月を取得（2018/05）
        jlb.setNengetsu( getNengetsu() );

        //計算結果と表示するメッセージを入れ物（bean)にセットする
        for ( AdviceVo av : resultList )
        {
            JihakuBean jb = new JihakuBean();
            jb.setCategoryname( av.getCategoryName() );
            jb.setExcess( av.getDifference() );

            list.add( jb );
        }
        jlb.setJihakulist( list );
        jlb.setGoukei( goukei );
        jlb.setMokuhyou( mokuhyou );
        return jlb;
    }

    /**
     * selectConanAdvice
     * AdviceVo型のListをConanBean型のListに変換
     * @param userId ユーザーID
     * @return ConanListBean
     */
    public static ConanListBean selectConanAdvice( String userId )
    {
        ConanListBean clb = new ConanListBean();
        List<ConanBean> list = new ArrayList<ConanBean>();

        //現在の月を取得（2018/05）
        clb.setNengetsu( getNengetsu() );

        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH, -1 );
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

        List<AdviceVo> resultList = AdviceDBManager.selectConanAdvice( nengetsu, userId );

        for ( AdviceVo av : resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName( av.getCategoryName() );
            cb.setDifference( av.getDifference() );

            list.add( cb );
        }
        //ConanListBeanに入れる
        clb.setList( list );
        //月の値を入れる
        clb.setThisMonth( calendar.get( Calendar.MONTH ) + 1 );

        return clb;
    }

    /**
     * getNengetsu
     * 現在の年月をyyyy/MMで取得
     * @return nengetsu
     */
    protected static String getNengetsu()
    {
        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
        return nengetsu;
    }

    public static BunsekiListBean selectBunseki( String userId )
    {
        String nengetsu = getNengetsu();
        BunsekiListBean blb = setBunsekiList( userId, nengetsu );

        return blb;
    }

    public static BunsekiListBean selectBunseki( String userId, Calendar calendar )
    {
        //指定された年月を取得
        //Calendar ｄesignationCalendar = calendar;
        //calendar.add( Calendar.MONTH, -1 );
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

        BunsekiListBean blb = setBunsekiList( userId, nengetsu );

        return blb;
    }

    /**
     * リストに入れる
     * @param userId
     * @param nengetsu
     * @return　BunsekiListBean
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
            bb.setWariai( (100 * bb.getSumSpending() / allSumSpending) );
            blb.setSumSpending( allSumSpending );
            blb.addList( bb );
        }
        return blb;

    }
}
