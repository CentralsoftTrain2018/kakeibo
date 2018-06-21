package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BungyDao extends Dao
{

     private static final String GOUKEI = "SELECT" +
             "			SUM(e.Kingaku)" +
             "			FROM" +
             "			expenses as e" +
             "			WHERE" +
             "           DATE_FORMAT(e.expenseDate, '%Y/%m') = ? " +
             "			AND e.user_userid = ?;";

     private static final String MOKUHYOU = "SELECT" +
             "			SUM(m.Kingaku)" +
             "			FROM" +
             "			mokuhyou as m" +
             "			WHERE" +
             "			m.Month = ?" +
             "			AND m.user_userid = ?;";

    public BungyDao( Connection con )
    {
        super( con );
    }

    //-------------------------------------------------------
    // カテゴリ名、支出合計、目標

    /**
     * 月全体の支出合計を取得
     * @param nengetsu
     * @param userId
     * @return　月全体の支出合計
     * @throws SQLException
     */
        public int getSisyutuGoukei( String nengetsu, String userId ) throws SQLException
        {

            //System.out.println( month.toString() );
            ResultSet rset = null;

            try ( PreparedStatement stmt = con.prepareStatement( GOUKEI ); )
            {
                int goukei;

                stmt.setString( 1, nengetsu );
                stmt.setString( 2, userId );
                /* SQL実行 */
                rset = stmt.executeQuery();

                /* 取得したデータを表示します。 */

                rset.next();
                goukei = (rset.getInt( 1 ));
                return goukei;
            }

            catch ( SQLException e )
            {
                throw e;
            }
        }
    /**
     * その月全体の目標額を取得
     * @param nengetsu
     * @param userId
     * @return　月全体の目標額
     * @throws SQLException
     */
        public int getTsukiMokuhyou( String nengetsu, String userId ) throws SQLException
        {

            ResultSet rset = null;

            try ( PreparedStatement stmt = con.prepareStatement( MOKUHYOU ) )
            {
                int mokuhyou;

                stmt.setString( 1, nengetsu );
                stmt.setString( 2, userId );
                /* SQL実行 */
                rset = stmt.executeQuery();

                /* 取得したデータを表示します。 */
                rset.next();
                mokuhyou = (rset.getInt( 1 ));
                return mokuhyou;
            }

            catch ( SQLException e )
            {
                throw e;
            }
        }

}
