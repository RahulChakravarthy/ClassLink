package app.classlink.favouritesBar;

/**
 * Created by jaywe on 2017-06-09.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.classlink.R;
import app.classlink.backend.studyGroup;

public class groupAdapter extends RecyclerView.Adapter<groupAdapter.MyViewHolder> {

    private List<studyGroup> groupList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, id;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            id = (TextView) view.findViewById(R.id.id);
        }
    }

    public groupAdapter(List<studyGroup> groupList) {
        this.groupList = groupList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(groupAdapter.MyViewHolder holder, int position) {
        studyGroup studyGroup = groupList.get(position);
        holder.name.setText(studyGroup.getGroupName());
        holder.description.setText(studyGroup.getGroupDescription());
//        holder.id.setText(studyGroup.getGroupId());

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
