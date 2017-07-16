package app.classlink.backend.users.teacher;

import app.classlink.backend.users.user.user;


/**
 * @Class teacher : teacher class
 */
public class teacher extends user {

    protected int teacherScore;
    protected int teacherId;

    public teacher(String firstName, String lastName, String userName, String password, String securityQuestion, String securityAnswer){
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissionLevel = 2;
    }

    @Override
    public int getUserPermission() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}
}
