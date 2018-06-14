package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BunsekiListBean
{
    private List<BunsekiBean> list;
    private int sumSpending = 0;
    private Calendar date;

    public BunsekiListBean()
    {
        list = new ArrayList<BunsekiBean>();
    }

    public List<BunsekiBean> getList()
    {
        return list;
    }

    public void setList( List<BunsekiBean> list )
    {
        this.list = list;
    }

    public void addList( BunsekiBean bb )
    {
        list.add( bb );
    }

    public int getSumSpending()
    {
        return sumSpending;
    }

    public void setSumSpending( int sumSpending )
    {
        this.sumSpending = sumSpending;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate( Calendar date )
    {
        this.date = date;
    }

}
