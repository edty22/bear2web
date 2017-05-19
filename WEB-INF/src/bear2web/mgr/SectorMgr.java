package bear2web.mgr;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bear2web.model.Sector;
import bear2web.util.DbComponent;

public class SectorMgr {

	private static Logger _log = Logger.getLogger(SectorMgr.class);	//

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Sector> getAllSectors() throws Exception {

		DbComponent db = new DbComponent(false);
		List<Sector> sectors = null;

		try {

			String sql = "SELECT sector_code, sector_name, regist_date, update_date " +
			" FROM mt_sector " +
			" ORDER BY sector_code ";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				if (sectors == null) {
					sectors = new ArrayList<Sector>();
				}

				Sector sector = new Sector();
				sector.setSectorCode( rs.getString("sector_code") );
				sector.setSectorName( rs.getString("sector_name") );

				sectors.add( sector );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return sectors;
	}

	/**
	 *
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Sector getSectorByCode (String code) throws Exception {

		DbComponent db = new DbComponent(false);
		Sector sector = null;

		try {

			String sql = "SELECT sector_code, sector_name, regist_date, update_date " +
			" FROM mt_sector " +
			" WHERE sector_code = '" + code + "'";

			ResultSet rs = db.select( sql );

			while ( rs.next() ) {

				sector = new Sector();
				sector.setSectorCode( rs.getString("sector_code") );
				sector.setSectorName( rs.getString("sector_name") );

			}

		} catch (Exception e) {

			_log.error( "erro !!" , e );
			e.printStackTrace();
			throw e;

		} finally {

			db.close();

		}

		return sector;
	}

}
