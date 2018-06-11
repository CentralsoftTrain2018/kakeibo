package service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import bean.JihakuBean;
import bean.JihakuListBean;
import dbmanager.AdviceDBManager;
import vo.AdviceVo;

public class AdviceService
{

    //AdviceVo型のListをConanBean型のListに変換
    public JihakuListBean jihaku( Date date, String userId )
    {
        List<AdviceVo> resultList = AdviceDBManager.selectJihakuAdvice( date, userId );
        JihakuListBean jlb = new JihakuListBean();

        //計算結果と表示するメッセージを入れ物（bean)にセットする
        for ( AdviceVo av : resultList )
        {
            JihakuBean jb = new JihakuBean();
            jb.setCategoryname( av.getCategoryName() );
            jb.setExcess( av.getDifference() );

            jlb.getJihakulist().add( jb );
        }

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
        Calendar calendar = Calendar.getInstance();
        String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
        clb.setNengetsu( nengetsu );

        calendar.add( Calendar.MONTH, -1 );
        nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

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
}
