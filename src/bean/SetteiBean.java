package bean;

import java.util.List;

import vo.SetteiVo;

public class SetteiBean {
    //カテゴリー毎の目標金額、カテゴリー名、カテゴリーIDを持つリスト
    private List<SetteiVo> categoryMokuhyouList;

    //現在の収入を表示するための変数
    private int syunyuu;

    //現在の項目ごとの目標金額の合計を表示するための変数
    private int mokuhyougoukei;

    private String userId;

    //現在のパスワードを表示するための変数
    private String userPass;

    //画面遷移の際前回表示していた画面が何かを記録するための変数
    private String disp;

    public List<SetteiVo> getCategoryMokuhyouList() {
        return categoryMokuhyouList;
    }
    public void setCategoryMokuhyouList(List<SetteiVo> categoryMokuhyouList) {
        this.categoryMokuhyouList = categoryMokuhyouList;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getSyunyuu() {
        return syunyuu;
    }
    public void setSyunyuu(int syunyuu) {
        this.syunyuu = syunyuu;
    }
    public int getMokuhyougoukei() {
        return mokuhyougoukei;
    }
    public void setMokuhyougoukei(int mokuhyougoukei) {
        this.mokuhyougoukei = mokuhyougoukei;
    }
    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    public String getDisp() {
        return disp;
    }
    public void setDisp(String disp) {
        this.disp = disp;
    }


}
