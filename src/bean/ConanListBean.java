package bean;

import java.util.List;

public class ConanListBean
{

    private int totalDifference;
    private int thisMonth;
    private String nengetsu;
    private List<ConanBean> list;

    public int getThisMonth()
    {
        return thisMonth;
    }

    public void setThisMonth( int thisMonth )
    {
        this.thisMonth = thisMonth;
    }

    public int getTotalDifference()
    {
        return totalDifference;
    }

    public void setTotalDifference( int totalDifference )
    {
        this.totalDifference = totalDifference;
    }

    public List<ConanBean> getList()
    {
        return list;
    }

    public void setList( List<ConanBean> list )
    {
        this.list = list;
    }

    public String getNengetsu()
    {
        return nengetsu;
    }

    public void setNengetsu( String nengetsu )
    {
        this.nengetsu = nengetsu;
    }

}
