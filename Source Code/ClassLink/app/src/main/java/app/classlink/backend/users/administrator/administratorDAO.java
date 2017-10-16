package app.classlink.backend.users.administrator;

import app.classlink.backend.misc.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.userDAO;

/**
 * @Class administratorDAO : DAO for the administrator class individuals
 */
public class administratorDAO extends userDAO {
    /**
     * @Constructor: initializes the connection
     */
    public administratorDAO() {
        this.list = this.list.child(listNames.ADMIN); //push data under users/admin
    }

    /**
     * @Method createAdmin programmatically creates an admin and stores it in the database
     * @param adminFirstName : first name
     * @param adminLastName : last name
     * @param adminUserName : user name
     * @param school : School that this admin belongs to
     * @return boolean : whether admin was created or not
     */
    public boolean createAdmin(String adminFirstName, String adminLastName, String adminUserName, String securityQuestion, String securityAnswer, School school){
        //check if admin already exists, if so throw false
        String adminId = this.list.push().getKey();
        administrator newAdmin = new administrator(adminFirstName, adminLastName, adminUserName, school);
        newAdmin.setUserId(adminId);
        this.list.child(adminId).setValue(newAdmin);
        return true;
    }

    /**
     * @Method createAdmin : creates a admin object and updates the database with it
     * @param admin : admin reference
     * @return boolean : true if admin was created
     */
    public boolean createAdmin(administrator admin){
        String adminId = this.list.push().getKey();
        admin.setUserId(adminId);
        this.list.child(adminId).setValue(admin);
        return true;
    }

    /**
     * @Method getAdminByEmail
     * @param adminEmail : email address of admin
     * @return student
     */
    public administrator getAdministratorByEmail(String adminEmail){
        for (administrator child : adminCache.values()){
            if (child.getEmail().equals(adminEmail)){
                return child; //only one email per user
            }
        }
        return  null; //if user doesn't exist
    }

    /**
     * @Method deleteAdminById
     * @param adminId : Id string of admin
     */
    public void deleteAdminById(String adminId){
        this.list.child(adminId).removeValue();
    }

    /**
     * @Method updateAdmin : updates an existing admin with new provided data
     * @param admin : admin user
     */
    public void updateAdmin(administrator admin){
        this.list.child(admin.getUserId()).setValue(admin);
    }
}
