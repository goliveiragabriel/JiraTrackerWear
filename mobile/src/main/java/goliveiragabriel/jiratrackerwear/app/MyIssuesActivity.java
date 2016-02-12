package goliveiragabriel.jiratrackerwear.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.helper.DbHelper;
import domain.model.Issues;
import domain.model.QueryResult;
import goliveiragabriel.jiratrackerwear.R;
import goliveiragabriel.jiratrackerwear.app.helper.Cache;

public class MyIssuesActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private IssuesRecycleAdapter mAdapter;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_issues);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (RecyclerView) findViewById(R.id.listView_myissues);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if ( dbHelper == null ) {
            dbHelper = OpenHelperManager.getHelper(this, DbHelper.class);
        }
        //QueryResult queryResult = dbHelper.getQueryResultDao().queryBuilder().where().eq("user_id", Cache.currentUser.id).query().get(0);
        if(Cache.currentUser != null) {
            List<Issues> lst = new ArrayList(Cache.currentUser.queryResult.issues);
            mAdapter = new IssuesRecycleAdapter(this, lst);
            listView.setAdapter(mAdapter);
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
