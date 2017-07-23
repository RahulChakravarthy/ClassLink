package app.classlink.backend.groups.lecture;


import java.util.HashMap;
import java.util.LinkedList;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.comments;
import app.classlink.backend.statement.statementType.question;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class lectureGroup : Handles internal functions of a lecture group (ALL GROUPS MUST BE STORED IN FIREBASE)
 */
public class lectureGroup extends baseGroup {

    protected teacher lectureCreator; //The object of the teacher who created the group
    protected HashMap<String, String> lectureGroupTags; // Used for searching for the group
    protected LinkedList<groupedStatement> statements; //Linkedlist stores all statements in order of which they were asked THIS MIGHT NOT BE NEEDED

    public lectureGroup(GROUP_TYPE groupType, String groupName, String groupId, String groupDescription, teacher lectureCreator){
        this.groupType = groupType;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;
        this.lectureCreator = lectureCreator;

        this.lectureGroupTags = new HashMap<>();
        this.createLectureTags();
    }

    /**
     * @Method createLectureTags : creates tags in which the group can be accessed while performing a search query
     */
    private void createLectureTags() {
        lectureGroupTags.put("groupName", this.groupName);
        //add more tags as you go on (use these keys to provide categories in which users can search by
    }

    /**
     * @Method getTagByTagValue : returns a specific tag by value (helps with searching up groups and minimizes time complexity of query
     */
    public String getTagByTagValue(String tagValue){
        return this.lectureGroupTags.get(tagValue);
    }

    /**
     * @Method getGroupStatements returns and array of all group statements (questions, answers, comments) in order of when they were asked
     * @return array of all grouped statements
     */
    @Override
    public LinkedList<groupedStatement> getGroupStatements() {
        return this.statements;
    }

    /**
     * @Method addGroupedStatement : create a new grouped statement and add it to the lecture room database
     * @param questionText : text for question
     * @param userId : user id of user who asked question
     */
    public void addGroupedStatement(String questionText, int userId){
        groupedStatement newStatement = new groupedStatement(new question(questionText,userId));
        statements.addLast(newStatement);
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
    public void addCommentToAnswer(groupedStatement statement, answers userAnswer, String commentText, int userId){
        statement.addComment(userAnswer, new comments(commentText, userId));
    }
}
