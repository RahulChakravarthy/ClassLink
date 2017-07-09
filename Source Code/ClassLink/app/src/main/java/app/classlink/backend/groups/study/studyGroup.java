package app.classlink.backend.groups.study;


import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.baseGroup;

public class studyGroup extends baseGroup {

    public studyGroup(GROUP_TYPE groupType, int groupId, String groupName, String groupDescription) {
        this.groupType = groupType;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

}
