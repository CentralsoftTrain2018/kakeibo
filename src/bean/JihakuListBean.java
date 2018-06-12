package bean;

import java.util.List;

public class JihakuListBean
{
    private List<JihakuBean> jihakulist;
    private String nengetsu;
    private int goukei;
    private int mokuhyou;

    public String getNengetsu()
    {
        return nengetsu;
    }

    public void setNengetsu( String nengetsu )
    {
        this.nengetsu = nengetsu;
    }

    public List<JihakuBean> getJihakulist()
    {
        return jihakulist;
    }

    public void setJihakulist( List<JihakuBean> jihakulist )
    {
        this.jihakulist = jihakulist;
    }


    public int getGoukei() {
        return goukei;
    }

    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }

    public int getMokuhyou() {
        return mokuhyou;
    }

    public void setMokuhyou(int mokuhyou) {
        this.mokuhyou = mokuhyou;
    }

    public String toString()
    {
        StringBuffer kakujihaku = new StringBuffer();
        for ( JihakuBean jihakub : jihakulist )
        {
            String jihakus = jihakub.toString();
            kakujihaku.append( jihakus );
        }
        return kakujihaku.toString();
    }

}
