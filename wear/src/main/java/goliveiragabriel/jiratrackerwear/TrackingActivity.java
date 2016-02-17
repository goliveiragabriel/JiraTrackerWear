package goliveiragabriel.jiratrackerwear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

public class TrackingActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        setAmbientEnabled();
    }
}
