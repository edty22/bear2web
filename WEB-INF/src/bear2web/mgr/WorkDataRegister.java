package bear2web.mgr;

import bear2web.util.DbComponent;



public class WorkDataRegister {

	private DbComponent dc = null;

	public WorkDataRegister() {

		try {
			dc = new DbComponent();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void registWorkSummary1(
			String patternCd ,String targetValue ,String summaryValue ,String dispValue)
	 throws Exception {

		 String sql = "INSERT INTO work_summary1("
            + " pattern_cd ,target_value, summary_value, disp_value) "
            + " VALUES ('" + patternCd + "' ,'" + targetValue + "','" + summaryValue + "','" + dispValue + "')";

	        System.out.println( sql );

	        try {
	        	dc.insert(sql);
	        	dc.commit();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	dc.rollback();
	        }

	}

	public void registWorkSummary2(
			String patternCd ,int target_cnt, int total_cnt , double target_ratio ,
			String targetValue ,String summaryValue ,String dispValue)
			 throws Exception {

				 String sql = "INSERT INTO work_summary2("
						 + " pattern_cd ,target_cnt ,total_cnt, target_ratio, target_value, summary_value, disp_value) "
						 + " VALUES ('" + patternCd + "' ," + target_cnt + ", " +  total_cnt + ", " +  target_ratio + ","
						 + "'" + targetValue + "','" + summaryValue + "','" + dispValue + "'"
						 + ")";

			        System.out.println( sql );

			        try {
			        	dc.insert(sql);
			        	dc.commit();
			        } catch (Exception e) {
			        	e.printStackTrace();
			        	dc.rollback();
			        }

			}



}
