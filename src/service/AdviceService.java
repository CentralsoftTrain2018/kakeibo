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

public class AdviceService {

    //AdviceVo型のListをConanBean型のListに変換
    public JihakuListBean jihaku(Date date, String userId) {
        List<AdviceVo> resultList = AdviceDBManager.selectJihakuAdvice(date, userId);
        JihakuListBean jlb= new JihakuListBean();

        //計算結果と表示するメッセージを入れ物（bean)にセットする
        for(AdviceVo av : resultList)
        {
            JihakuBean jb = new JihakuBean();
            jb.setCategoryname( av.getCategoryName());
            jb.setExcess( av.getDifference() );

            jlb.getJihakulist().add(jb);
        }

        return jlb;
    }

//---------------------------コナン機能---------------------------------------------------------
    //AdviceVo型のListをConanBean型のListに変換
    public static ConanListBean selectConanAdvice() {
        //現在の月を取得（201806）
        Calendar calendar = Calendar.getInstance();
        String thisMonth = new SimpleDateFormat( "yyyyMM" ).format( calendar.getTime() );

        //取得したい値は201805なので-1している
        int month = Integer.parseInt(thisMonth) -1;

        List<AdviceVo> resultList = AdviceDBManager.selectConanAdvice(month);

        ConanListBean clb = new ConanListBean();

        List<ConanBean> list = new ArrayList<ConanBean>();
        for( AdviceVo av: resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName(av.getCategoryName());
            cb.setDifference(av.getDifference());

            list.add(cb);
        }
        //ConanListBeanに入れる
        clb.setList(list);
        //月の値を入れる（6月の場合5が出力されるので、本番では+1すること）
        clb.setThisMonth(calendar.get(Calendar.MONTH));

        return clb;
    }
}
