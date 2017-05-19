package bear2web.util;

import java.util.Calendar;

public class TestDateUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("--- start ---");
		try {
			String str = "2006/04/31";

			DateUtil util = new DateUtil();

			System.out.println( util.yesterday(str) );

			System.out.println(Calendar.DECEMBER);

			System.out.println(util.getSysdateString());


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- end ---");

	}

}
