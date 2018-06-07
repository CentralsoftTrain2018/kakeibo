package service;

import java.util.ArrayList;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import bean.JihakuBean;
import bean.JihakuListBean;
import dbmanager.AdviceDBManager;
import vo.ConanVo;

public class AdviceService {

    public JihakuListBean jihaku() {
        JihakuListBean jihakulist= new JihakuListBean();

        JihakuBean bean = new JihakuBean();
        int sisyutu = 10000;
        int mokuhyou = 5000;
        String categoryname = ("雑費");
        //計算結果と表示するメッセージを入れ物（bean)にセットする
        bean.setCategoryname(categoryname);
        bean.setExcess(sisyutu,mokuhyou);

        JihakuBean bean1 = new JihakuBean();
        int sisyutu1 = 10000;
        int mokuhyou1= 5000;
        String categoryname1 = ("食費");
        //計算結果と表示するメッセージを入れ物（bean)にセットする
        bean1.setCategoryname(categoryname1);
        bean1.setExcess(sisyutu1,mokuhyou1);

        jihakulist.getJihakulist().add(bean);
        jihakulist.getJihakulist().add(bean1);
        return jihakulist;
    }

//---------------------------コナン機能---------------------------------------------------------
    //ConanVo型のListをConanBean型のListに変換
    public static ConanListBean selectConanAdvice() {
        List<ConanVo> resultList = AdviceDBManager.selectConanAdvice();
        ConanListBean clb = new ConanListBean();

        int totalDifference = 0;	//月の目標ー支出
        List<ConanBean> list = new ArrayList<ConanBean>();
        for( ConanVo cv: resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName(cv.getCategoryName());

            int difference = cv.getDifference();
            totalDifference += difference;
            cb.setDifference(difference);

            list.add(cb);
        }
        //ConanListBeanに入れる
        clb.setList(list);
        clb.setThisMonth(5);
        clb.setTotalDifference(totalDifference);

        return clb;
    }
}
