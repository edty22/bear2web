
package bear2web.util;


public class TestFileGetter {

	private static String year = "2004";
	private static String month = "10";
	private static String date = "07";

	public static void main(String[] args) {

		System.out.println("--- START ---");
		try {
			FileGetter fg = new FileGetter(year ,month ,date);
			fg.douwnLoad();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("--- END ---");

	}
}
