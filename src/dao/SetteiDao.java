package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.SetteiVo;

public class SetteiDao extends Dao {
    private static final String INSERT_CATEGORY =
            "insert into " +
            "	category( " +
            "		categoryname, " +
            "		useflag," +
            "		color," +
            "		user_userid" +
            "	) " +
            "values " +

            "	(?, ? , ?, ?)";
    private static final String INSERT_MOKUHYOU =
            "insert into " +
            "	mokuhyou( " +
            "		kingaku, " +
            "		month," +
            "		user_userid," +
            "		category_categoryId" +
            "	) " +
            "values " +
            "	(?, ? , ?, "+
            "	(select " +
            "		categoryid " +
            "	from " +
            "		category " +
            "	where " +
            "		user_userid = ? " +
            "	and	categoryname = ? ));";

    private static final String UPDATE_CATEGORY =
            "update " +
            "	category " +
            "set " +
            "	categoryname = ? " +
            "where " +
            "	user_userid = ? " +
            "	and categoryname = ?";

    private static final String DELETE_CATEGORY =
            "update " +
            "	category " +
            "set " +
            "	useflag= 0 " +
            "where " +
            "	user_userid = ? " +
            "	and categoryname = ?";

    private static final String SELECT_SYUNYUU =
           "SELECT Income " +
           "FROM user " +
           "WHERE userId = ?;";

    private static final String SELECT_MOKUHYOU =
            "SELECT " +
            "categoryName " +
            ",Kingaku " +
            "FROM mokuhyou m " +
            ",category c " +
            "WHERE " +
            "m.category_categoryId = c.categoryId " +
            "AND c.useflag = 1 " +
            "AND m.user_userId = ? " +
            "AND m.Month = ?;";

    private static final String  UPDATE_MOKUHYOU =
            "update " +
            "	mokuhyou " +
            "set " +
            "	kingaku = ? " +
            "where " +
            "	month = ? " +
            "	and category_categoryid in " +
            "	(select " +
            "		categoryid " +
            "	from " +
            "		category " +
            "	where " +
            "		user_userid = ? " +
            "	and	categoryname = ? )";

    private static final String UPDATE_SYUNYUU =
            "update " +
            "user " +
            "set " +
            "Income = ? " +
            "where " +
            "userId = ?;";


    private static final String UPDATE_PASSWORD =
            "update " +
            "	user " +
            "set " +
            "	Passward = ? " +
            "where " +
            "	userId = ?";


    public SetteiDao(Connection con) {
        super(con);
    }

    //カテゴリーの追加
    //呼び出し元
    //UserDBManager
    public void addCategory(String categoryName,String categoryColor, String userId, String nengetu, int kingaku ) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( INSERT_CATEGORY ); )
        {

            stmt.setString( 1, categoryName );
            stmt.setInt(2, 1);
            stmt.setString(3, categoryColor);
            stmt.setString(4, userId);
            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    public void addNewMokuhyou(String categoryName, String userId, String nengetu, int kingaku ) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( INSERT_MOKUHYOU ); )
        {

            stmt.setInt( 1, kingaku );
            stmt.setString(2, nengetu);
            stmt.setString(3, userId);
            stmt.setString(4, userId);
            stmt.setString(5, categoryName);
            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    //カテゴリーの変更
    //呼び出し元
    //UserDBManager
    public void updateCategory(String newCategoryName, String userId, String oldCategoryName) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE_CATEGORY ); )
        {

            stmt.setString(1, newCategoryName);
            stmt.setString(2, userId);
            stmt.setString(3, oldCategoryName);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    //カテゴリーの削除
    //呼び出し元
    //UserDBManager
    public void deleteCategory(String userId, String categoryName) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( DELETE_CATEGORY ); )
        {
            stmt.setString(1, userId);
            stmt.setString(2, categoryName);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    //収入の取得
    //呼び出し元
    //UserDBManager
    public int getSyunyuu(String userId) throws SQLException
    {

        //System.out.println( month.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_SYUNYUU ); )
        {
            int syunyuu;

            stmt.setString( 1, userId );
            /* SQL実行 */
            rset = stmt.executeQuery();

            /* 取得したデータを表示します。 */

            rset.next();
            syunyuu = (rset.getInt( 1 ));
            return syunyuu;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

    //目標の取得
    //呼び出し元
    //UserDBManager
    public List<SetteiVo> getMokuhyou(String userId,String nengetsu) throws SQLException
    {

        //System.out.println( month.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_MOKUHYOU ); )
        {
            List<SetteiVo> list = new ArrayList<SetteiVo>();

            stmt.setString( 1, userId );
            stmt.setString( 2, nengetsu );
            /* SQL実行 */
            rset = stmt.executeQuery();


            /* 取得したデータを表示します。 */
            while ( rset.next() )
            {
                SetteiVo sv = new SetteiVo();

                sv.setCategoryName(rset.getString( 1 ));
                sv.setMokuhyouKingaku(rset.getInt(2));
                list.add(sv);
            }
            return list;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }

    //パスワードの変更
    //呼び出し元
    //UserDBManager
    public void updatePassword(String userId, String password) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE_PASSWORD ); )
        {

            stmt.setString(1, password);
            stmt.setString(2, userId);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    //収入の更新
    //呼び出し元
    //UserDBManager
    public void updateSyunyuu(String userId, int newIncome) throws SQLException
    {
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE_SYUNYUU ); )
        {

            stmt.setInt(1, newIncome);
            stmt.setString(2, userId);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    //目標の更新
    //呼び出し元
    //UserDBManager
    public void updateMokuhyou(String userId, int newMokuhyoukingaku, String categoryName, String nengetsu) throws SQLException
    {
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE_MOKUHYOU ); )
        {

            stmt.setInt(1, newMokuhyoukingaku);
            stmt.setString(2, nengetsu);
            stmt.setString(3, userId);
            stmt.setString(4, categoryName);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }
}
