package bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BungyBean {
    private int mokuhyou;
    private int sisyutu;
    private int hanninIchi;
    private String nengetsu;
    private String message;

    //今日の日付
    private int today;
    private int thisMonth;
    //指定した年月の日数
    private int days;

    private boolean monthfinflg=false;
    private boolean gameoverflg=false;

    //ユーザーの登録月
    private int registMonth;
    private int registYear;

    public void checkFlgs()
    {
        monthFinCheck();
        gameOverFinCheck();
    }
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
        double yoko=getHanninIchi()%19.0/19.0*12.0;
        if((hanninIchi>=0&&hanninIchi<19)||(hanninIchi>=38&&hanninIchi<57))
        {
            return 12-(int)yoko;
        }

        //犯人の表示位置（高さ）が７５％を超えた場合、特定位置までの横移動に切り替える
        if(hanninIchi>=75)
        {
            int specialYoko=(int)((((double)sisyutu/(double)mokuhyou*100.0-75.0)/25.0)*38.0)+12;
            if(specialYoko>=45)
            {
                specialYoko=45;
            }
            return specialYoko;
        }
        return (int)yoko;
    }
    public boolean isMonthfinflg() {
        return monthfinflg;
    }

    public boolean isGameoverflg() {
        return gameoverflg;
    }

    public int getHanninMuki()
    {
        //犯人画像の表示向きを切り替える
        int hanninIchi=getHanninIchi();
        //犯人の位置が特定位置の場合、元画像から左右反転させる。
        if((hanninIchi>=0&&hanninIchi<19)||(hanninIchi>=38&&hanninIchi<57))
        {
            return -1;
        }
        return 1;
    }
    public void setHanninIchi(int hanninIchi) {

        this.hanninIchi = hanninIchi;
    }

    public int getJumperDispPosition() {

        return  (int)((double)today/(double)days*50.0);
    }
    public String getNengetsu() {
        return nengetsu;
    }

    public void setNengetsu(String nengetsu) {
        this.nengetsu = nengetsu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getButtonImage()
    {
        if(gameoverflg)
        {
            return "image/gameover.png";
        }
        return "image/gameclear.png";
    }
    public String getAdovicePage()
    {
        if(gameoverflg)
        {
            return "JihakuServlet";
        }
        return "ConanServlet";
    }

    private void gameOverFinCheck()
    {
        if(mokuhyou-sisyutu<0)
        {
            gameoverflg=true;
        }
    }

    public int getRegistYear()
    {
        return registYear;
    }

    public void setRegistYear(int registYear)
    {
        this.registYear = registYear;
    }

    public void setRegistMonth(int registMonth)
    {
        this.registMonth = registMonth;
    }

    private void monthFinCheck()
    {
        String[] YearAndMonth = nengetsu.split("/");

        //今日の日付を取得
        Calendar calendar = Calendar.getInstance();
        today=calendar.get(Calendar.DATE);
        thisMonth=calendar.get(Calendar.MONTH)+1;

        //指定年月の日数を取得
        Calendar c = new GregorianCalendar(Integer.parseInt(YearAndMonth[0]),Integer.parseInt(YearAndMonth[1]),1);
        days=c.getActualMaximum(Calendar.DAY_OF_MONTH);

        int month=Integer.parseInt(YearAndMonth[1]);
        //DBに記録されている月が現在の月と異なる場合フラグの切り替え
        if(thisMonth!=month)
        {

            monthfinflg=true;
        }
    }
    @Override
    public String toString() {
        return "BungyBean [mokuhyou=" + mokuhyou + ", sisyutu=" + sisyutu + ", hanninIchi=" + hanninIchi + ", date="
                + nengetsu + ", message=" + message + "]";
    }

}