package app.classlink.backend.groups.study;


import java.util.HashMap;
import java.util.LinkedList;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.comments;
import app.classlink.backend.statement.statementType.question;
import app.classlink.backend.users.student.student;

/**
 * @Class studyGroup : Handles internal functions of a lecture group (ALL GROUPS MUST BE STORED IN FIREBASE)
 */
public class studyGroup extends baseGroup {

    protected student groupCreator;
    protected HashMap<String, String> studyGroupTags; // Used for searching for the group
    protected LinkedList<groupedStatement> statements; //Linkedlist stores all statements in order of which they were asked

    public studyGroup(String groupId, String groupName, String groupDescription, student groupCreator) {
        this.groupType = GROUP_TYPE.STUDY_GROUP;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;
        this.groupCreator = groupCreator;
        this.studyGroupTags = new HashMap<>();
        this.statements = new LinkedList<>();

        this.createGroupTags();

    }

    /**
     * @Consructor DATASNAPSHOT CONSTRUCTOR : DO NOT USE FOR REGULAR OBJECT CREATION
     */
    public studyGroup(){
        //ONLY USED BY DATA SNAPSHOT
    }

    /**
     * @Method createLectureTags : creates tags in which the group can be accessed while performing a search query
     */
    private void createGroupTags() {
        this.studyGroupTags.put("groupName", this.groupName);
        this.studyGroupTags.put("groupDescription", this.groupDescription);
    }

    public String getTagByTagValue(String tagValue){
        return this.studyGroupTags.get(tagValue);
    }

    public void addGroupedStatement(String questionText, int userId){
        statements.addLast(new groupedStatement(new question(questionText,userId)));
        StudyGroupDAO studyGroupDAO = new StudyGroupDAO();
        studyGroupDAO.updateStudyGroup(this);
    }

    public void addAnswerToQuestion(groupedStatement statement, String answerText, int userId){
        statement.addAnswer(new answers(answerText, userId));
        StudyGroupDAO studyGroupDAO = new StudyGroupDAO();
        studyGroupDAO.updateStudyGroup(this);
    }

    public void addCommentToAnswer(groupedStatement statement, answers userAnswer, String commentText, int userId){
        statement.addComment(userAnswer, new comments(commentText, userId));
        StudyGroupDAO studyGroupDAO = new StudyGroupDAO();
        studyGroupDAO.updateStudyGroup(this);
    }



    @Override
    public LinkedList<groupedStatement> getGroupStatements() {
        return this.statements;
    }

    @Override
    public void setGroupedStatements(LinkedList<groupedStatement> newGroupedStatements) {
        this.statements = newGroupedStatements;
    }

    public student getGroupCreator() { return this.groupCreator; }
}
