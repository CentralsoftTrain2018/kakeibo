package bean;

import java.util.Calendar;
import java.util.List;

public class ConanListBean
{
    private int totalDifference;
    private Calendar date;
    private String nengetsu;
    private List<ConanBean> list;

    public Calendar getDate()
    {
        return date;
    }

    public void setDate( Calendar date )
    {
        this.date = date;
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
