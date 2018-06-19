package bean;

import java.util.List;

import vo.SetteiVo;

public class SetteiBean {
    private String categoryname;
    private List<SetteiVo> categoryMokuhyouList;
    private int syunyuu;
    private String userId;

    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
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


}
