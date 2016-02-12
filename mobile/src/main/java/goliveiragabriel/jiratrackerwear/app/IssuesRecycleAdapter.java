package goliveiragabriel.jiratrackerwear.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import domain.model.Issues;
import goliveiragabriel.jiratrackerwear.R;

/**
 * Created by Gabriel on 11/02/2016.
 */
public class IssuesRecycleAdapter extends RecyclerView.Adapter<IssuesRecycleAdapter.IssuesHolder> {

    Context mContext;
    List<Issues> mIssues;
    final LayoutInflater inflater;

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
    public void onBindViewHolder(IssuesHolder holder, int position) {
        Issues issue = mIssues.get(position);
        holder.summary.setText(issue.fields.summary);
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    public class IssuesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView summary;

        public IssuesHolder(View vi){
            super(vi);
            this.summary = (TextView) vi.findViewById(R.id.summary);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
