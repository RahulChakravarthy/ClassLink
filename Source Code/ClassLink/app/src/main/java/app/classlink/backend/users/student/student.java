package app.classlink.backend.users.student;

import java.util.Calendar;
import java.util.HashMap;

import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;

/**
 * @Class student : student class object
 */
public class student extends user {

    protected int studentId, studentScore;
    protected HashMap<lectureGroup, ban> banList; //lecture Group you are banned from, and String date that you will be unbanned

    public student(String firstName, String lastName, String userName, String password, String securityQuestion, String securityAnswer, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.school = school;

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

}

/**
 * @Class ban : handles all ban related information regarding user
 */
class ban {
    Calendar calendar;
    protected int lengthOfBanHours;
    protected boolean isBanComplete;
    protected String banStart;

    public ban(int lengthOfBanHours){
        this.lengthOfBanHours = lengthOfBanHours;
        this.isBanComplete = false;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        this.banStart = cal.getTime().toString();
    }

    //Call this method evey so often to update length of ban hours left
    public void updateBanDates(){
        //get the start of the ban and see if enough time has elapesed, if so set the ban to off
    }

    public boolean BanComplete(){
        return this.isBanComplete;
    }
}


