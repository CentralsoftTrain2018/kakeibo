package service;

import bean.JihakuBean;
import bean.JihakuListBean;

public class ConanService {

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
        bean1.setExcess(sisyutu,mokuhyou1);

        jihakulist.getJihakulist().add(bean);
        jihakulist.getJihakulist().add(bean1);
        return jihakulist;
    }
}
