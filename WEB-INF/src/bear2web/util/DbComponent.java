package bear2web.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.log4j.Category;



public class DbComponent {

	private DbConnector connector;
	private Connection conn = null;
	private Exception ex = null;
	private Statement stmt = null;

	public DbComponent(boolean autoCommit) throws Exception{

		ex = null;
		try{
			connector = DbConnector.getInstance();
			conn = connector.getConnection();
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			ex = e;
			throw(e);
		}

	}


	public DbComponent() throws Exception{

		ex = null;
		try{
			connector = DbConnector.getInstance();
			conn = connector.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			ex = e;
			throw(e);
		}

	}

	public static Category _cat = Category.getInstance(DbComponent.class);


	public ResultSet select(String sql) throws Exception{

		ResultSet rslt = null;
		if ( stmt != null ) {
			stmt.close();
			stmt = null;
		}
		ex = null;

		printLog("select:" + sql);  //Debug
		try {

			conn.clearWarnings();
			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rslt = stmt.getResultSet();
			} else {
				rslt = null;
			}

		} catch (Exception e) {

			ex = e;
			rslt = null;
			printLog(":" + ex.getMessage());
			throw(ex);

		}
		return rslt;
	}

	public int insert(String sql) throws Exception{

		int insertCount = 0;
		if ( stmt != null ) {
			stmt.close();
			stmt = null;
		}
		ex = null;

		printLog("insert:" + sql);  //Debug
		try {

			conn.clearWarnings();
			stmt = conn.createStatement();
			insertCount = stmt.executeUpdate(sql);
			printLog("insertCount:" + insertCount);  //Debug

		} catch (Exception e) {

			ex = e;
			printLog(":" + ex.getMessage());
			throw(ex);

		}
		return insertCount;
	}


	public int update(String sql) throws Exception{

		int updateCount = 0;
		if ( stmt != null ) {
			stmt.close();
			stmt = null;
		}
		ex = null;

		printLog("update:" + sql);  //Debug
		try {

			conn.clearWarnings();
			stmt = conn.createStatement();
			updateCount = stmt.executeUpdate(sql);
			printLog("updateCount:" + updateCount);  //Debug

		} catch (Exception e) {

			ex = e;
			printLog(":" + ex.getMessage());
			throw(ex);

		}
		return updateCount;
	}


	public int delete(String sql) throws Exception{

		int deleteCount = 0;
		if ( stmt != null ) {
			stmt.close();
			stmt = null;
		}
		ex = null;

		try {

			printLog("delete:" + sql);  //Debug
			conn.clearWarnings();
			stmt = conn.createStatement();
			printLog("delete: statement created");  //Debug
			deleteCount = stmt.executeUpdate(sql);
			printLog("delete: executed");  //Debug
			printLog("deleteCount:"+deleteCount);  //Debug

		} catch (Exception e) {

			ex = e;
			printLog(":" + ex.getMessage());
			throw(ex);

		}
		return deleteCount;
	}


	public boolean execute(String sql) throws Exception{

		boolean result = false;
		if ( stmt != null ) {
			stmt.close();
			stmt = null;
		}
		ex = null;

		printLog("execute:" + sql);  //Debug
		try {

			conn.clearWarnings();
			stmt = conn.createStatement();
			result = stmt.execute(sql);
			printLog("result:" + result);  //Debug

		} catch (Exception e) {

			ex = e;
			printLog(":" + ex.getMessage());
			throw(ex);

		}
		return result;
	}



	public void commit() throws Exception{
		ex = null;
		try{

			conn.commit();

		} catch (Exception e) {

			ex = e;
			printLog(ex.getMessage());
			throw(ex);

		}
	}


	public void rollback() throws Exception{
		ex = null;
		try{

			conn.rollback();

		} catch (Exception e) {

			ex = e;
			printLog(ex.getMessage());
			throw(ex);

		}
	}

	public void close() throws Exception{
		ex = null;
		if ( null != stmt ) {

			stmt.close();
			stmt = null;

		}

		if (null != conn) {

			try{

				conn.clearWarnings();
				connector.returnConnection(conn);
				conn = null;

			} catch (Exception e) {

				ex = e;
				printLog("close:" + ex.getMessage());
				throw(ex);

			}
		}
	}


	public String toString(){
		ex = null;
		String str = new String("DBConnection Object Status:\n");
		if(conn == null){
			str += "Not Connected\n";
		} else {
			str += "Connected\n";
		}
		return str;
	}


	public int getErrorCode() {
		if(ex != null){
			return ((SQLException)ex).getErrorCode();
		} else {
			return 0;
		}
	}


	public String getErrorMessage() {
		if(ex != null){
			return ((SQLException)ex).toString();
		} else {
			return null;
		}
	}


	protected void finalize(){
		if(conn != null){

			try{

				conn.clearWarnings();
				connector.returnConnection(conn);
				conn = null;

			} catch (Exception e) {

				ex = e;
				printLog(":" + ex.getMessage());

			}

		}
	}


	private void printLog(String message){

		java.util.Date now = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		_cat.debug(format.format(now) + " " + message);

	}

}
