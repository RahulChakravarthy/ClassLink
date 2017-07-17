package app.classlink.backend.users.student;

import java.util.HashMap;

import app.classlink.backend.users.user.user;

/**
 * @Class student : student class object
 */
public class student extends user {

    protected int studentId, studentScore;
    protected HashMap<String, prohibition> groupAccess; //this stores the status that this student has with every group it has ever entered


    public student(String firstName, String lastName, String userName, String password, String securityQuestion, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;

        this.permissionLevel = 1;
        this.studentScore = 0;
        //generate userId
    }

    @Override
    public int getUserPermission() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}

    /**
     * Getters and Setters for the Student Class
     */
    public int getStudentId() {return this.studentId;}

    public void setStudentId(int studentId) {this.studentId = studentId;}

    public int getStudentScore() {return this.studentScore;}

    public void setStudentScore(int studentScore) {this.studentScore = studentScore;}


    /**
     * Prohibition enumeration
     */
    private enum prohibition {
        FULLACCESS(0),
        CHATMUTED(1),
        BANNED(2);

        private final int restrictionLevel;

        prohibition(int restrictionLevel){
            this.restrictionLevel = restrictionLevel;
        }
    }
}

