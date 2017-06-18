package app.classlink.backend;


public class studyGroup extends baseGroup {

    public studyGroup(GROUP_TYPE groupType, int groupId, String groupName, String groupDescription) {
        this.setGroupType(groupType);
        this.setGroupId(groupId);
        this.setGroupName(groupName);
        this.setGroupDescription(groupDescription);
    }

}
