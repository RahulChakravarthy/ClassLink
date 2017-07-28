package app.classlink.backend.users.user;


import java.util.HashMap;

import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.misc.School;

public abstract class user {
    protected int userId;
    protected String userName, password, firstName, lastName, email, securityQuestion, securityAnswer;
    protected School school;
    protected int permissionLevel; // 1 = student, 2 = teacher, 3 = administrator, 4 = god?????

    abstract public int getUserPermission();

    abstract public void setPermissionLevel(int permissionLevel);

    /**
     *Getters and Setters for the user class
     */
    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getPassword(){return this.password;}

    public void setPassword(String password){this.password = password;}

    public String getFirstName(){return this.firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return this.lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email = email;}

    public String getSecurityQuestion() {return securityQuestion;}

    public void setSecurityQuestion(String securityQuestion) {this.securityQuestion = securityQuestion;}

    public String getSecurityAnswer() {return securityAnswer;}

    public void setSecurityAnswer(String securityAnswer) {this.securityAnswer = securityAnswer;}

    public School getSchool(){
        return this.school;
    }

    public void setSchool(School school){
        this.school = school;
    }
}
