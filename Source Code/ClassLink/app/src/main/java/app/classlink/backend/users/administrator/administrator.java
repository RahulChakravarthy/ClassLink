package app.classlink.backend.users.administrator;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;

/**
 *@Class administrator's have overall app functionality access and can go in to change modify and even delete groups
 */

public class administrator extends user {

    private int adminScore;

    public administrator(String adminFirstName, String adminLastName, String userName, String securityQuestion, String securityAnswer, School school){
        this.firstName = adminFirstName;
        this.lastName = adminLastName;
        this.userName = userName;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.school = school;
        this.permissionLevel = 3;
        this.adminScore = 100; //cuz they are admins lol
    }

    @Override
    public int getUserPermission() {
        return this.permissionLevel;
    }

    @Override
    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public int getAdminScore() {return this.adminScore;}

    public void setAdminScore(int adminScore) {this.adminScore = adminScore;}
}
