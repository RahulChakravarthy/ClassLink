package app.classlink.backend.users.teacher;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;


/**
 * @Class teacher : teacher class
 */
public class teacher extends user {

    protected int teacherScore;

    public teacher(String firstName, String lastName, String userName, School school){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.school = school;
        this.permissionLevel = 2;
        this.teacherScore = 0;
    }

    /**
     * @Consructor DATASNAPSHOT CONSTRUCTOR : DO NOT USE FOR REGULAR OBJECT CREATION
     */
    public teacher(){
        //ONLY USED BY DATA SNAPSHOT
    }

    /**
     * Getters and Setters for the teacher Class
     */
    @Override
    public int getPermissionLevel() {return this.permissionLevel;}

    @Override
    public void setPermissionLevel(int permissionLevel) {this.permissionLevel = permissionLevel;}

    public int getTeacherScore() {return this.teacherScore;}

    public void setTeacherScore(int teacherScore) {this.teacherScore = teacherScore;}
}
