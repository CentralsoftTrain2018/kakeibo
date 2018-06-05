package bean;

public class ConanBean {
    String categoryName;
    int goal;
    int spending;
    int difference;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getSpending() {
        return spending;
    }

    public void setSpending(int spending) {
        this.spending = spending;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return categoryName + "の目標金額は" + goal + "円、\t" + "支出合計は" + spending + "円、\t差額は" + difference + "円\tだよ。";
    }
}
