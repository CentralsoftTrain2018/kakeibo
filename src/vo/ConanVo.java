package vo;

public class ConanVo {
    private String categoryName;
    private int difference;

    public ConanVo() {
        super();
    }

    public ConanVo(String categoryName) {
        super();
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "ConanVo [categoryName=" + categoryName + ", difference=" + difference + "]";
    }

}
