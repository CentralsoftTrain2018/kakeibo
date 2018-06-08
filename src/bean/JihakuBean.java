package bean;

public class JihakuBean {
    private String categoryname;
    private int excess;

    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    public int getExcess() {
        return excess;
    }
    public void setExcess(int excess) {
        this.excess = excess;
    }
    @Override
    public String toString() {
        return categoryname+"が"+excess+"円<br>";
    }


}
