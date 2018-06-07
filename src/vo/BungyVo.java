package vo;

public class BungyVo {
    public int mokuhyou;
    public int totalexpenses;

    public BungyVo() {
        super();
    }

    public BungyVo(int mokuhyou, int totalexpenses ) {
        this.mokuhyou = mokuhyou;
        this.totalexpenses = totalexpenses;
    }

    public int getMokuhyou() {
        return mokuhyou;
    }

    public void setMokuhyou(int mokuhyou) {
        this.mokuhyou = mokuhyou;
    }

    public int getTotalexpenses() {
        return totalexpenses;
    }

    public void setTotalexpenses(int totalexpenses) {
        this.totalexpenses = totalexpenses;
    }
}
