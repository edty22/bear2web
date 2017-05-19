/*
 * Created on 2004/12/11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package bear2web.util;

/**
 * @author endo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.ResultSet;

public class TestDbConnect {

	public static void main(String[] args) {

		System.out.println("--- START ---");
		try {
			DbComponent db = new DbComponent(false);

			String sql =  "select market_cd from market_price";

			ResultSet rs = db.select(sql);

			while (rs.next()) {

				System.out.println(rs.getString("market_cd"));

			}
			rs.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- END ---");

	}
}
