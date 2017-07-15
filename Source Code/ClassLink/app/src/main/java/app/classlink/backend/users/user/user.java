package app.classlink.backend.users.user;


public abstract class user {
    protected int userId;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected int permissionLevel;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
