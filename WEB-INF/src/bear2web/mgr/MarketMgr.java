package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Market;
import bear2web.util.DbComponent;

public class MarketMgr {

	private static Logger _log = Logger.getLogger(MarketMgr.class);	// ���O

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Market> getAllMarkets() throws Exception {

		DbComponent db = new DbComponent(false);
		List<Market> markets = null;

		try {

			String sql = "SELECT market_cd, market_name, type, market, unit, regist_date, update_date "
					+ " FROM mt_market "
					+ " ORDER BY type ,market ,market_cd ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (markets == null) {
					markets = new ArrayList<Market>();
				}

				Market market = new Market();
				market.setMarketCd( rs.getString("market_cd") );
				market.setMarketName( rs.getString("market_name") );
				market.setType( rs.getString("type") );
				market.setMarket( rs.getString("market") );
				market.setUnit( rs.getInt("unit") );

				markets.add( market );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return markets;

	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Market getMarketByCd(String marketCd) throws Exception {

		DbComponent db = new DbComponent(false);
		Market market = null;

		try {

			String sql = "SELECT market_cd, market_name, type, market, unit, regist_date, update_date "
					+ " FROM mt_market "
					+ " WHERE market_cd = '" + marketCd + "'";

			ResultSet rs = db.select( sql );

			if ( rs.next() ) {

				market = new Market();
				market.setMarketCd( rs.getString("market_cd") );
				market.setMarketName( rs.getString("market_name") );
				market.setType( rs.getString("type") );
				market.setMarket( rs.getString("market") );
				market.setUnit( rs.getInt("unit") );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return market;

	}

}
