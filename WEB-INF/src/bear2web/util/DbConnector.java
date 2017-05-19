package bear2web.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


class DbConnector{

	private static final String DRIVER1 = "org.postgresql.Driver";

	private static DbConnector instance;

	//private static String FILE_PATH = "C:/tools/pleiades4.6.3/workspace/bear2Web/conf/bear2.properties";
	//本番環境に合わせてシンボリックリンク
	private static String FILE_PATH = "C:/tools/pleiades/workspace/bear2Web/conf/bear2.properties";

	protected static synchronized DbConnector getInstance(){

		if (instance == null) {
			instance = new DbConnector();
		}
		return instance;

	}

	private DbConnector(){
		try{
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");

			Class.forName(DRIVER1);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected synchronized Connection getConnection()
		throws Exception
	{

		Properties prop = new Properties();
		prop.load(new FileInputStream(FILE_PATH));

		String dbHostName = prop.getProperty("DB_HOST_NAME");
		String dbName = prop.getProperty("DB_NAME");
		String dbUser = prop.getProperty("DB_USER");
		String dbPw = prop.getProperty("DB_PW");


		String urlStr ="jdbc:postgresql://" + dbHostName
				+ "/" + dbName
				+ "?user=" + dbUser
				+ "&password=" + dbPw;
		return DriverManager.getConnection(urlStr);
	}

	protected synchronized void returnConnection(Connection conn)
		throws Exception
	{
		conn.close();
	}

}
