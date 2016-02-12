package domain.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import domain.model.Field;
import domain.model.IssueType;
import domain.model.Issues;
import domain.model.QueryResult;
import domain.model.User;
import goliveiragabriel.jiratrackerwear.R;

/**
 * Created by Gabriel on 05/02/2016.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "jira.db";
    private static final int DATABASE_VERSION = 1;


    private Dao<User, Integer> userDao = null;
    private RuntimeExceptionDao<User,Integer> userIntegerRuntimeExceptionDao = null;

    private Dao<QueryResult, Integer> queryResultDao = null;
    private RuntimeExceptionDao<QueryResult,Integer> queryResultIntegerRuntimeExceptionDao = null;

    private Dao<Issues, Integer> issuesDao = null;
    private RuntimeExceptionDao<Issues,Integer> issuesIntegerRuntimeExceptionDao = null;

    private Dao<Field, Integer> fieldDao = null;
    private RuntimeExceptionDao<Field,Integer> fieldIntegerRuntimeExceptionDao = null;

    private Dao<IssueType, Integer> issueTypeDao = null;
    private RuntimeExceptionDao<IssueType,Integer> issueTypeIntegerRuntimeExceptionDao = null;

    public RuntimeExceptionDao<IssueType, Integer> getIssueTypeDao() {
        if (issuesIntegerRuntimeExceptionDao == null)
            issueTypeIntegerRuntimeExceptionDao = getRuntimeExceptionDao(IssueType.class);
        return issueTypeIntegerRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<Field, Integer> getFieldDao() {
        if(fieldIntegerRuntimeExceptionDao == null)
            fieldIntegerRuntimeExceptionDao = getRuntimeExceptionDao(Field.class);
        return fieldIntegerRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<Issues, Integer> getIssuesDao() {
        if(issuesIntegerRuntimeExceptionDao == null)
            issuesIntegerRuntimeExceptionDao = getRuntimeExceptionDao(Issues.class);
        return issuesIntegerRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<QueryResult, Integer> getQueryResultDao() {
        if(queryResultIntegerRuntimeExceptionDao == null)
            queryResultIntegerRuntimeExceptionDao = getRuntimeExceptionDao(QueryResult.class);
        return queryResultIntegerRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<User, Integer> getUserDao() {
        if(userIntegerRuntimeExceptionDao == null)
            userIntegerRuntimeExceptionDao = getRuntimeExceptionDao(User.class);
        return userIntegerRuntimeExceptionDao;
    }

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Field.class);
            TableUtils.createTable(connectionSource, IssueType.class);
            TableUtils.createTable(connectionSource, Issues.class);
            TableUtils.createTable(connectionSource, QueryResult.class);

        } catch (SQLException e ) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Field.class, true);
            TableUtils.dropTable(connectionSource, Issues.class, true);
            TableUtils.dropTable(connectionSource, IssueType.class, true);
            TableUtils.dropTable(connectionSource, QueryResult.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
