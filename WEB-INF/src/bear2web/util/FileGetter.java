
package bear2web.util;

/**
 * @author endo
 *	FILE���擾����
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

// ���O�p
import org.apache.log4j.Logger;

public class FileGetter {

	private static Logger _log = Logger.getLogger(FileGetter.class);

	private String url;
	private String saveDir;
	private String saveFile;
	private String year;
	private String month;
	private String date;
	private String fileName;

	public FileGetter (String year ,String month ,String date) {

		this.year = year;
		this.month = month;
		this.date = date;
		fileName = this.year + this.month + this.date + ".LZH";

		ResourceBundle rb = ResourceBundle.getBundle("b2db");
		url = rb.getString("DL_URL") + year + "/" + fileName;
		saveDir = rb.getString("SAVE_DIR");
		saveFile = saveDir + fileName;

	}


	/**
	 * @param date The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @param saveDir The saveDir to set.
	 */
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public void douwnLoad() {

		_log.info( "FILE GETTER START : " + saveFile );
		try{

			if ( (new File(saveFile)).exists() ){

				throw new Exception("File Exists " + saveFile );
			}

			URL getUrl = new URL(url);

			if( getUrl.getPort() == -1 ) {
				System.out.println("PORT=" +  getUrl.getDefaultPort() );
			} else {
				System.err.println( getUrl.getPort() );
			}

			// HTTP
			HttpURLConnection http = (HttpURLConnection)getUrl.openConnection();

			// GET
			http.setRequestMethod("GET");

			//
			http.connect();

			//
			BufferedInputStream bis
				= new BufferedInputStream( http.getInputStream() );
			int data;
			BufferedOutputStream bos
				= new BufferedOutputStream( new FileOutputStream(saveFile) );

			while ( (data = bis.read()) != -1 ) {
				bos.write(data);
			}

			bos.close();

		} catch( IOException e ) {
			e.printStackTrace();
			_log.error( e.getMessage() );
		} catch (Exception e) {
			e.printStackTrace();
			_log.error( e.getMessage() );
		}
		_log.info( "FILE GETTER FINISH" );

	}

}
