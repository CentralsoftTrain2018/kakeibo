package bean;

import java.util.ArrayList;
import java.util.List;

public class BunsekiListBean {
    private List<BunsekiBean> list;

    public BunsekiListBean()
    {
        list=new ArrayList<BunsekiBean>();
    }
    public List<BunsekiBean> getList()
    {
        return list;
    }

    public void setList(List<BunsekiBean> list)
    {
        this.list = list;
    }

    public void addList(BunsekiBean bb)
    {
        list.add(bb);
    }
}
