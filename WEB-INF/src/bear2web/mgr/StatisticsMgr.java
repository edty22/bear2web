package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Statistics;
import bear2web.util.DbComponent;

public class StatisticsMgr {

	private static Logger _log = Logger.getLogger(StatisticsMgr.class);	//

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Statistics> getStatisticsByCd(String patternCd ,String sortStr) throws Exception {

		DbComponent db = new DbComponent(false);
		List<Statistics> stes = null;

		String sortText = "total_cnt DESC ,summary_value ,target_cnt DESC,target_ratio DESC";
		// ToDo 並び順

		try {

			String sql = "SELECT pattern_cd ,target_cnt, total_cnt, target_ratio, target_value, summary_value, "
					+ " disp_value "
					+ " FROM work_summary2 "
					+ " WHERE pattern_cd = '" + patternCd + "' "
					+ " ORDER BY " + sortText;

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (stes == null) {
					stes = new ArrayList<Statistics>();
				}

				Statistics st = new Statistics();
				st.setPatternCd(patternCd);
				st.setTargetCnt(rs.getInt("target_cnt"));

				double targetRatio = (rs.getInt("target_cnt") / rs.getInt("total_cnt")) * 100;

				st.setTargetRatio( targetRatio );
				st.setTargetValue(rs.getInt("target_value"));
				st.setTotalCnt(rs.getInt("total_cnt"));
				st.setDispValue(rs.getString("disp_value"));
				st.setSummaryValue(rs.getString("summary_value"));

				stes.add(st);

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return stes;

	}

	/**
	 * 符号パターンから対象取得
	 * @return
	 * @throws Exception
	 */
	public List<Statistics> getStatisticsByCdPtnStr(String patternCd ,String ptnStr) throws Exception {

		DbComponent db = new DbComponent(false);
		List<Statistics> stes = null;

		String sortText = "total_cnt DESC ,summary_value ,target_cnt DESC,target_ratio DESC";
		// ToDo 並び順

		try {

			String sql = "SELECT pattern_cd ,target_cnt, total_cnt, target_ratio, target_value, summary_value, "
					+ " disp_value "
					+ " FROM work_summary2 "
					+ " WHERE pattern_cd = '" + patternCd + "' "
					+ " AND summary_value = '" + ptnStr + "' "
					+ " ORDER BY " + sortText;

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (stes == null) {
					stes = new ArrayList<Statistics>();
				}

				Statistics st = new Statistics();
				st.setPatternCd(patternCd);
				st.setTargetCnt(rs.getInt("target_cnt"));

				double targetRatio = (rs.getInt("target_cnt") / rs.getInt("total_cnt")) * 100;

				st.setTargetRatio( targetRatio );
				st.setTargetValue(rs.getInt("target_value"));
				st.setTotalCnt(rs.getInt("total_cnt"));
				st.setDispValue(rs.getString("disp_value"));
				st.setSummaryValue(rs.getString("summary_value"));

				stes.add(st);

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return stes;

	}


}
