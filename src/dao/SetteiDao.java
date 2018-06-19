package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public SetteiDao(Connection con) {
        super(con);
    }

    public void addCategory(String categoryName) throws SQLException {
        try ( PreparedStatement stmt = con.prepareStatement( INSERT_CATEGORY ); )
        {

            stmt.setString( 1, (categoryName) );

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

            stmt.setString( 1, (categoryName) );
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
}
