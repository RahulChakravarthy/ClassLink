package app.classlink.backend.groups.lecture;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.misc.School;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.question;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class lectureGroup : Handles internal functions of a lecture group (ALL GROUPS MUST BE STORED IN FIREBASE)
 */
public class lectureGroup extends baseGroup {

    protected teacher lectureCreator; //The object of the teacher who created the group
    protected HashMap<String, String> lectureGroupTags; // Used for searching for the group
    protected HashMap<String, groupedStatement> groupedStatement; //HashMap stores time in which question was asked as key and grouped statement as value

    /**
     * @Constructor : default constructor
     * @param schoolName : school name
     * @param groupName : name of group
     * @param groupId : groupId
     * @param groupDescription : group Description
     * @param lectureCreator : teacher who created the group
     */
    public lectureGroup(School schoolName, String groupName, String groupId, String groupDescription, teacher lectureCreator){
        this.groupType = GROUP_TYPE.LECTUREGROUPS;
        this.schoolName = schoolName;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;
        this.lectureCreator = lectureCreator;
        this.lectureGroupTags = new HashMap<>();
        this.groupedStatement = new HashMap<>();

        this.setStatements();
        this.createLectureTags();
    }

    /**
     * @Consructor DATASNAPSHOT CONSTRUCTOR : DO NOT USE FOR REGULAR OBJECT CREATION
     */
    public lectureGroup(){
        //ONLY USED BY DATA SNAPSHOT
    }

    /**
     * @Method setStatements : set default value to groupedStatement so that they are registered in the database and can be queired
     */
    private void setStatements() {
        question nullQuestion = new question("Welcome to the lecture room!", "NULL");
        groupedStatement statement = new groupedStatement(nullQuestion);
        this.groupedStatement.put(nullQuestion.getWrittenTime(), statement);
    }

    /**
     * @Method createLectureTags : creates tags in which the group can be accessed while performing a search query
     */
    private void createLectureTags() {
        this.lectureGroupTags.put("groupName", this.groupName);
        this.lectureGroupTags.put("groupDescription", this.groupDescription);
        this.lectureGroupTags.put("teacherFirstName", this.lectureCreator.getFirstName());
        this.lectureGroupTags.put("teacherLastName", this.lectureCreator.getLastName());
    }

    /**
     * @Method getTagByTagValue : returns a specific tag by value (helps with searching up groups and minimizes time complexity of query
     */
    public String getTagByTagValue(String tagValue){
        return this.lectureGroupTags.get(tagValue);
    }

    /**
     * @Method addGroupedStatement : create a new grouped statement and add it to the lecture room database
     * @param questionText : text for question
     * @param userId : user id of user who asked question
     */
    public void addGroupedStatement(String questionText, String userId){
        groupedStatement.put(questionText, new groupedStatement(new question(questionText,userId)));
        LectureGroupDAO lectureGroupDAO = new LectureGroupDAO();
        lectureGroupDAO.updateLectureGroup(this);
    }

    /**
     * @Method addAnswerToQuestion : appends an answer to a specific question in the groupedStatement
     * @param statement : the grouped statement in question
     * @param answerText : the text for the answer
     * @param userId : user who issued answer
     */
    public void addAnswerToQuestion(groupedStatement statement, String answerText, int userId){
        LectureGroupDAO lectureGroupDAO = new LectureGroupDAO();
        lectureGroupDAO.updateLectureGroup(this);
    }

    /**
     * @Method addAnswerToQuestion : appends an answer to a specific question in the groupedStatement
     * @param statement : the grouped statement in question
     * @param commentText : the text for the comment
     * @param userId : user who issued the comment
     */
    public void addCommentToAnswer(groupedStatement statement, answers userAnswer, String commentText, int userId){
        LectureGroupDAO lectureGroupDAO = new LectureGroupDAO();
        lectureGroupDAO.updateLectureGroup(this);
    }

    /**
     * Getters and Setters
     */
    @Override
    public HashMap<String, groupedStatement> getGroupedStatement() {
        return this.groupedStatement;
    }

    @Override
    public void setGroupedStatement(HashMap<String, groupedStatement> newGroupedStatements) {
        this.groupedStatement = newGroupedStatements;
    }

    public teacher getLectureCreator(){
        return this.lectureCreator;
    }
}
