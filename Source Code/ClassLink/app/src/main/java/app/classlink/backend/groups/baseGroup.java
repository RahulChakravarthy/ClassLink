package app.classlink.backend.groups;

import java.util.LinkedList;

import app.classlink.backend.utility.Question;
import app.classlink.backend.users.user.user;

public abstract class baseGroup {

    public GROUP_TYPE groupType;
    private int groupId;
    private String groupName;
    private String groupDescription;
    private LinkedList<user> userList;
    private LinkedList<Question> questionList;

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
