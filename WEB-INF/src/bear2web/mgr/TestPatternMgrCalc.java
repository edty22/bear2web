package bear2web.mgr;

public class TestPatternMgrCalc {

	public static void main(String[] args) {

		System.out.println("--- start ---");
		try {

			PatternMgr pm = new PatternMgr();

			//String ptnCode = "PTN0002";

			for (int i=3 ; i < 9;i++) {

				String ptnCode = "PTN000" + i;

				pm.calcPattern(ptnCode);

				System.out.println(ptnCode);

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- end ---");

	}

}
