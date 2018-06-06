package bean;

public class BungyBean {
    private int mokuhyou;
    private int sisyutu;
    private int hanninIchi;
    private int date;
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
        //return sisyutu/mokuhyou*100;
    }

    public void setHanninIchi(int hanninIchi) {

        this.hanninIchi = hanninIchi;
    }

    public int getDate() {

        return (int)((double)this.date/(double)30*100.0);
    }

    public void setDate(int date) {
        this.date = date;
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
                + date + ", message=" + message + "]";
    }


}
