package bean;

import java.util.ArrayList;
import java.util.List;

public class JihakuListBean {
    private List<JihakuBean> jihakulist = new ArrayList<JihakuBean>();

    public List<JihakuBean> getJihakulist() {
        return jihakulist;
    }

    public void setJihakulist(List<JihakuBean> jihakulist) {
        this.jihakulist = jihakulist;
    }

    public String toString() {
        StringBuffer kakujihaku = new StringBuffer();
        for(JihakuBean jihakub: jihakulist) {
            String jihakus = jihakub.toString();
            kakujihaku.append(jihakus);
        }
        return kakujihaku.toString();
    }


}
