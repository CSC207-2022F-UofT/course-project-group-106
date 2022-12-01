package UseCases;

import Entities.Student;
import Entities.User;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.lang.reflect.Array;
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
            ArrayList<String> lectureList = new ArrayList<>();
            ArrayList<String> tutorialList = new ArrayList<>();
            if(coursesList.contains("")){
                coursesList.remove("");
            }
            for(String course: coursesList){
                if (lectureList.contains(course)){
                    tutorialList.add(course);
                }
                else{
                    lectureList.add(course);
                }
            }
            StringBuilder courseString = new StringBuilder();
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
                courseString.append("\n");
            }
            courseString.deleteCharAt(courseString.length() - 1);
            return courseString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public ArrayList<String> getFriendList(String userID) {
//        try {
//            User user = this.ub.getUserByID(userID);
//            ArrayList<Student> friendList = ((Student) user).getFriendList();
//            ArrayList<String> friendListString = new ArrayList<>();
//            if (friendList.contains(null)) {
//                return friendListString;
//            }
//            for (Student friend: friendList) {
//                friendListString.add(friend.getFullName());
//            }
//            return friendListString;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public boolean sendFriendRequest(String userID, String viewerUserID) {
        try {
            User viewerUser = this.ub.getUserByID(viewerUserID);
            User receiveRequestUser = this.ub.getUserByID(userID);
            ((Student) viewerUser).sendFriendRequest((Student )receiveRequestUser);
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
