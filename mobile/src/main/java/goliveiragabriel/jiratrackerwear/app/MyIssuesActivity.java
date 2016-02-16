package goliveiragabriel.jiratrackerwear.app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

public class MyIssuesActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private IssuesRecycleAdapter mAdapter;
    RecyclerView listView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RestClient restClient = new RestClient();

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
    }

    @Override
    protected void onDestroy() {
        if ( dbHelper != null ) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
        super.onDestroy();
    }
}
