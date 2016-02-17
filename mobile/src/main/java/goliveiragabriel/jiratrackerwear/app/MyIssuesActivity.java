package goliveiragabriel.jiratrackerwear.app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.helper.DbHelper;
import domain.model.Issues;
import domain.model.QueryResult;
import domain.rest.ApiService;
import domain.rest.IssuesCallback;
import domain.rest.RestClient;
import goliveiragabriel.jiratrackerwear.R;
import goliveiragabriel.jiratrackerwear.app.helper.Cache;

public class MyIssuesActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private DbHelper dbHelper;
    private IssuesRecycleAdapter mAdapter;
    RecyclerView listView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RestClient restClient = new RestClient();
    public static GoogleApiClient mGoogleApiClient;
    public static String ISSUES_KEY = "/issue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_issues);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (RecyclerView) findViewById(R.id.listView_myissues);
        setSupportActionBar(toolbar);
        final Context mContext = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restClient.apiService = RestClient.createService(ApiService.class, Cache.currentUser.userName, Cache.currentUser.password);
                IssuesCallback issuesCallback = new IssuesCallback(mContext, dbHelper, Cache.currentUser);
                restClient.GetIssues(Cache.currentUser.userName, 50, "updated", false, issuesCallback);
                int indexOf = Cache.currentUser.queryResult.issues.indexOf(mAdapter.mIssues.get(mAdapter.mIssues.size()-1));
                List<Issues> oldIssues = mAdapter.mIssues;
                mAdapter.mIssues = Cache.currentUser.queryResult.issues;
                mAdapter.notifyDataSetChanged();
                // Verifica se foi adicionado nova issue
                if(indexOf > 0 && indexOf > oldIssues.size() - 1){
                    mAdapter.notifyChanges(mAdapter.mIssues.subList(indexOf, mAdapter.mIssues.size()-1));
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if ( dbHelper == null ) {
            dbHelper = OpenHelperManager.getHelper(this, DbHelper.class);
        }
        //QueryResult queryResult = dbHelper.getQueryResultDao().queryBuilder().where().eq("user_id", Cache.currentUser.id).query().get(0);
        if(Cache.currentUser != null) {
            listView.setHasFixedSize(true);
            List<Issues> lst = new ArrayList(Cache.currentUser.queryResult.issues);
            mLayoutManager = new WrappingLinearLayoutManager(this);
            listView.setLayoutManager(mLayoutManager);
            mAdapter = new IssuesRecycleAdapter(this, lst);
            listView.setAdapter(mAdapter);
            listView.setNestedScrollingEnabled(false);
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onDestroy() {
        if ( dbHelper != null ) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
        super.onDestroy();
    }

    @Override
    public void onConnected(Bundle bundle) {
        List<Issues> issues = Cache.currentUser.queryResult.issues;
        new DataTask (this, issues).execute();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private class DataTask extends AsyncTask<Node, Void, Void> {

        Context mContext;
        List<Issues> mIssues;

        public DataTask(Context context, List<Issues> issues) {
            this.mContext = context;
            this.mIssues = issues;
        }

        @Override
        protected Void doInBackground(Node... params) {

            PutDataMapRequest dataMap = PutDataMapRequest.create ("/myapp/myissues");
            String[] contents = new String[mIssues.size()];
            int i =0;
            for(Issues issue : mIssues) {
                contents[i] = issue.key;
                i++;
            }
            dataMap.getDataMap().putStringArray("contents", contents);
            PutDataRequest request = dataMap.asPutDataRequest();

            DataApi.DataItemResult dataItemResult = Wearable.DataApi
                    .putDataItem(mGoogleApiClient, request).await();

            return null;
        }
    }
}
