package goliveiragabriel.jiratrackerwear;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConfirmTrackActivity extends Activity {

    private TextView mTextView;
    private ImageButton mTrackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_track);
        final Context context = this;
        mTrackButton = (ImageButton) findViewById(R.id.startTrack);
        mTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrackingActivity.class);
                startActivity(intent);
            }
        });

    }
}
