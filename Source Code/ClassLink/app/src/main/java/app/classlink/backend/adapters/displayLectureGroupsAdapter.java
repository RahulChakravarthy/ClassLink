package app.classlink.backend.adapters;

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
import app.classlink.frontend.lectureRoom;

/**
 * RecyclerView Adapter for displaying lecture groups to the join a lecture group activity
 */
public class displayLectureGroupsAdapter extends RecyclerView.Adapter<displayLectureGroupsAdapter.ViewHolder>{

    private static ArrayList<lectureGroup> data = new ArrayList<>(); //ArrayList that holds all lecture group objects
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

        /**
         * @Consructor: views defined the xml to views defined in the class
         * @param itemView : contains all the views in the layout
         */
        public ViewHolder(View itemView) {
            super(itemView);
            this.lectureGroupName = (TextView) itemView.findViewById(R.id.lecture_group_name);
            this.lectureGroupTeacher = (TextView) itemView.findViewById(R.id.lecture_group_creator);
            this.lectureGroupDescription = (TextView) itemView.findViewById(R.id.lecture_group_description);
            itemView.setOnClickListener(this);
        }

        /**
         * @Method bindData : assigns specific values to views
         * @param group : current lecture Group
         */
        public void bindData(lectureGroup group){
            this.lectureGroupName.setText(group.getGroupName());
            this.lectureGroupTeacher.setText("Teacher: " + group.getLectureCreator().getFirstName() + " " + group.getLectureCreator().getLastName());
            this.lectureGroupDescription.setText(group.getGroupDescription());
        }

        /**
         * @Method onClick : Launch the lectureGroup activity with the specific lecture group information
         * @param view : current view that's being clicked on
         */
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

    /**
     * @Method onCreateViewHolder : sets up the UI for the view holder given the lecture list relative layout template
     * @param parent : view parent used to get the context
     * @param viewType : view type
     * @return ViewHolder : the ViewHolder with the newly inflated view
     */
    @Override
    public displayLectureGroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_list, parent, false);
        return new ViewHolder(inflatedView);
    }

    /**
     * @Method onBindViewHolder : binds an element to the view holder that was created
     * @param holder : view holder that you are adding elements to
     * @param position : the position of the data in the list
     */
    @Override
    public void onBindViewHolder(displayLectureGroupsAdapter.ViewHolder holder, int position) {
        lectureGroup item = displayLectureGroupsAdapter.data.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor("#bdecfd"));
        holder.bindData(item);
    }

    /**
     * @Method getItemCount : get the total number of elements in this view holder
     * @return int count
     */
    @Override
    public int getItemCount() {
        return displayLectureGroupsAdapter.data.size();
    }

    /**
     * @Method swapData : handles refreshing of new LectureGroups
     * @param data
     */
    public void swapData(ArrayList<lectureGroup> data){
        if (!(data == null || data.size() == 0 || data.equals(displayLectureGroupsAdapter.data))) {
            displayLectureGroupsAdapter.data.clear();
            displayLectureGroupsAdapter.data = data;
            notifyDataSetChanged();
        }
    }
}
