package bean;

public class JihakuBean {
    String categoryname;
    int difference;
    int excess;

    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    public int getDifference() {
        return difference;
    }
    public void setDifference(int difference) {
        this.difference = difference;
    }
    public int getExcess() {
        return excess;
    }
    public void setExcess(int a , int b) {
        this.excess = a-b;
    }
    @Override
    public String toString() {
        return categoryname+"が"+excess+"円<br>";
    }


}
