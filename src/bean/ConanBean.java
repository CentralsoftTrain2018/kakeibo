package bean;

public class ConanBean {
    int thisMonth;
    int nextMonth;
    int total;
    String goodCategory;
    int goodDifference;
    String badCategory;
    int badDifference;

    public int getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(int thisMonth) {
        this.thisMonth = thisMonth;
    }

    public int getNextMonth() {
        return nextMonth;
    }

    public void setNextMonth(int nextMonth) {
        this.nextMonth = nextMonth;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getGoodCategory() {
        return goodCategory;
    }

    public void setGoodCategory(String goodCategory) {
        this.goodCategory = goodCategory;
    }

    public int getGoodDifference() {
        return goodDifference;
    }

    public void setGoodDifference(int googDifference) {
        this.goodDifference = googDifference;
    }

    public String getBadCategory() {
        return badCategory;
    }

    public void setBadCategory(String badCategory) {
        this.badCategory = badCategory;
    }

    public int getBadDifference() {
        return badDifference;
    }

    public void setBadDifference(int badDifference) {
        this.badDifference = badDifference;
    }

    @Override
    public String toString() {
        return thisMonth + "月は目標達成だね！おめでとう(^_^)/♪<br>" +
                "君のおかげで犯人を逮捕することができたよ！<br>" +
                "目標金額より" + total + "円節約できたね。えらい！！<br>" +
                "一番節約したのは" + goodCategory + "で、" + goodDifference + "円も節約できたね！<br>" +
                "でも" + badCategory + "は" + badDifference + "円オーバーしちゃったね･･･。気をつけよう！<br>" +
                "この調子で" + nextMonth + "月もがんばってね！";
    }

}
