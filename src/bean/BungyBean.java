package bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BungyBean {
    private int mokuhyou;
    private int sisyutu;
    private int hanninIchi;
    private String month;
    private String message;
    private boolean finflg=false;

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
        //犯人の表示位置（高さ）の設定。パーセントで変化する
        int ichi=(int)((double)sisyutu/(double)mokuhyou*100.0);
        //犯人の表示位置が７５％に達すると、それ以上高さが変化しなくなる
        if(ichi>=75)
        {
            return 75;
        }
        return ichi;
    }
    public int getHanninYoko()
    {
        //犯人の表示位置（横）の設定
        int hanninIchi=getHanninIchi();
        //表示されている画像に合わせて表示位置を調整する
        double yoko=getHanninIchi()%19.0/19.0*15.0;
        if(hanninIchi>=0&&hanninIchi<19||hanninIchi>=38&&hanninIchi<57)
        {
            return 15-(int)yoko;
        }
        //犯人の表示位置（高さ）が７５％を超えた場合、特定位置までの横移動に切り替える
        if(hanninIchi>=75)
        {
            return (int)((((double)sisyutu/(double)mokuhyou*100.0-75.0)/25.0)*35.0)+15;
        }
        return (int)yoko;
    }
    public int getHanninMuki()
    {
        //犯人画像の表示向きを切り替える
        int hanninIchi=getHanninIchi();
        //犯人の位置が特定位置の場合、元画像から左右反転させる。
        if(hanninIchi>=0&&hanninIchi<19||hanninIchi>=38&&hanninIchi<57)
        {
            return -1;
        }
        return 1;
    }
    public void setHanninIchi(int hanninIchi) {

        this.hanninIchi = hanninIchi;
    }

    public int getMonth() {
        String[] YearAndMonth = month.split("/");
        //今日の日付を取得
        Calendar calendar = Calendar.getInstance();
        int today=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH);
        //指定年月の日数を取得
        Calendar c = new GregorianCalendar(Integer.parseInt(YearAndMonth[0]),Integer.parseInt(YearAndMonth[1]),1);
        int days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //DBに記録されている月が現在の月と異なる場合フラグの切り替え
        if(month==Integer.parseInt(YearAndMonth[0]))
        {
            finflg=true;
        }
        return  (int)((double)today/(double)days*50.0);
    }
        /**
    public int getDate() {
        //バンジージャンパの表示位置と紐の長さの調整を行う

    }
    **/

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
