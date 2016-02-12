package goliveiragabriel.jiratrackerwear.app.utils;

/**
 * Created by Gabriel on 05/02/2016.
 */
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * DatabaseConfigUtl writes a configuration file to avoid using annotation processing in runtime which is very slow
 * under Android. This gains a noticeable performance improvement.
 *
 * The configuration file is written to /res/raw/ by default. More info at: http://ormlite.com/docs/table-config
 */

public class DbConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile(new File("mobile/src/main/res/raw/ormlite_config.txt"));
    }
}