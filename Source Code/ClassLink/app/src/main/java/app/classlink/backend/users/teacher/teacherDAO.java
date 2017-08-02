package app.classlink.backend.users.teacher;

import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.userDAO;

/**
 * @Class teacherDAO : DAO for teacher class individuals
 */
public class teacherDAO extends userDAO {
    /**
     * @Constructor: initializes the connection
     */
    public teacherDAO() {
        this.list = this.list.child(listNames.TEACHERS);
    }

    /**
     * @Method createTeacher programmatically creates a group in and stores it in the database
     * @param teacherFirstName : first name
     * @param teacherLastName : last name
     * @param teacherUsername : user name
     * @param school : School that this teacher belongs to
     * @return boolean : whether teacher was created or not
     */
    public boolean createTeacher(String teacherFirstName, String teacherLastName, String teacherUsername, String securityQuestion, String securityAnswer, School school){
        //check if teacher already exists throw false
        String teacherId = this.list.push().getKey();
        teacher newTeacher = new teacher(teacherFirstName, teacherLastName, teacherUsername, securityQuestion, securityAnswer, school);
        newTeacher.setUserId(teacherId);
        this.list.child(teacherId).setValue(newTeacher);
        return true;
    }


    /**
     * @Method createTeacher : creates a teacher object and updates the database with it
     * @param teacher : teacher reference
     * @return boolean : true if teacher was created
     */
    public boolean createTeacher(teacher teacher){
        String teacherId = this.list.push().getKey();
        teacher.setUserId(teacherId);
        this.list.child(teacherId).setValue(teacher);
        return true;
    }

    /**
     * @Method getTeacherByEmail
     * @param teacherEmail : email address of teacher
     * @return teacher
     */
    public teacher getTeacherByEmail(String teacherEmail){
        for (teacher child : teacherCache.values()){
            if (child.getEmail().equals(teacherEmail)){
                return child; //only one email per user
            }
        }
        return  null; //if user doesn't exist
    }

    /**
     * @Method deleteTeacherById
     * @param teacherId : Id string of teacher
     */
    public void deleteTeacherById(String teacherId){
        this.list.child(teacherId).removeValue();
    }

    /**
     * @Method updateTeacher : updates an existing teacher with new provided data
     * @param teacher : teacher user
     */
    public void updateTeacher(teacher teacher){
        this.list.child(teacher.getUserId()).setValue(teacher);
    }
}
