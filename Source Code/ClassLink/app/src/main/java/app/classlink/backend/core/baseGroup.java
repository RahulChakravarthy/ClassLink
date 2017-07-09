package app.classlink.backend.core;

import java.util.LinkedList;

import app.classlink.backend.utility.Question;
import app.classlink.backend.users.user.user;

public abstract class baseGroup {

    public GROUP_TYPE groupType;
    protected int groupId;
    protected String groupName;
    protected String groupDescription;
    protected LinkedList<user> userList;
    protected LinkedList<Question> questionList;

    public GROUP_TYPE getGroupType() {
        return groupType;
    }

    public void setGroupType(GROUP_TYPE groupType) {
        this.groupType = groupType;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }


}