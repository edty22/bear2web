package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Price;
import bear2web.util.DbComponent;

public class PriceMgr {

	private static Logger _log = Logger.getLogger(PriceMgr.class);	//

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Price> getAllPricesByCd(String marketCd) throws Exception {

		DbComponent db = new DbComponent(false);
		List<Price> prices = null;

		try {

			String sql = "SELECT market_cd, regist_date, start_price, high_price, low_price, end_price, "
					+ " volume "
					+ " FROM market_price "
					+ " WHERE market_cd = '" + marketCd + "' "
					+ " ORDER BY regist_date DESC ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (prices == null) {
					prices = new ArrayList<Price>();
				}

				Price price = new Price();
				price.setMarketCd(rs.getString("market_cd"));
				price.setRegistDate(rs.getString("regist_date"));
				price.setStartPrice(rs.getDouble("start_price"));
				price.setHighPrice(rs.getDouble("high_price"));
				price.setLowPrice(rs.getDouble("low_price"));
				price.setEndPrice(rs.getDouble("end_price"));
				price.setVolume(rs.getDouble("volume"));

				prices.add( price );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return prices;

	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Price getPriceByCdDate(String marketCd ,String registDate) throws Exception {

		DbComponent db = new DbComponent(false);
		Price price = null;

		try {

			String sql = "SELECT market_cd, regist_date, start_price, high_price, low_price, end_price, "
					+ " volume "
					+ " FROM market_price "
					+ " WHERE market_cd = '" + marketCd + "' "
					+ " AND regist_date = '" + registDate + "' "
					+ " ORDER BY regist_date DESC ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				price = new Price();
				price.setMarketCd(rs.getString("market_cd"));
				price.setRegistDate(rs.getString("regist_date"));
				price.setStartPrice(rs.getDouble("start_price"));
				price.setHighPrice(rs.getDouble("high_price"));
				price.setLowPrice(rs.getDouble("low_price"));
				price.setEndPrice(rs.getDouble("end_price"));
				price.setVolume(rs.getDouble("volume"));


			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return price;

	}


	/**
	 * 価格を一件登録
	 */
	public void registPrice( Price price ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "INSERT INTO market_price("
	        		+ "market_cd, regist_date, start_price, high_price, low_price, end_price ,volume)"
	        		+ "VALUES ('"
	        		+ price.getMarketCd() + "' ,'"
	        		+ price.getRegistDate() + "', "
	        		+ price.getStartPrice() + ", "
	        		+ price.getHighPrice() + ", "
	        		+ price.getLowPrice() + ", "
	        		+ price.getEndPrice() + ","
	        		+ price.getVolume() + ")";

	        //System.out.println( sql );

	        try {
	        	db.insert(sql);
	        	db.commit();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	db.rollback();
	        }

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

	}

	/**
	 * 価格を一件更新
	 * キー（マーケットコードと登録日）
	 */
	public void updatePrice( Price price ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "UPDATE market_price SET "
	        		+ "start_price = " + price.getStartPrice() + ", "
	        		+ "high_price = " +price.getHighPrice()  + ", "
	        		+ "low_price = " + price.getLowPrice() + ", "
	        		+ "end_price = " + price.getEndPrice() + ", "
	        		+ "volume = " + price.getVolume()
	        		+ " WHERE market_cd = '" + price.getMarketCd() + "' "
	        		+ " AND regist_date = '" + price.getRegistDate() + "' ";

	        //System.out.println( sql );

	        try {
	        	db.insert(sql);
	        	db.commit();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	db.rollback();
	        }

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

	}

	/**
	 * 価格を一件削除
	 * キー（マーケットコードと登録日）
	 */
	public void deletePrice( Price price ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "DELETE FROM market_price "
	        		+ " WHERE market_cd = '" + price.getMarketCd() + "' "
	        		+ " AND regist_date = '" + price.getRegistDate() + "' ";

	        //System.out.println( sql );

	        try {
	        	db.insert(sql);
	        	db.commit();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	db.rollback();
	        }

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

	}


}
