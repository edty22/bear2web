package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Pattern;
import bear2web.model.PatternDetail;
import bear2web.model.Statistics;
import bear2web.util.DbComponent;

public class OracleMgr {

	private static Logger _log = Logger.getLogger(OracleMgr.class);	//

	/**
	 * 対象マーケットコードと日付を引数に一致する集計パターンを探す
	 *
	 *
	 */
	public List<Statistics> predictData(String marketCd ,String registDate ) throws Exception {

		DbComponent db = new DbComponent(false);

		List<Statistics> results = null;
		try {

			results = new ArrayList<Statistics>();

			//== 対象マーケットコードからパターンを引く
			String sql1 = "SELECT pattern_cd, pattern_name, target_market_cd, target_atype, memo, "
					+ " regist_date, update_date "
					+ " FROM mt_pattern "
					+ " WHERE target_market_cd = '" + marketCd + "' ";

			ResultSet rs1 = db.select(sql1);

			StatisticsMgr sm = new StatisticsMgr();

			PatternMgr pm = new PatternMgr();

			while (rs1.next()) {

				String patternCd = rs1.getString("pattern_cd");

				//== ここから符号パターン取得
				Pattern ptn = pm.getPatternByCd( patternCd );

				List<PatternDetail> pds = ptn.getPatternDetails();

				String summaryValue = "";

				for (Iterator<PatternDetail> it = pds.iterator(); it.hasNext();) {
					PatternDetail pd = (PatternDetail) it.next();

					summaryValue += pm.getSignalData(pd.getMarketCd() ,registDate ,pd.getPreDayCnt());

				}

				List<Statistics> st = sm.getStatisticsByCdPtnStr(patternCd, summaryValue);

				for (Iterator<Statistics> it = st.iterator(); it.hasNext();) {
					Statistics tmpSt = (Statistics) it.next();

					results.add(tmpSt);

				}

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return  results;

	}


}
