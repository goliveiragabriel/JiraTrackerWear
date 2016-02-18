package goliveiragabriel.jiratrackerwear;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Gabriel on 16/02/2016.
 */
public class ListenerService extends WearableListenerService {
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals("/issue")) {
            final String message = new String(messageEvent.getData());

            // Broadcast message to wearable activity for display
            Intent messageIntent = new Intent();
            messageIntent.setAction(Intent.ACTION_SEND);
            messageIntent.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(messageIntent);
        }
        else {
            super.onMessageReceived(messageEvent);
        }
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event: dataEvents) {
            String eventUri = event.getDataItem().getUri().toString();
            if (eventUri.contains ("/myapp/myissues")) {
                DataMapItem dataItem = DataMapItem.fromDataItem (event.getDataItem());
                String[] data = dataItem.getDataMap().getStringArray("contents");
                // Assign an adapter to the list
                MyIssuesActivity.data = data;
                if(MyIssuesActivity.listView != null) {
                    ListAdapter adapter = (ListAdapter) MyIssuesActivity.listView.getAdapter();
                    adapter.setData(data);

                }
                //myListener.onDataReceived(data);
            }
        }
    }
}
