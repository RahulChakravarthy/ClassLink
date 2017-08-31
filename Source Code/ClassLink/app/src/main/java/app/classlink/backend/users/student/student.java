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

    protected int studentScore;
    protected HashMap<lectureGroup, ban> banList; //lecture Group you are banned from, and String date that you will be unbanned

    public student(String firstName, String lastName, String email, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.permissionLevel = 1;
        this.studentScore = 0;
    }
    /**
     * @Consructor DATASNAPSHOT CONSTRUCTOR : DO NOT USE FOR REGULAR OBJECT CREATION
     */
    public student(){
        //ONLY USED BY DATA SNAPSHOT
    }

    @Override
    public int getPermissionLevel() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}

    @Override
    public int getUserScore() {
        return this.studentScore;
    }

    @Override
    public void setUserScore(int score) {
        this.studentScore = score;
    }

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


