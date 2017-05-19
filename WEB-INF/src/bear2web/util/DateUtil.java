package bear2web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {


	public int getDayOfWeek( String today ) throws Exception {

		return getToday( today ).get( Calendar.DAY_OF_WEEK );

	}


	public String yesterday( String today ) throws Exception {

		return ctlDate( today , -1 );

	}


	public String tomorrow( String today ) throws Exception {

		return ctlDate( today , 1 );

	}


	private String ctlDate( String today ,int move) throws Exception {

		String result = null;

		try {

			Calendar cal = getToday( today );
			cal.add( Calendar.DATE , move );

			String year = Integer.toString( cal.get(Calendar.YEAR) );
			int month_num = cal.get(Calendar.MONTH) + 1;
			String month = null;
			if ( month_num < 10 ) {
				month = "0" + Integer.toString( month_num );
			} else {
				month = Integer.toString( month_num );
			}
			String date = null;
			if ( cal.get(Calendar.DATE) < 10 ) {
				date = "0" + Integer.toString( cal.get(Calendar.DATE) );
			} else {
				date = Integer.toString( cal.get(Calendar.DATE));
			}

			result = year + "/" + month + "/" + date;

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		}

		return result;

	}


	private Calendar getToday(String today) throws Exception {

		try {

			String[] dates = today.split("/");

			//System.out.println( today );

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt( dates[0]) );
			cal.set(Calendar.MONTH ,Integer.parseInt(dates[1]) - 1 );	//
			cal.set(Calendar.DATE ,Integer.parseInt( dates[2] ) );

			return cal;

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		}

	}


	public String getSysdateString() throws Exception {

		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			return ( format.format(cal.getTime()) );

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}


	}


}
