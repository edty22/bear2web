package bear2web.mgr;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Pattern;

/**
 *
 * パターンを一括で計算し直すバッチ
 *
 *
 * @author Toshi
 *
 */

public class BatchPatternCalc {

	private static Logger _log = Logger.getLogger(BatchPatternCalc.class);

	public static void main(String[] args) {

		System.out.println("--- start ---");
		try {

			PatternMgr pm = new PatternMgr();

			List<Pattern> ptns = pm.getAllPattern();

			for (Iterator<Pattern> it = ptns.iterator(); it.hasNext();) {

				Pattern ptn = (Pattern)it.next();

				System.out.println( "バッチ実行:" + ptn.getPatternCd() + " : " + ptn.getPatternName() );

				pm.calcPattern( ptn.getPatternCd() );

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- end ---");

	}

}
