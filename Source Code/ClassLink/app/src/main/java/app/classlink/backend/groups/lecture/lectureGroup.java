package app.classlink.backend.groups.lecture;


import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;

/**
 * @Class lectureGroup : Handles internal functions of a lecture group
 */
public class lectureGroup extends baseGroup {

    protected String[] lectureGroupTags;

    public lectureGroup(GROUP_TYPE groupType, String groupName, int groupId, String groupDescription){
        this.groupType = groupType;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupDescription = groupDescription;
    }
}
