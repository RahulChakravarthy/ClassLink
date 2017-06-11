package app.classlink.backend;

/**
 * Created by jaywe on 2017-06-09.
 */

public class studyGroup extends baseGroup {

    public studyGroup(GROUP_TYPE groupType, int groupId, String groupName, String groupDescription) {
        this.setGroupType(groupType);
        this.setGroupId(groupId);
        this.setGroupName(groupName);
        this.setGroupDescription(groupDescription);
    }

}
