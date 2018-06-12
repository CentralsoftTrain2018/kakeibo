package vo;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Tue Jun 12 15:28:00 JST 2018
 */
import java.io.Serializable;

/**
 * CategoryVo.
 * @author yuka.tokoro
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 */
public class CategoryVo implements Serializable{

    public static final String TABLE = "CATEGORY";

    /**
     * categoryId:int(10) <Primary Key>
     */
    private int categoryid;

    /**
     * categoryName:varchar(10)
     */
    private String categoryname;

    /**
     * useflag:tinyint(3)
     */
    private byte useflag;

    /**
    * Constractor
    */
    public CategoryVo(){}

    /**
    * Constractor
    * @param <code>categoryid</code>
    */
    public CategoryVo(int categoryid){
        this.categoryid = categoryid;
    }

    public int getCategoryid(){ return this.categoryid; }

    public void setCategoryid(int categoryid){ this.categoryid = categoryid; }

    public String getCategoryname(){ return this.categoryname; }

    public void setCategoryname(String categoryname){ this.categoryname = categoryname; }

    public byte getUseflag(){ return this.useflag; }

    public void setUseflag(byte useflag){ this.useflag = useflag; }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("[CategoryVo:");
        buffer.append(" categoryid: ");
        buffer.append(categoryid);
        buffer.append(" categoryname: ");
        buffer.append(categoryname);
        buffer.append(" useflag: ");
        buffer.append(useflag);
        buffer.append("]");
        return buffer.toString();
    }

}
