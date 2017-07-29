package app.classlink.backend.users.teacher;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;


/**
 * @Class teacher : teacher class
 */
public class teacher extends user {

    protected int teacherScore;

    public teacher(String firstName, String lastName, String userName, String securityQuestion, String securityAnswer, School school){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.school = school;
        this.permissionLevel = 2;
        this.teacherScore = 0;
    }

    /**
     * Getters and Setters for the Student Class
     */
    @Override
    public int getUserPermission() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}

    public int getTeacherScore() {return this.teacherScore;}

    public void setTeacherScore(int teacherScore) {this.teacherScore = teacherScore;}
}
