package bear2web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


	public List getCsvFileList( String dirName ) throws Exception {

		List result = new ArrayList();

		try {

			File file = new File( dirName ) ;

			File[] files = file.listFiles();

			for (int i = 0 ; i < files.length ; i++) {

				File tmp = files[i];
				String rName = tmp.getPath().replaceAll("\\", "/");

				if ( -1 != rName.indexOf(".csv") ) {
					result.add( rName );
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;


	}




}
