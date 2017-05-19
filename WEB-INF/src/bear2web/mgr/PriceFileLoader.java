package bear2web.mgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import bear2web.util.DbComponent;

/**
 * ファイルをロードします
 * @author Toshi
 *
 */

public class PriceFileLoader {

	private String filePath;

	private String marketCode;

	// ALL:一括洗い替え（デフォルト）
	// DIFF:差分
	private String bulkOption = "ALL";

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getBulkOption() {
		return bulkOption;
	}

	public void setBulkOption(String bulkOption) {
		this.bulkOption = bulkOption;
	}

	public void loadPriceFile() throws Exception {

		Scanner scanner;

		try {
			DbComponent dc = new DbComponent();
			scanner = new Scanner(new File( filePath ));

			System.out.println("AAA:" + getBulkOption());

			if (getBulkOption().equals("ALL")) {

				DbComponent dc2 = new DbComponent();

				String sql2 = "DELETE FROM market_price WHERE market_cd = '" + marketCode + "' ";

				dc2.delete(sql2);
				dc2.commit();
				dc2.close();

			}

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();

		        String[] strs = line.split("," , -1);
		        String strDate = strs[0];
		        if (strDate.length() == 3) {
		        	strDate = "20000" + strDate;
		        } else if (strDate.length() == 4) {
		        	strDate = "2000" + strDate;
		        } else if (strDate.length() == 5) {
		        	strDate = "200" + strDate;
		        } else if (strDate.length() == 6) {
		        	strDate = "20" + strDate;
		        }

		        String strOpen = strs[1];
		        String strHigh = strs[2];
		        String strLow = strs[3];
		        String strClose = strs[4];
		        String strVolume = "NULL";
		        try {
		        	strVolume = strs[5];
		        } catch (Exception e) {
		        	//# 出来高がなくても何もしない
		        }

		        String sql = "INSERT INTO public.market_price("
		        		+ "market_cd, regist_date, start_price, high_price, low_price, end_price ,volume)"
		        		+ "VALUES ('" + marketCode + "' ,'" + strDate + "', " + strOpen + ", " + strHigh
		        		+ ", " + strLow + ", " + strClose + "," + strVolume + ")";

		        System.out.println( sql );

		        try {
		          dc.insert(sql);
		          dc.commit();
		        } catch (Exception e) {
		        	dc.rollback();
		        	e.printStackTrace();
		        }

		        //System.out.println(line.toUpperCase());
		    }

		    //dc.commit();

		    try {
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
