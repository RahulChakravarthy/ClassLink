package app.classlink.backend.adapters;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import app.classlink.R;
import app.classlink.backend.misc.DateParser;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementGrouping.groupedStatementDAO;
import app.classlink.backend.users.user.user;

/**
 * @Class displayStatementAdapter : class handler for displaying a list view of grouped statements in the lecture group
 */
public class displayStatementAdapter extends RecyclerView.Adapter<displayStatementAdapter.groupedStatementHolder> {

    private LinkedList<groupedStatement> displayableStatements;
    private static groupedStatementDAO groupedStatementDAO;
    private user currentUser;

    /**
     * @innerClass groupedStatementHolder : holds, binds and controls an element of the groupedStatement adapter contents
     */
    public static class groupedStatementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private groupedStatement currentStatement;
        private user currentUser;
        private TextView statementMessage;
        private TextView writtenTime;
        private TextView score;
        private ImageView upvoteButton;
        private ImageView topLine;
        private ImageView bottomLine;

        /**
         * @Consructor: Initialize class fields with equivalent xml views
         * @param itemView : item view context
         * @param currentUser : current user logged in
         */
        public groupedStatementHolder(View itemView, user currentUser) {
            super(itemView);

            //User process
            this.currentUser = currentUser;
            //Graphical Process
            styleUpvoteButton(itemView); //style the upvote button
            this.statementMessage = (TextView) itemView.findViewById(R.id.statement_message);
            this.score = (TextView) itemView.findViewById(R.id.statement_score);
            this.writtenTime = (TextView) itemView.findViewById(R.id.statement_written_time);
            this.writtenTime.setTextColor(Color.parseColor("#739CEC"));
            this.bottomLine = (ImageView) itemView.findViewById(R.id.line_separator_bottom);
            this.bottomLine.setImageResource(R.drawable.line);
            this.topLine = (ImageView) itemView.findViewById(R.id.line_separator_top);
            this.topLine.setImageResource(R.drawable.line);
            itemView.setOnClickListener(this);

        }

        /**
         * @Method bindGroupedStatement : set all group statement related data to class fields
         * @param groupedStatement : current grouped statement
         */
        public void bindGroupedStatement(groupedStatement groupedStatement){
            this.currentStatement = groupedStatement;
            this.statementMessage.setText(groupedStatement.getStatementQuestion().getQuestionText());
            this.score.setText((String.valueOf(groupedStatement.getStatementQuestion().getScore())));
            //parse date
            DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
            Date newStatementWrittenTime;
            try {
                newStatementWrittenTime = df.parse(groupedStatement.getStatementQuestion().getWrittenTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            this.writtenTime.setText(newStatementWrittenTime.toString());
        }

        @Override
        public void onClick(View view) {
//            if (currentUser.getPermissionLevel() >= 2) {
//                  they are professor/admin
//                  In the future implement more options for profs
//            }
        }

        /**
         * @Method styleUpvoteButton : sets the style of the upvote button upon being loaded
         * @param itemView : current item view
         */
        private void styleUpvoteButton(final View itemView){
            this.upvoteButton = (ImageView) itemView.findViewById(R.id.upvote_button);
            this.upvoteButton.setImageResource(R.drawable.upvote);
            this.upvoteButton.setScaleX(1.5f);
            this.upvoteButton.setScaleY(1.5f);
//          Determine state of button in new handler
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (currentStatement.getStatementQuestion().getUserEmailsWhoUpVoted().contains(currentUser.getUserId())){
                        upvoteButton.setImageResource(R.drawable.upvoted);
                    } else {
                        upvoteButton.setImageResource(R.drawable.upvote);
                    }
                    setUpvoteButton();
                }
            }, 50);
        }

        /**
         * @Method setUpVoteButton : sets condition and style of upvote button based on whether or not user has upvoted said statement
         */
        private void setUpvoteButton(){
            this.upvoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //User has not upvoted this statement
                    if (!currentStatement.getStatementQuestion().getUserEmailsWhoUpVoted().contains(currentUser.getUserId())){
                        int oldScore = Integer.parseInt(score.getText().toString());
                        int newScore = ++oldScore;
                        score.setText(String.valueOf(newScore));
                        currentStatement.getStatementQuestion().setScore(newScore);
                        //changes tint colour to signify that user has upvoted the statement
                        upvoteButton.setImageResource(R.drawable.upvoted);
                        currentStatement.getStatementQuestion().getUserEmailsWhoUpVoted().add(currentUser.getUserId());
                        currentUser.setUserScore(newScore);
                    } else { //user has already upvoted this statement, revert upvote and reset colour filter (reduce score by 1)
                        int oldScore = Integer.parseInt(score.getText().toString());
                        int newScore = --oldScore;
                        score.setText(String.valueOf(newScore));
                        currentStatement.getStatementQuestion().setScore(newScore);
                        currentStatement.getStatementQuestion().getUserEmailsWhoUpVoted().remove(currentUser.getUserId());
                        upvoteButton.setImageResource(R.drawable.upvote);
                        currentUser.setUserScore(newScore);
                    }
                    //update statement and user score on the remote database
                    displayStatementAdapter.groupedStatementDAO.updateStatement(currentStatement);
                }
            });
        }
    }

    /**
     * @Consructor: constructor for adapter
     * @param displayableStatements : all statements
     * @param groupedStatementDAO : displayStatementDAO
     * @param currentUser : current user
     */
    public displayStatementAdapter(LinkedList<groupedStatement> displayableStatements, groupedStatementDAO groupedStatementDAO, user currentUser){
        this.displayableStatements = displayableStatements;
        displayStatementAdapter.groupedStatementDAO = groupedStatementDAO;
        this.currentUser = currentUser;
    }

    /**
     * @Method onCreateViewHolder : creates a view holder for all elements
     * @param parent : view group parent
     * @param viewType : the view type
     * @return inflated view holder
     */
    @Override
    public displayStatementAdapter.groupedStatementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_statement_row, parent, false);
        return new groupedStatementHolder(inflatedView, currentUser);
    }

    /**
     * @Method onBindViewHolder : binds statement elements to the generated view holder
     * @param holder : displayStatementAdapter view holder
     * @param position : position of the statement
     */
    @Override
    public void onBindViewHolder(displayStatementAdapter.groupedStatementHolder holder, int position) {
       groupedStatement statement = displayableStatements.get(position);
        holder.bindGroupedStatement(statement);
    }

    /**
     * @Method getItemCount : gets the current numebr of elements in the view holder
     * @return amount of statement elements
     */
    @Override
    public int getItemCount() {
        return this.displayableStatements.size();
    }

    /**
     * @Method swapData : updates handler with new data, assumes the data passed in is already in the correct order
     * @param data : linked list of incoming group statements
     */
    public void swapData(LinkedList<groupedStatement> data){
        LinkedList<groupedStatement> cleanData = DateParser.getOrderedStatementsByDate(data);
        if (!(data == null || data.size() == 0 || this.displayableStatements.equals(cleanData))){
            this.displayableStatements.clear();
            this.displayableStatements = cleanData;
            notifyDataSetChanged();
        }
    }
}
