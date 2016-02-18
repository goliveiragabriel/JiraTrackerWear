package goliveiragabriel.jiratrackerwear;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;


public class MyIssuesActivity extends WearableActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
                                                            DataApi.DataListener, WearableListView.ClickListener{

    private TextView mTextView;
    private ImageButton mTrackButton;
    private GoogleApiClient mGoogleApiClient;
    public static WearableListView listView;
    public static String[] data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myissues);
        //mTextView = (TextView) findViewById(R.id.text);

        // Register the local broadcast receiver, defined in step 3.
        IntentFilter messageFilter = new IntentFilter(Intent.ACTION_SEND);
        MessageReceiver messageReceiver = new MessageReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, messageFilter);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // Get the list component from the layout of the activity
        listView =
                (WearableListView) findViewById(R.id.wearable_list);
        listView.setAdapter(new ListAdapter(this, data));


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event: dataEvents) {
            String eventUri = event.getDataItem().getUri().toString();
            if (eventUri.contains ("/myapp/myissues")) {
                DataMapItem dataItem = DataMapItem.fromDataItem (event.getDataItem());
                data = dataItem.getDataMap().getStringArray("contents");
                // Assign an adapter to the list
                //myListener.onDataReceived(data);
            }
        }
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Integer tag = (Integer) viewHolder.itemView.getTag();
        Intent intent = new Intent(this, ConfirmTrackActivity.class);
        intent.putExtra("issue", tag);
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    private class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            // Display message in UI
            //mTextView.setText(message);
        }
    }
}
