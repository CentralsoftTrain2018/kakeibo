package bean;

public class ConanBean {
    private String categoryName;
    private int sumSpending;		//支出合計
    private int mokuhyouKingaku;	//目標金額
    private int difference;		//差額（目標ー支出）

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSumSpending() {
        return sumSpending;
    }

    public void setSumSpending(int sumSpending) {
        this.sumSpending = sumSpending;
    }

    public int getMokuhyouKingaku() {
        return mokuhyouKingaku;
    }

    public void setMokuhyouKingaku(int mokuhyouKingaku) {
        this.mokuhyouKingaku = mokuhyouKingaku;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = this.mokuhyouKingaku - this.sumSpending;
    }

    @Override
    public String toString() {
        return categoryName + "の目標金額は" + mokuhyouKingaku + "円、\t" + "支出合計は" + sumSpending + "円、\t差額は" + difference + "円\tだよ。";
    }
}
