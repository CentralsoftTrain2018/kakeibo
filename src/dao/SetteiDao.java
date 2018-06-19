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
            "		categoryname " +
            "	) " +
            "values " +
            "	(?)";

    private static final String UPDATE_CATEGORY =
            "update " +
            "	category " +
            "set " +
            "	categoryname = ? " +
            "where " +
            "	categoryid = ?";

    private static final String DELETE_CATEGORY =
            "update " +
            "	category " +
            "set " +
            "	useflag = 0 " +
            "where " +
            "	categoryid = ?";

    private static final String SELECT_SYUNYUU =
           "SELECT Income " +
           "FROM kakeibo.user" +
           "WHERE userId = ?;";

    private static final String SELECT_MOKUHYOU =
            "SELECT" +
            "categoryName" +
            ",Kingaku" +
            "FROM mokuhyou m" +
            ",category c" +
            "WHERE" +
            "m.category_categoryId = c.categoryId" +
            "AND user_userId = ?" +
            "AND Month = ?;";

    private static final String  UPDATE_MOKUHYOU =
            "update mokuhyou " +
            " " +
            "set Kingaku = 30000 " +
            " " +
            "where " +
            "month = '2018/05' " +
            "AND user_userid = 1 " +
            "AND category_categoryId = 1;";

    private static final String UPDATE_SYUNYUU =
            "update " +
            "user" +
            "set" +
            "Income = 100000" +
            "where" +
            "userId = 1;";


    private static final String UPDATE_PASSWORD =
            "update " +
            "	user " +
            "set " +
            "	password = ? " +
            "where " +
            "	userid = ?";

    private static final String SELECT_PASSWORD =
            "select " +
            "	password " +
            "from " +
            "	user " +
            "where " +
            "	userid = ?";


    public SetteiDao(Connection con) {
        super(con);
    }

    public void addCategory(String categoryName) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( INSERT_CATEGORY ); )
        {

            stmt.setString( 1, categoryName );

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    public void updateCategory(int categoryId, String categoryName) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( UPDATE_CATEGORY ); )
        {

            stmt.setString( 1, categoryName );
            stmt.setInt(2, categoryId);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

    public void deleteCategory(int categoryId) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( DELETE_CATEGORY ); )
        {
            stmt.setInt(1, categoryId);

            /* ｓｑｌ実行 */
            stmt.executeUpdate();
        } catch ( SQLException ex )
        {
            throw ex;
        }
    }

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

    public List<SetteiVo> getMokuhyou(String userId,String nengetsu) throws SQLException
    {

        //System.out.println( month.toString() );
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_MOKUHYOU ); )
        {
            List<SetteiVo> list = new ArrayList<SetteiVo>();

            stmt.setString( 1, userId );
            stmt.setString( 1, nengetsu );
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

    public String getPassword(String userId) throws SQLException {
        ResultSet rset = null;

        try ( PreparedStatement stmt = con.prepareStatement( SELECT_PASSWORD ); )
        {
            stmt.setString( 1, userId );

            rset = stmt.executeQuery();

            String password = "";
            while(rset.next()) {
                password = rset.getString(1);
            }

            return password;
        }

        catch ( SQLException e )
        {
            throw e;
        }
    }
}
