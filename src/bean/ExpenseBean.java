package bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ExpenseBean
{
    private String message;
    private Calendar date;
    //その月の1日が何曜日か0なら日曜日
    private int startDayOfTheWeek;
    //その月が何日まであるか
    private int endDay;
    //一日の合計が30日分入っている配列
    private int[] expenses;

    private List<CategoryBean> categoryList;

    public ExpenseBean()
    {
        super();
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate( Calendar date )
    {
        this.date = date;
    }

    public int getStartDayOfTheWeek()
    {
        return startDayOfTheWeek;
    }

    public void setStartDayOfTheWeek( int startDayOfTheWeek )
    {
        this.startDayOfTheWeek = startDayOfTheWeek;
    }

    public int getEndDay()
    {
        return endDay;
    }

    public void setEndDay( int endDay )
    {
        this.endDay = endDay;
    }

    public List<CategoryBean> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList( List<CategoryBean> categoryList )
    {
        this.categoryList = categoryList;
    }

    public int[] getExpenses()
    {
        return expenses;
    }

    public void setExpenses( int[] expenses )
    {
        this.expenses = expenses;
    }

    @Override
    public String toString()
    {
        return "ExpenseBean [message=" + message + ", date=" + date + ", startDayOfTheWeek=" + startDayOfTheWeek
                + ", endDay=" + endDay + ", expenses=" + Arrays.toString( expenses ) + "]";
    }

}
