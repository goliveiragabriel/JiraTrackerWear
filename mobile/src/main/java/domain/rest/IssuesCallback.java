package domain.rest;

import android.content.Context;
import android.content.Intent;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;
import java.util.Calendar;

import domain.helper.DbHelper;
import domain.model.Issues;
import domain.model.QueryResult;
import domain.model.User;
import goliveiragabriel.jiratrackerwear.app.MyIssuesActivity;
import goliveiragabriel.jiratrackerwear.app.helper.Cache;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Gabriel on 15/02/2016.
 */
public class IssuesCallback implements Callback<QueryResult> {

    public User mUser;
    public Context mContext;
    public DbHelper dbHelper;

    public IssuesCallback(Context context, DbHelper dbHelper, User user){
        this.mUser = user;
        this.mContext = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public void success(QueryResult queryResult, Response response) {
        mUser.lastAccess = Calendar.getInstance().getTime();
        if ( Cache.currentUser != null) {
            Intent intent = new Intent(mContext, MyIssuesActivity.class );
            mContext.startActivity(intent);
        }
        queryResult.user = mUser;
        dbHelper.getUserDao().createOrUpdate(mUser);
        queryResult.dbIssues = new ArrayList<Issues>(queryResult.issues);
        mUser.queryResult = queryResult;
        //dbHelper.getQueryResultDao().create(queryResult);
        Cache.currentUser = mUser;
        if ( dbHelper != null ) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
        Intent intent = new Intent(mContext, MyIssuesActivity.class );
        mContext.startActivity(intent);
    }

    @Override
    public void failure(RetrofitError error) {

    }

}
