package app.classlink.helperClasses.recyclerAdapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import app.classlink.R;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.users.user.user;

/**
 * @Class displayStatementAdapter : class handler for displaying a list view of grouped statements in the lecture group
 */
public class displayStatementAdapter extends RecyclerView.Adapter<displayStatementAdapter.groupedStatementHolder> {

    private static LinkedList<groupedStatement> displayableStatements;

    public static class groupedStatementHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        private groupedStatement currentStatement;
        private ArrayList<String> userUpvoteIdList;
        private user currentUser;

        private TextView statementMessage;
        private TextView writtenTime;
        private TextView score;
        private ImageView upvoteButton;
        private ImageView topLine;
        private ImageView bottomLine;

        public groupedStatementHolder(View itemView) {
            super(itemView);
            this.statementMessage = (TextView) itemView.findViewById(R.id.statement_message);
            this.score = (TextView) itemView.findViewById(R.id.statement_score);
            this.upvoteButton = (ImageView) itemView.findViewById(R.id.upvote_button);
            this.upvoteButton.setImageResource(R.drawable.upvote);
            this.upvoteButton.setScaleX(1.5f);
            this.upvoteButton.setScaleY(1.5f);
            this.setUpvoteButton();
            this.bottomLine = (ImageView) itemView.findViewById(R.id.line_separator_bottom);
            this.bottomLine.setImageResource(R.drawable.line);
            this.topLine = (ImageView) itemView.findViewById(R.id.line_separator_top);
            this.topLine.setImageResource(R.drawable.line);
            itemView.setOnTouchListener(this);
        }

        public void bindGroupedStatement(groupedStatement groupedStatement){
            this.currentStatement = groupedStatement;
            this.statementMessage.setText(groupedStatement.getStatementQuestion().getQuestionText());
            this.score.setText((String.valueOf(groupedStatement.getStatementQuestion().getScore())));
        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_BUTTON_PRESS:
                    Log.d("BUTTON PRESS", "RANDOM PRESS ACTION");
                    break;
                case MotionEvent.ACTION_BUTTON_RELEASE:
                    Log.d("BUTTON RELEASE", "RANDOM RELEASE MESSAGE");
                    break;
            }
            return false;
        }

        private void setUpvoteButton(){
            this.upvoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int oldScore = Integer.parseInt(score.getText().toString());
                    int newScore = ++oldScore;
                    score.setText(String.valueOf(newScore));
                    currentStatement.getStatementQuestion().setScore(newScore);
                }
            });
        }
    }

    public displayStatementAdapter(LinkedList<groupedStatement> displayableStatements){
        displayStatementAdapter.displayableStatements = displayableStatements;
    }

    @Override
    public displayStatementAdapter.groupedStatementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_statement_row, parent, false);
        return new groupedStatementHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(displayStatementAdapter.groupedStatementHolder holder, int position) {
       groupedStatement statement = displayableStatements.get(position);
        holder.bindGroupedStatement(statement);
    }

    @Override
    public int getItemCount() {
        return displayStatementAdapter.displayableStatements.size();
    }

    /**
     * @Method swapData : updates handler with new data, assumes the data passed in is already in the correct order
     * @param data : linked list of incoming group statements
     */
    public void swapData(LinkedList<groupedStatement> data){
        if (data == null || data.size() == 0){
            return;
        } else {
            //This performs a check between both linked lists and adds new nodes to the current displayStatements cache
//            for (groupedStatement inListStatement : displayStatementAdapter.displayableStatements){
//                for (groupedStatement incomingStatement : data){
//
//                }
//            }
            displayStatementAdapter.displayableStatements.clear();
            displayStatementAdapter.displayableStatements = data;
            notifyDataSetChanged();
        }
    }
}
