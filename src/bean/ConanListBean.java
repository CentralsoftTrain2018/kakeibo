package bean;

import java.util.List;

public class ConanListBean {

    private int totalDifference;
    private int thisMonth;
    private List<ConanBean> list;

    public int getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(int thisMonth) {
        this.thisMonth = thisMonth;
    }

    public int getTotalDifference() {
        return totalDifference;
    }

    public void setTotalDifference(int totalDifference) {
        this.totalDifference = totalDifference;
    }

    public List<ConanBean> getList() {
        return list;
    }

    public void setList(List<ConanBean> list) {
        this.list = list;
    }
//    @Override
//    public String toString() {
//        return thisMonth + "月は目標達成だね！おめでとう(^_^)/♪<br>" +
//                "君のおかげで犯人を逮捕することができたよ！<br>" +
//                "合計目標金額は\t" + totalGoal + "円、\t支出合計は" + totalSpending + "円、\t差額は" + totalDifference + "円\tだよ。<br>";
//    }

}
