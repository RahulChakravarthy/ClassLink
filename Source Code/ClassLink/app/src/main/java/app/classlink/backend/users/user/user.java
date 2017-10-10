package app.classlink.backend.users.user;


import java.io.Serializable;

import app.classlink.backend.misc.School;

/**
 * @Class user : base skeleton class for the users in ClassLink
 */
public abstract class user implements Serializable {
    protected String userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected School school;
    protected int permissionLevel; // 1 = student, 2 = teacher, 3 = administrator, 4 = god?????

    abstract public int getPermissionLevel();

    abstract public void setPermissionLevel(int permissionLevel);

    abstract public int getUserScore();

    abstract public void setUserScore(int score);

    /**
     *Getters and Setters for the user class
     */
    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    public String getFirstName(){return this.firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return this.lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email = email;}


    public School getSchool(){
        return this.school;
    }

    public void setSchool(School school){
        this.school = school;
    }
}
