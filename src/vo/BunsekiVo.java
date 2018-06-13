package vo;

public class BunsekiVo {

    private String categoryName;
    private int sumSpending;
    private int mokuhyouKingaku;
    private int difference;

    public BunsekiVo() {
        super();
    }

    public BunsekiVo(String categoryName, int sumSpending, int mokuhyouKingaku) {
        super();
        this.categoryName = categoryName;
        this.sumSpending = sumSpending;
        this.mokuhyouKingaku = mokuhyouKingaku;
    }

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
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "ConanVo [categoryName="
                + categoryName + ", sumSpending=" + sumSpending + ", mokuhyouKingaku=" + mokuhyouKingaku + "]";
    }

}
