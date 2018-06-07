package service;

import java.util.ArrayList;
import java.util.List;

import bean.ConanBean;
import bean.ConanListBean;
import bean.JihakuBean;
import bean.JihakuListBean;
import dbmanager.AdviceDBManager;
import vo.AdviceVo;

public class AdviceService {

    //AdviceVo型のListをConanBean型のListに変換
    public JihakuListBean jihaku() {
        List<AdviceVo> resultList = AdviceDBManager.selectJihakuAdvice();
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
        List<AdviceVo> resultList = AdviceDBManager.selectConanAdvice();
        ConanListBean clb = new ConanListBean();

        int totalGoal = 0;		//月の目標
        int totalSpending = 0;	//月の支出合計
        int totalDifference = 0;	//月の目標ー支出
        List<ConanBean> list = new ArrayList<ConanBean>();
        for( AdviceVo av: resultList )
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName(av.getCategoryName());

            int spanding = av.getSumSpending();
            totalSpending += spanding;
            cb.setSumSpending(spanding);

            int goal = av.getMokuhyouKingaku();
            totalGoal += goal;
            cb.setMokuhyouKingaku(goal);

            int difference = av.getDifference();
            totalDifference += difference;
            cb.setDifference(difference);

            list.add(cb);
        }
        //ConanListBeanに入れる
        clb.setList(list);
        clb.setThisMonth(5);
        clb.setTotalGoal(totalGoal);
        clb.setTotalSpending(totalSpending);
        clb.setTotalDifference(totalDifference);

        return clb;
    }
}
