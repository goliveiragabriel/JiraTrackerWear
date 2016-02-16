package goliveiragabriel.jiratrackerwear.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import domain.model.Issues;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import goliveiragabriel.jiratrackerwear.R;


/**
 * Created by Gabriel on 11/02/2016.
 */
public class IssuesRecycleAdapter extends RecyclerView.Adapter<IssuesRecycleAdapter.IssuesHolder> {

    Context mContext;
    List<Issues> mIssues;
    final LayoutInflater inflater;
    static int NOTIFICATION_ID = 001;
    final static String GROUP_KEY_EMAILS = "group_key_emails";


    public IssuesRecycleAdapter(Context context, List<Issues> listIssues){
        mIssues = listIssues;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public IssuesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = inflater.inflate(R.layout.issue_item, null);
        IssuesHolder holder = new IssuesHolder(vi);
        return holder;

    }

    @Override
    public void onBindViewHolder(final IssuesHolder holder, int position) {
        final Issues issue = mIssues.get(position);
        holder.summary.setText(issue.fields.summary);
        holder.key.setText(issue.key);
        holder.sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent viewIntent = new Intent(mContext, MyIssuesActivity.class);
                PendingIntent viewPendingIntent = PendingIntent.getActivity(mContext, 0, viewIntent, 0);
                // Create builder for the main notification
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(mContext)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(issue.key)
                                .setContentText(issue.fields.summary)
                                .setContentIntent(viewPendingIntent);
                // Build the notification and issues it with notification manager.
                // Get an instance of the NotificationManager service
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(mContext);

                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

            }
        });
    }

    public void notifyChanges(List<Issues> lst) {
        if ( lst.size() == 0 )
            return;

        Intent viewIntent = new Intent(mContext, MyIssuesActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(mContext, 0, viewIntent, 0);
        String title = lst.size() + " nova(s) issues atualizadas";
        // Create builder for the main notification
        Bitmap largeIcon = BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.ic_launcher);

// Create an InboxStyle notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();

        for (Issues issue : lst ) {
            style.addLine(issue.key + " " + issue.fields.summary);
        }
        style.setBigContentTitle(title)
                .setSummaryText("My issues tracker");
        builder.setStyle(style)
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
        ;
        Notification summaryNotification = builder.build();

                // Build the notification and issues it with notification manager.
        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(mContext);

        notificationManager.notify(NOTIFICATION_ID, summaryNotification);

    }
    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    public class IssuesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView summary;
        public TextView key;
        public ImageView sendImage;

        public IssuesHolder(View vi){
            super(vi);
            this.summary = (TextView) vi.findViewById(R.id.summary);
            this.key = (TextView) vi.findViewById(R.id.txtKey);
            this.sendImage = (ImageView) vi.findViewById(R.id.sendImage);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
