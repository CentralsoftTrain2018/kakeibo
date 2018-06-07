package vo;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Wed Jun 06 16:18:48 JST 2018
 */
import java.io.Serializable;

/**
 * MokuhyouVo.
 * @author yosuke.ochi
 * @version 1.0 
 * history 
 * Symbol	Date		Person		Note
 * [1]		2018/06/06	yosuke.ochi		Generated.
 */
public class MokuhyouVo implements Serializable{

	public static final String TABLE = "MOKUHYOU";

	/**
	 * mokuhyouId:int(10) <Primary Key>
	 */
	private int mokuhyouid;

	/**
	 * categoryName:varchar(10)
	 */
	private String categoryname;

	/**
	 * Kingaku:int(10)
	 */
	private int kingaku;

	/**
	 * Month:date(0)
	 */
	private java.sql.Date month;

	/**
	 * user_userid:varchar(100)
	 */
	private String user_userid;

	/**
	 * category_categoryId:int(10)
	 */
	private int category_categoryid;

	/**
	* Constractor
	*/
	public MokuhyouVo(){}

	/**
	* Constractor
	* @param <code>mokuhyouid</code>
	*/
	public MokuhyouVo(int mokuhyouid){
		this.mokuhyouid = mokuhyouid;
	}

	public int getMokuhyouid(){ return this.mokuhyouid; }

	public void setMokuhyouid(int mokuhyouid){ this.mokuhyouid = mokuhyouid; }

	public String getCategoryname(){ return this.categoryname; }

	public void setCategoryname(String categoryname){ this.categoryname = categoryname; }

	public int getKingaku(){ return this.kingaku; }

	public void setKingaku(int kingaku){ this.kingaku = kingaku; }

	public java.sql.Date getMonth(){ return this.month; }

	public void setMonth(java.sql.Date month){ this.month = month; }

	public String getUser_userid(){ return this.user_userid; }

	public void setUser_userid(String user_userid){ this.user_userid = user_userid; }

	public int getCategory_categoryid(){ return this.category_categoryid; }

	public void setCategory_categoryid(int category_categoryid){ this.category_categoryid = category_categoryid; }

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[MokuhyouVo:");
		buffer.append(" mokuhyouid: ");
		buffer.append(mokuhyouid);
		buffer.append(" categoryname: ");
		buffer.append(categoryname);
		buffer.append(" kingaku: ");
		buffer.append(kingaku);
		buffer.append(" month: ");
		buffer.append(month);
		buffer.append(" user_userid: ");
		buffer.append(user_userid);
		buffer.append(" category_categoryid: ");
		buffer.append(category_categoryid);
		buffer.append("]");
		return buffer.toString();
	}

}
