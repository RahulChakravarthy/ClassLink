package app.classlink.backend.groups.lecture;


import java.util.ArrayList;
import java.util.HashMap;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.comments;
import app.classlink.backend.statement.statementType.question;

/**
 * @Class lectureGroup : Handles internal functions of a lecture group
 */
public class lectureGroup extends baseGroup {

    protected ArrayList<String> lectureGroupTags; // Used for searching for the group
    protected HashMap<String, groupedStatement> statements; //Hashmap stores all user statements with time stamps as keys

    public lectureGroup(GROUP_TYPE groupType, String groupName, int groupId, String groupDescription){
        this.groupType = groupType;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;

        this.lectureGroupTags = new ArrayList<>();
        this.createLectureTags();
    }

    /**
     * @Method createLectureTags : creates tags in which the group can be accessed while performing a search query
     */
    private void createLectureTags() {
        lectureGroupTags.add(this.groupName);
    }

    /**
     * @Method getGroupStatements returns and array of all group statements (questions, answers, comments) in order of when they were asked
     * @return array of all grouped statements
     */
    @Override
    public ArrayList<groupedStatement> getGroupStatements() {
        ArrayList<groupedStatement> groupData = new ArrayList<>();
        for (String key: statements.keySet()){
            groupData.add(statements.get(key));
        }
        return groupData;
    }

    /**
     * @Method addGroupedStatement :
     * @param questionText : text for question
     * @param userId : user id of user who asked question
     */
    public void addGroupedStatement(String questionText, int userId){
        groupedStatement newStatement = new groupedStatement(new question(questionText,userId));
        statements.put("test", newStatement);
    }

    /**
     * @Method addAnswerToQuestion : appends an answer to a specific question in the groupedStatement
     * @param statement : the grouped statement in question
     * @param answerText : the text for the answer
     * @param userId : user who issued answer
     */
    public void addAnswerToQuestion(groupedStatement statement, String answerText, int userId){
        statement.addAnswer(new answers(answerText, userId));
    }

    /**
     * @Method addAnswerToQuestion : appends an answer to a specific question in the groupedStatement
     * @param statement : the grouped statement in question
     * @param commentText : the text for the comment
     * @param userId : user who issued the comment
     */
    public void addCommentToAnswer(groupedStatement statement, String commentText, int userId){
        statement.addComment(new comments(commentText, userId));
    }
}
