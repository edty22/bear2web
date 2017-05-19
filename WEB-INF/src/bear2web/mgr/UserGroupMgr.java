package bear2web.mgr;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.UserGroup;
import bear2web.util.DbComponent;

/**
 *
 * @author Toshiyuki Endo
 *
 */

public class UserGroupMgr {

	private static Logger _log = Logger.getLogger(UserGroupMgr.class);	// ���O

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<UserGroup> getAllUserGroup() throws Exception {

		DbComponent db = new DbComponent(false);
		List<UserGroup> groups = null;

		try {

			String sql = "SELECT section_id, section_name " +
			" FROM mt_section " +
			" ORDER BY section_id ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (groups == null) {
					groups = new ArrayList<UserGroup>();
				}

				UserGroup group = new UserGroup();
				group.setGroupId( rs.getString("section_id") );
				group.setGroupName( rs.getString("section_name") );

				groups.add( group );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return groups;

	}


}
