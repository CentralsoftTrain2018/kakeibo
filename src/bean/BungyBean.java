package bean;

public class BungyBean {
    private int mokuhyou;
    private int sisyutu;
    private int hanninIchi;
    private String month;
    private String message;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BungyBean [mokuhyou=" + mokuhyou + ", sisyutu=" + sisyutu + ", hanninIchi=" + hanninIchi + ", date="
                + month + ", message=" + message + "]";
    }


}
