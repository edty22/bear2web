package bear2web.util;

import java.sql.ResultSet;

import bear2web.model.Price;


public class PriceUtil {


	/**
	 * 初期化
	 */
	public PriceUtil() {
		super();

	}


	/**
	 *
	 */
	public Price getPriceOfDays(String marketCd ,String strDate , int num) {

		Price result = null;
		DbComponent dc = null;
		try {
			dc = new DbComponent();

			String sql ="SELECT market_cd, regist_date, start_price, high_price, low_price, end_price ,volume"
					+ " FROM public.market_price "
					+ " WHERE market_cd = '" + marketCd + "' ";

			if (num > 0) {

				sql += " AND regist_date > '" + strDate + "' "
					+ " ORDER BY regist_date ASC "
					+ " LIMIT " + num
					+ " OFFSET 0 ";


			} else if (num < 0) {

				sql += " AND regist_date < '" + strDate + "' "
					+ " ORDER BY regist_date DESC "
					+ " LIMIT " + (-num)
					+ " OFFSET 0 ";

				//System.out.println( sql );


			} else {  //

				//return result;
				sql += " AND regist_date = '" + strDate + "' "
						+ " ORDER BY regist_date DESC ";

			}

			ResultSet rs1 = dc.select(sql);

			int cnt = 0;
			while (rs1.next()) {

				cnt++;

				if (cnt == Math.abs(num)) {

					result = new Price();
					result.setRegistDate(rs1.getString("regist_date"));
					result.setMarketCd(rs1.getString("market_cd"));
					result.setHighPrice(rs1.getDouble("high_price"));
					result.setLowPrice(rs1.getDouble("low_price"));
					result.setStartPrice(rs1.getDouble("start_price"));
					result.setEndPrice(rs1.getDouble("end_price"));
					result.setVolume(rs1.getDouble("volume"));

					break;

				} else if (num == 0) {

					result = new Price();
					result.setRegistDate(rs1.getString("regist_date"));
					result.setMarketCd(rs1.getString("market_cd"));
					result.setHighPrice(rs1.getDouble("high_price"));
					result.setLowPrice(rs1.getDouble("low_price"));
					result.setStartPrice(rs1.getDouble("start_price"));
					result.setEndPrice(rs1.getDouble("end_price"));
					result.setVolume(rs1.getDouble("volume"));

					break;

				}

			}
			rs1.close();
			dc.close();

			//System.out.println(result.toString());


		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result == null) {
			result = new Price();

		}
		return result;

	}

	/**
	 *
	 */
	public int compePriceToHighLow(double price1 ,double price2)
		throws Exception {

		int result = 0;

		if (price1 > price2) {
			result = 1;
		}

		return result;

	}

}
