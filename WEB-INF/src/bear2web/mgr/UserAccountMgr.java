package bear2web.mgr;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.UserAccount;
import bear2web.util.DbComponent;


public class UserAccountMgr {

	private static Logger _log = Logger.getLogger(UserAccountMgr.class);	// ���O


	/**
	 *
	 * @param account
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public UserAccount login(String account, String passwd)
			throws Exception {

		DbComponent db = new DbComponent(false);
		UserAccount ua = null;

		try {

			String sql = "SELECT ua.user_account_id as user_account_id, " +
					"ua.user_account as user_account, " +
					"ua.user_passwd as user_passwd , " +
					"ua.user_name as user_name, " +
					"ua.section_id as section_id, " +
					"ua.regist_date as regist_date, " +
					"ua.update_date as update_date, " +
					"ms.section_name as section_name " +
					" FROM user_account ua ,mt_section ms " +
					" WHERE ua.user_account = '" + account + "' " +
					" AND ua.user_passwd = '" + passwd + "'" +
					" AND ua.section_id = ms.section_id ";

			ResultSet rs = db.select( sql );

			if ( rs.next() ) {

				ua = new UserAccount();
				ua.setUserAccountId( rs.getInt("user_account_id") );
				ua.setUserAccount( rs.getString("user_account") );
				ua.setUserPasswd( rs.getString("user_passwd") );
				ua.setUserName( rs.getString("user_name") );
				ua.setSectionId( rs.getInt("section_id") );
				ua.setSectionName( rs.getString("section_name") );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return ua;

	}

	/**
	 *
	 * @param userAccountId
	 * @return
	 * @throws Exception
	 */
	public UserAccount getUserAccountById(int userAccountId)
	throws Exception {

		DbComponent db = new DbComponent(false);
		UserAccount ua = null;

		try {

			String sql = "SELECT ua.user_account_id as user_account_id, " +
			"ua.user_account as user_account, " +
			"ua.user_passwd as user_passwd , " +
			"ua.user_name as user_name, " +
			"ua.section_id as section_id, " +
			"ua.regist_date as regist_date, " +
			"ua.update_date as update_date, " +
			"ms.section_name as section_name " +
			" FROM user_account ua ,mt_section ms " +
			" WHERE ua.user_account_id = '" + userAccountId + "' " +
			" AND ua.section_id = ms.section_id ";

			ResultSet rs = db.select( sql );

			if ( rs.next() ) {

				ua = new UserAccount();
				ua.setUserAccountId( rs.getInt("user_account_id") );
				ua.setUserAccount( rs.getString("user_account") );
				ua.setUserPasswd( rs.getString("user_passwd") );
				ua.setUserName( rs.getString("user_name") );
				ua.setSectionId( rs.getInt("section_id") );
				ua.setSectionName( rs.getString("section_name") );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return ua;

	}



	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<UserAccount> getAllUsers() throws Exception {

		DbComponent db = new DbComponent(false);
		List<UserAccount> users = null;

		try {

			String sql = "SELECT ua.user_account_id as user_account_id, " +
			"ua.user_account as user_account, " +
			"ua.user_passwd as user_passwd , " +
			"ua.user_name as user_name, " +
			"ua.section_id as section_id, " +
			"ua.regist_date as regist_date, " +
			"ua.update_date as update_date, " +
			"ms.section_name as section_name " +
			" FROM user_account ua ,mt_section ms " +
			" WHERE ua.section_id = ms.section_id " +
			" ORDER BY ua.sort_num ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (users == null) {
					users = new ArrayList<UserAccount>();
				}

				UserAccount ua = new UserAccount();
				ua.setUserAccountId( rs.getInt("user_account_id") );
				ua.setUserAccount( rs.getString("user_account") );
				ua.setUserPasswd( rs.getString("user_passwd") );
				ua.setUserName( rs.getString("user_name") );
				ua.setSectionId( rs.getInt("section_id") );
				ua.setSectionName( rs.getString("section_name") );

				users.add( ua );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return users;

	}

}
