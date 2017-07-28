package app.classlink.backend.users.teacher;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;


/**
 * @Class teacher : teacher class
 */
public class teacher extends user {

    protected int teacherScore;
    protected int teacherId;

    public teacher(String firstName, String lastName, String userName, String password, String securityQuestion, String securityAnswer, School school){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.school = school;
        this.permissionLevel = 2;

        this.teacherScore = 0;
    }

    @Override
    public int getUserPermission() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}
}
