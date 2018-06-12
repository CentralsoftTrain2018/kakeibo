package bean;

import java.util.Calendar;

public class ExpenseBean {
    private String message;
    private Calendar date;
    //その月の1日が何曜日か0なら日曜日
    private int startDayOfTheWeek;
    //その月が何日まであるか
    private int endDay;

    public ExpenseBean() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getStartDayOfTheWeek() {
        return startDayOfTheWeek;
    }

    public void setStartDayOfTheWeek(int startDayOfTheWeek) {
        this.startDayOfTheWeek = startDayOfTheWeek;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "KakeiboBean [message=" + message + ", date=" + date + ", startDayOfTheWeek=" + startDayOfTheWeek
                + ", endDay=" + endDay + "]";
    }
}
