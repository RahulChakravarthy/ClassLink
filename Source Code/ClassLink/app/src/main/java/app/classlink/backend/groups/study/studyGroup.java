package app.classlink.backend.groups.study;


import java.util.HashMap;
import java.util.LinkedList;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.statement.statementGrouping.groupedStatement;

/**
 * @Class studyGroup : Handles internal functions of a lecture group (ALL GROUPS MUST BE STORED IN FIREBASE)
 */
public class studyGroup extends baseGroup {

    protected HashMap<String, String> lectureGroupTags; // Used for searching for the group
    protected LinkedList<groupedStatement> statements; //Linkedlist stores all statements in order of which they were asked

    public studyGroup(GROUP_TYPE groupType, String groupId, String groupName, String groupDescription) {
        this.groupType = groupType;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;

        this.lectureGroupTags = new HashMap<>();
        this.createLectureTags();
    }

    /**
     * @Method createLectureTags : creates tags in which the group can be accessed while performing a search query
     */
    private void createLectureTags() {
        lectureGroupTags.put("Group Name", this.groupName);
        //add more tags as you go on (use these keys to provide categories in which users can search by
    }

    @Override
    public LinkedList<groupedStatement> getGroupStatements() {
        return this.statements;
    }

    @Override
    public void setGroupedStatements(LinkedList<groupedStatement> newGroupedStatements) {
        this.statements = newGroupedStatements;
    }
}
