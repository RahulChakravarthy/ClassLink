package app.classlink.helperClasses.recyclerAdapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.classlink.R;
import app.classlink.backend.groups.lecture.lectureGroup;

/**
 * RecyclerView Adapter for displaying lecture groups to the join a lecture group activity
 */
public class displayLectureGroupsAdapter extends RecyclerView.Adapter<displayLectureGroupsAdapter.ViewHolder>{

    private ArrayList<lectureGroup> data = new ArrayList<>(); //Array that holds all lecture group objects

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
            //use findViewById to attach each member field to it's reference in the appropriate layout.xml
            itemView.setOnClickListener(this);


        }

        public void bindData(lectureGroup group){

        }

        @Override
        public void onClick(View view) {
            //Modify this to change activities when clicked
            Log.d("Object", "Clicked");
        }
    }

    /**
     * @Consructor : accepts an array of data to be displayed to the recycler view through this adapter
     */
    public displayLectureGroupsAdapter(ArrayList<lectureGroup> data){
        this.data = data;
    }

    @Override
    public displayLectureGroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_list, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(displayLectureGroupsAdapter.ViewHolder holder, int position) {
        lectureGroup item = this.data.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
