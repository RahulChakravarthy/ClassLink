package app.classlink.backend.users.student;

import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.userDAO;

/**
 * @Class studentDAO : DAO for student class individuals
 */
public class studentDAO extends userDAO {
    /**
     * @Constructor: initializes the connection
     */
    public studentDAO() {
        this.list = this.list.child(listNames.STUDENTS);
    }

    /**
     * @Method createStudent programmatically creates a student and stores it in the database
     * @param studentFirstName : first name
     * @param studentLastName : last name
     * @param studentUsername : user name
     * @param school : School that this student belongs to
     * @return boolean : whether student was created
     */
    public boolean createStudent(String studentFirstName, String studentLastName, String studentUsername, String securityQuestion, String securityAnswer, School school){
        //check if student already exists throw false
        String studentId = this.list.push().getKey();
        student newStudent = new student(studentFirstName, studentLastName, studentUsername, securityQuestion, securityAnswer, school);
        newStudent.setUserId(studentId);
        this.list.child(studentId).setValue(newStudent);
        return true;
    }

    /**
     * @Method createTeacher : creates a student object and updates the database with it
     * @param student : student reference
     * @return boolean : true if student was created
     */
    public boolean createStudent(student student){
        String teacherId = this.list.push().getKey();
        student.setUserId(teacherId);
        this.list.child(teacherId).setValue(student);
        return true;
    }

    /**
     * @Method getStudentById
     * @param studentId : Id string of the student
     * @return student
     */
    public student getStudentById(String studentId){
        return null;
    }

    /**
     * @Method getStudentByEmail
     * @param studentEmail : email address of student
     * @return student
     */
    public student getStudentByEmail(String studentEmail){
        return null;
    }

    /**
     * @Method deleteStudentById
     * @param studentId : Id string of student
     * @retrurn boolean if it was succesful
     */
    public boolean deleteStudentById(String studentId){
        return true;
    }

    /**
     * @Method deleteStudent By Emaail : delete a student by email
     * @param email : email address of student
     * @return
     */
    public boolean deleteStudentByEmail(String email){
        return true;
    }

    /**
     * @Method updateStudent : updates an existing student with new provided data
     * @param student
     * @return
     */
    public boolean updateStudent(student student){
        this.list.child(student.getUserId()).setValue(student);
        return true;
    }
}
