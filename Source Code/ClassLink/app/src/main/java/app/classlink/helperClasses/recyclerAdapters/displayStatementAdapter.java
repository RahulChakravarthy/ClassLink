package app.classlink.helperClasses.recyclerAdapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import app.classlink.R;
import app.classlink.backend.statement.statementGrouping.groupedStatement;

/**
 * @Class displayStatementAdapter : class handler for displaying a list view of grouped statements in the lecture group
 */
public class displayStatementAdapter extends RecyclerView.Adapter<displayStatementAdapter.groupedStatementHolder> {

    LinkedList<groupedStatement> displayableStatements;

    public static class groupedStatementHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        private TextView statementMessage;
        private TextView score;
        private ImageView upvotebutton;
        private ImageView topLine;
        private ImageView bottomLine;

        public groupedStatementHolder(View itemView) {
            super(itemView);
            this.statementMessage = (TextView) itemView.findViewById(R.id.statement_message);
            this.score = (TextView) itemView.findViewById(R.id.statement_score);
            this.upvotebutton = (ImageView) itemView.findViewById(R.id.upvote_button);
            this.bottomLine = (ImageView) itemView.findViewById(R.id.line_separator_bottom);
            this.topLine = (ImageView) itemView.findViewById(R.id.line_separator_top);
            itemView.setOnTouchListener(this);
        }

        public void bindGroupedStatement(groupedStatement groupedStatement){
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
    }

    public displayStatementAdapter(LinkedList<groupedStatement> displayableStatements){
        this.displayableStatements = displayableStatements;
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
        return this.displayableStatements.size();
    }
}
