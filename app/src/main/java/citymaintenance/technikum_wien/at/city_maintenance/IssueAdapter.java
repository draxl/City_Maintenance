package citymaintenance.technikum_wien.at.city_maintenance;

/**
 * Created by UAS on 2017.12.16..
 */

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.MyViewHolder> {

    private List<Issues> issuesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category_name, description, status, id_issue;

        public MyViewHolder(View view) {
            super(view);
            category_name = (TextView) view.findViewById(R.id.category_name);
            description = (TextView) view.findViewById(R.id.description);
            status = (TextView) view.findViewById(R.id.status);
            id_issue = (TextView) view.findViewById(R.id.id_issue);
        }
    }


    public IssueAdapter(List<Issues> issuesList) {
        this.issuesList = issuesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Issues issue = issuesList.get(position);
        holder.category_name.setText(issue.getCategory_name()+" ");
        holder.description.setText(issue.getDescription()+" ");
        holder.status.setText(issue.getStatus()+ " " );
        holder.id_issue.setText(issue.getId_issue()+" ");
    }

    @Override
    public int getItemCount() {
        return issuesList.size();
    }
}
