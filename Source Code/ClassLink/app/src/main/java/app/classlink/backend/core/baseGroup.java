package app.classlink.backend.core;

import java.io.Serializable;
import java.util.HashMap;

import app.classlink.backend.misc.School;
import app.classlink.backend.statement.statementGrouping.groupedStatement;

/**
 * @Class baseGroup : based class skeleton for a group type in ClassLink
 */
public abstract class baseGroup implements Serializable {

    public GROUP_TYPE groupType;
    protected String groupId;
    protected String groupName;
    protected String groupDescription;
    protected School schoolName;

    public GROUP_TYPE getGroupType() {
        return groupType;
    }

    public void setGroupType(GROUP_TYPE groupType) {
        this.groupType = groupType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
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

    public School getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(School schoolName){
        this.schoolName = schoolName;
    }

    abstract public HashMap<String, groupedStatement> getGroupedStatement();

    abstract public void setGroupedStatement(HashMap<String, groupedStatement> newGroupedStatements);
}
