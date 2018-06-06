package bean;

public class BungyBean {
    private int mokuhyou;
    private int sisyutu;
    private int hanninIchi;
    private int date;

    public BungyBean() {
        super();
    }

    public int getMokuhyou() {
        return mokuhyou;
    }
    public void setMokuhyou(int mokuhyou) {
        this.mokuhyou = mokuhyou;
    }
    public int getSisyutu() {
        return sisyutu;
    }
    public void setSisyutu(int sisyutu) {
        this.sisyutu = sisyutu;
    }
    public int getHanninIchi() {
        return hanninIchi;
    }
    public void setHanninIchi(int hanninIchi) {
        this.hanninIchi = hanninIchi;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BungyBean [mokuhyou=" + mokuhyou + ", sisyutu=" + sisyutu + ", hanninIchi=" + hanninIchi + ", date="
                + date + "]";
    }
}
