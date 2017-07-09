package app.classlink.helperClasses;

/**
 * Created by jaywe on 2017-06-09.
 * Adapter for gathering studyGroup information and feeding it to a recyclerView.
 * It takes in a list of studyGroups and inflates the recyclerView.
 * Probably will rewrite this more generally or write separate adapters for notifications/other recyclerViews.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.classlink.R;
import app.classlink.backend.groups.study.studyGroup;

public class groupAdapter extends RecyclerView.Adapter<groupAdapter.MyViewHolder> {

    private List<studyGroup> groupList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            image = (ImageView) view.findViewById(R.id.image);
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
        //holder.image.setImageResource();
        //fill this in when database is working

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
