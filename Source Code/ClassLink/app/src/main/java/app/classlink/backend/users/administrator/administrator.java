package app.classlink.backend.users.administrator;

import app.classlink.backend.users.user.user;

/**
 *@Class administrator's have overall app functionality access and can go in to change modify and even delete groups
 */

public class administrator extends user {

    private int adminId;

    public administrator(String firstName, String lastName){
        this.permissionLevel = 3;
        this.firstName = firstName;
        this.lastName = lastName;

        //generate admin and user Id

    }
}
