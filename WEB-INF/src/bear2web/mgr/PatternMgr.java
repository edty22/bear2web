package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Pattern;
import bear2web.model.PatternDetail;
import bear2web.model.Price;
import bear2web.util.DbComponent;
import bear2web.util.PriceUtil;

public class PatternMgr extends Thread {

	private static Logger _log = Logger.getLogger(PatternMgr.class);	//

	/**
	 * マルチスレッド実行用
	 * @param patternCd
	 */
	public void start(String patternCd) {

		try {
			calcPattern(patternCd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * すべてのパターンを取得
	 * @return
	 * @throws Exception
	 */
	public List<Pattern> getAllPattern() throws Exception {

		DbComponent db = new DbComponent(false);
		DbComponent db2 = new DbComponent(false);
		List<Pattern> patterns = null;

		try {

			String sql = "SELECT pattern_cd, pattern_name, target_market_cd, target_atype, memo, "
					+ " regist_date, update_date "
					+ " FROM mt_pattern "
					+ " ORDER BY pattern_cd ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (patterns == null) {
					patterns = new ArrayList<Pattern>();
				}

				Pattern pattern = new Pattern();
				pattern.setPatternCd(rs.getString("pattern_cd"));
				pattern.setPatternName(rs.getString("pattern_name"));
				pattern.setMemo(rs.getString("memo"));
				pattern.setTargetMarketCd(rs.getString("target_market_cd"));
				pattern.setTargetAtype(rs.getString("target_atype"));

				String sql2 = "SELECT pattern_cd, seq_id, market_cd, pre_day_cnt, pattern_atype, regist_date, "
						+ " update_date "
						+ " FROM mt_pattern_detail "
						+ " WHERE pattern_cd = '" + rs.getString("pattern_cd") + "' "
						+ " ORDER BY seq_id ";

				ResultSet rs2 = db2.select( sql2 );

				List<PatternDetail> patternDetails = new ArrayList<PatternDetail>();

				while ( rs2.next() ) {

					PatternDetail pd = new PatternDetail();
					pd.setPatternCd( rs2.getString("pattern_cd") );
					pd.setSeqId( rs2.getInt("seq_id") );
					pd.setMarketCd( rs2.getString("market_cd") );
					pd.setPreDayCnt( rs2.getInt("pre_day_cnt") );
					pd.setPatternAtype( rs2.getString("pattern_atype") );

					patternDetails.add( pd );

				}

				pattern.setPatternDetails( patternDetails );

				rs2.close();

				patterns.add( pattern );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db2.close();
			db.close();

		}

		return patterns;

	}

	/**
	 * コードを指定してパターンを取得
	 * @return
	 * @throws Exception
	 */
	public Pattern getPatternByCd( String patternCd ) throws Exception {

		DbComponent db = new DbComponent(false);
		DbComponent db2 = new DbComponent(false);
		Pattern pattern = null;

		try {

			String sql = "SELECT pattern_cd, pattern_name, target_market_cd, target_atype, memo, "
					+ " regist_date, update_date "
					+ " FROM mt_pattern "
					+ " WHERE pattern_cd = '" + patternCd + "' ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				pattern = new Pattern();
				pattern.setPatternCd(rs.getString("pattern_cd"));
				pattern.setPatternName(rs.getString("pattern_name"));
				pattern.setMemo(rs.getString("memo"));
				pattern.setTargetMarketCd(rs.getString("target_market_cd"));
				pattern.setTargetAtype(rs.getString("target_atype"));

				String sql2 = "SELECT pattern_cd, seq_id, market_cd, pre_day_cnt, pattern_atype, regist_date, "
						+ " update_date "
						+ " FROM mt_pattern_detail "
						+ " WHERE pattern_cd = '" + rs.getString("pattern_cd") + "' "
						+ " ORDER BY seq_id ";

				ResultSet rs2 = db2.select( sql2 );

				List<PatternDetail> patternDetails = new ArrayList<PatternDetail>();

				while ( rs2.next() ) {

					PatternDetail pd = new PatternDetail();
					pd.setPatternCd( rs2.getString("pattern_cd") );
					pd.setSeqId( rs2.getInt("seq_id") );
					pd.setMarketCd( rs2.getString("market_cd") );
					pd.setPreDayCnt( rs2.getInt("pre_day_cnt") );
					pd.setPatternAtype( rs2.getString("pattern_atype") );

					patternDetails.add( pd );

				}

				pattern.setPatternDetails( patternDetails );

				rs2.close();

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db2.close();
			db.close();

		}

		return pattern;

	}

	/**
	 * コード(パターンコードと番号)を指定してパターン詳細を取得
	 * @return
	 * @throws Exception
	 */
	public PatternDetail getPatternDetailByKey( String patternCd ,int seqId ) throws Exception {

		DbComponent db2 = new DbComponent(false);
		PatternDetail pd = null;

		try {

			String sql2 = "SELECT pattern_cd, seq_id, market_cd, pre_day_cnt, pattern_atype, regist_date, "
					+ " update_date "
					+ " FROM mt_pattern_detail "
					+ " WHERE pattern_cd = '" + patternCd + "' "
					+ " AND seq_id = " + seqId ;

			ResultSet rs2 = db2.select( sql2 );

			while ( rs2.next() ) {

				pd = new PatternDetail();
				pd.setPatternCd( rs2.getString("pattern_cd") );
				pd.setSeqId( rs2.getInt("seq_id") );
				pd.setMarketCd( rs2.getString("market_cd") );
				pd.setPreDayCnt( rs2.getInt("pre_day_cnt") );
				pd.setPatternAtype( rs2.getString("pattern_atype") );

				break;

			}

			rs2.close();

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db2.close();

		}

		return pd;

	}

	/**
	 * パターンの計算　符号化と集計を連続して行う
	 *
	 * @param patternCd
	 * @throws Exception
	 */
	public void calcPattern( String patternCd ) throws Exception {

		DbComponent db = new DbComponent(false);
		DbComponent db2 = new DbComponent(false);

		try {

			//== 既存データ削除
			String sql1 = "DELETE FROM work_summary1 WHERE pattern_cd = '" + patternCd + "' ";

			db.delete(sql1);
			db.commit();

			String sql2 = "DELETE FROM work_summary2 WHERE pattern_cd = '" + patternCd + "' ";

			db.delete(sql2);
			db.commit();

			//== ここから符号化
			Pattern ptn = getPatternByCd( patternCd );

			String sql3 =  "SELECT market_cd, regist_date, start_price, high_price, low_price, end_price ,volume"
					+ " FROM public.market_price "
					+ " WHERE market_cd = '" + ptn.getTargetMarketCd() + "' ";

			ResultSet rs3 = db.select(sql3);

			WorkDataRegister wdr = new WorkDataRegister();

			while (rs3.next()) {

				Price curPrice = new Price();
				curPrice.setMarketCd(rs3.getString("market_cd"));
				curPrice.setRegistDate(rs3.getString("regist_date"));
				curPrice.setStartPrice(rs3.getDouble("start_price"));
				curPrice.setHighPrice(rs3.getDouble("high_price"));
				curPrice.setLowPrice(rs3.getDouble("low_price"));
				curPrice.setEndPrice(rs3.getDouble("end_price"));
				curPrice.setVolume(rs3.getDouble("volume"));

				String targetValue = getSignalData(curPrice.getMarketCd() ,curPrice.getRegistDate() ,0);
				String summaryValue = "";
				String titleValue = curPrice.getMarketCd() + "||";

				List<PatternDetail> pds = ptn.getPatternDetails();

				for (Iterator<PatternDetail> it = pds.iterator(); it.hasNext();) {
					PatternDetail pd = (PatternDetail) it.next();

					summaryValue += getSignalData(pd.getMarketCd() ,curPrice.getRegistDate() ,pd.getPreDayCnt());

					titleValue += pd.getMarketCd() + "_"  + pd.getPreDayCnt() + ",";

				}

				wdr.registWorkSummary1(patternCd, targetValue, summaryValue, titleValue);

			}
			rs3.close();

			//== ここから集計
			String sql4 =  "select count(*) as cnt ,summary_value ,disp_value "
					+ " from work_summary1 "
					+ " WHERE pattern_cd = '" + patternCd + "' "
					+ " group by summary_value ,disp_value ";

			ResultSet rs4 = db.select(sql4);

			while (rs4.next()) {

				int totalCnt  = rs4.getInt("cnt");
				String sumValue = rs4.getString("summary_value");
				String dispValue = rs4.getString("disp_value");

				String sql5 = "select count(*) as detailCnt ,target_value ,summary_value "
						+ " from work_summary1 "
						+ " where summary_value = '" + sumValue + "' "
						+ " AND pattern_cd = '" + patternCd + "' "
						+ " group by target_value ,summary_value ";

				ResultSet rs5 = db2.select(sql5);

				while ( rs5.next() ) {

					int detailCnt = rs5.getInt("detailCnt");
					String targetValue = rs5.getString("target_value");

					double targetRatio = ((double)detailCnt / (double)totalCnt) * 100 ;

					wdr.registWorkSummary2(
							patternCd ,
							detailCnt,
							totalCnt ,
							targetRatio,
							targetValue,
							sumValue ,
							dispValue);

				}
				rs5.close();

			}
			rs4.close();

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db2.close();
			db.close();

		}

	}


	/**
	 * 過去分の比較と
	 * @param marketCd
	 * @param registDate
	 * @return
	 */
	public String getSignalData(String marketCd ,String registDate ,int preNum) {

		String result = "";
		int tmpVal = 0;
		if (preNum > 0) {
			tmpVal = -(preNum);
		}


		try {

			PriceUtil pu = new PriceUtil();

			Price price = pu.getPriceOfDays(marketCd, registDate, tmpVal);

			if (price.getEndPrice() > price.getStartPrice()) {
				result = "1" ;
			} else {
				result = "0" ;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * パターン（親）を一件登録
	 */
	public void registPattern( Pattern ptn ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "INSERT INTO mt_pattern("
					+ "pattern_cd, pattern_name, target_market_cd, target_atype, memo,"
					+" regist_date, update_date) "
					+" VALUES ("
					+ "'" + ptn.getPatternCd() + "', "
					+ "'" + ptn.getPatternName() + "', "
					+ "'" + ptn.getTargetMarketCd() + "', "
					+ "'" + ptn.getTargetAtype() + "', "
					+ "'" + ptn.getMemo() + "', "
					+ "now(), "
					+ "now()) ";

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
	 * パターン（親）を一件更新
	 */
	public void updatePattern( Pattern ptn ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "UPDATE mt_pattern SET "
					+ "pattern_name='" + ptn.getPatternName() + "', "
					+ "target_market_cd='" + ptn.getTargetMarketCd() + "', "
					+ "target_atype='" + ptn.getTargetAtype() + "', "
					+ " memo='" + ptn.getMemo() + "', "
					+ "update_date= now() "
					+ " WHERE pattern_cd = '" + ptn.getPatternCd() + "'  ";

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
	 * パターン（親）を一件削除
	 */
	public void deletePattern( String ptnCd ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "DELETE FROM mt_pattern "
					+ " WHERE pattern_cd = '" + ptnCd + "'  ";

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
	 * パターン詳細（子）を一件登録
	 */
	public void registPatternDetail( PatternDetail pd ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "INSERT INTO mt_pattern_detail("
					+ "pattern_cd, seq_id, market_cd, pre_day_cnt, pattern_atype, regist_date,"
					+ "update_date) "
					+ " VALUES ("
					+ "'" + pd.getPatternCd() + "', "
					+ pd.getSeqId() + ", "
					+ "'" + pd.getMarketCd() + "', "
					+ pd.getPreDayCnt() + ", "
					+ "'" + pd.getPatternAtype() + "', "
					+ "now(), "
					+ "now() )";


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
	 * パターン詳細（子）を一件更新
	 */
	public void updatePatternDetail( PatternDetail pd ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "UPDATE mt_pattern_detail SET "
					+ "market_cd='" + pd.getMarketCd() + "', "
					+ "pre_day_cnt=" + pd.getPreDayCnt() + ", "
					+ "pattern_atype='" + pd.getPatternAtype() + "', "
					+ "update_date= now() "
					+ "WHERE pattern_cd= '" + pd.getPatternCd() + "' "
					+ "AND seq_id= " + pd.getSeqId();

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
	 * パターン詳細（子）を一件削除
	 */
	public void deletePatternDetail( String ptnCd ,int seqId ) throws Exception {

		DbComponent db = new DbComponent(false);

		try {

			String sql = "DELETE FROM mt_pattern_detail "
					+ "WHERE pattern_cd= '" + ptnCd + "' "
					+ "AND seq_id= " + seqId;

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
