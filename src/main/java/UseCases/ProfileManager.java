package UseCases;

import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileManager extends UseCase{

    public ProfileManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public String getName(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserEmail(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return ((Student) user).getEmail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserInfo(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCourseString(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            ArrayList<String> coursesList = ((Student) user).getEnrolledCourseCodes();
<<<<<<< HEAD
            ArrayList<String> lectureList = new ArrayList<>();
            ArrayList<String> tutorialList = new ArrayList<>();
            for(String course: coursesList){
                if (lectureList.contains(course)){
                    tutorialList.add(course);
                }
                else{
                    lectureList.add(course);
                }
            }
=======
>>>>>>> cf8a37a (Fixed an infinite loop in database)
            StringBuilder courseString = new StringBuilder();
            if(lectureList.isEmpty()){
                return courseString.toString();
            }
<<<<<<< HEAD
            courseString.append("Lectures:\n");
            for(String lecture: lectureList){
                System.out.println(lecture);
                if(cb.getCourse(lecture, "LEC") == null){
                    tutorialList.add(lecture);
                }
                else{
                    courseString.append(lecture);
                    courseString.append(": ");
                    courseString.append(cb.getCourse(lecture, "LEC").getCourseName());
                    courseString.append("\n");
                }
            }
            if(tutorialList.isEmpty()){
                return courseString.toString();
            }
            courseString.append("\n");
            courseString.append("Tutorials:\n");
            for(String tutorial: tutorialList){
                courseString.append(tutorial);
                courseString.append(": ");
                courseString.append(cb.getCourse(tutorial, "TUT").getCourseName());
=======
            for(String course: coursesList){
                courseString.append(course);
                courseString.append(": ");
                courseString.append(course);
>>>>>>> cf8a37a (Fixed an infinite loop in database)
                courseString.append("\n");
            }
            courseString.deleteCharAt(courseString.length() - 1);
            return courseString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmail(String userID, String email){
        try {
            Student student = (Student) ub.getUserByID(userID);
            student.setEmail(email);
            ub.addStudentUser(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInfo(String userID, String info){
        try {
            Student student = (Student) ub.getUserByID(userID);
            student.setUserInfo(info);
            ub.addStudentUser(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
