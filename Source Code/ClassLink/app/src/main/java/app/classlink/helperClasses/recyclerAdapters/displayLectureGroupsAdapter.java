package app.classlink.helperClasses.recyclerAdapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.classlink.R;
import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.users.user.user;
import app.classlink.lectureRoom;

/**
 * RecyclerView Adapter for displaying lecture groups to the join a lecture group activity
 */
public class displayLectureGroupsAdapter extends RecyclerView.Adapter<displayLectureGroupsAdapter.ViewHolder>{

    private static ArrayList<lectureGroup> data = new ArrayList<>(); //LinkedList that holds all lecture group objects
    private static user currentUser;

    /**
     * @Class ViewHolder : holds on to reference of recycle data and all internal data
     * This is a reference to each individual object in the list
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //All class related variables
        private TextView lectureGroupName;
        private TextView lectureGroupDescription;
        private TextView lectureGroupTeacher;

        public ViewHolder(View itemView) {
            super(itemView);
            this.lectureGroupName = (TextView) itemView.findViewById(R.id.lecture_group_name);
            this.lectureGroupTeacher = (TextView) itemView.findViewById(R.id.lecture_group_creator);
            this.lectureGroupDescription = (TextView) itemView.findViewById(R.id.lecture_group_description);
            itemView.setOnClickListener(this);
        }

        public void bindData(lectureGroup group){
            this.lectureGroupName.setText(group.getGroupName());
            this.lectureGroupTeacher.setText("Teacher: " + group.getLectureCreator().getFirstName() + " " + group.getLectureCreator().getLastName());
            this.lectureGroupDescription.setText(group.getGroupDescription());
        }

        @Override
        public void onClick(View view) {
            //Modify this to change activities when clicked
            TextView lectureGroupName = (TextView) view.findViewById(R.id.lecture_group_name);
            for (lectureGroup group : data){
                if (group.getGroupName().equals(lectureGroupName.getText().toString())){
                    Intent intent = new Intent(view.getContext(), lectureRoom.class);
                    intent.putExtra("user", currentUser);
                    intent.putExtra("lectureGroup", group);
                    view.getContext().startActivity(intent);
                }
            }
        }
    }

    /**
     * @Consructor : accepts an array of data to be displayed to the recycler view through this adapter
     */
    public displayLectureGroupsAdapter(ArrayList<lectureGroup> data, user currentUser){
        displayLectureGroupsAdapter.data = data;
        displayLectureGroupsAdapter.currentUser =  currentUser;
    }

    @Override
    public displayLectureGroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_list, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(displayLectureGroupsAdapter.ViewHolder holder, int position) {
        lectureGroup item = displayLectureGroupsAdapter.data.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor("#bdecfd"));
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return displayLectureGroupsAdapter.data.size();
    }

    public void swapData(ArrayList<lectureGroup> data){
        if (!(data == null || data.size() == 0 || data.equals(displayLectureGroupsAdapter.data))) {
            displayLectureGroupsAdapter.data.clear();
            displayLectureGroupsAdapter.data = data;
            notifyDataSetChanged();
        }
    }
}
