import Database.*;
import Entities.Course;
import Entities.Student;
import GUI.LabelSelectFrame;
import GUI.ProfileFrame;
import GUI.TagMatchFrame;
import GUI.TagSelectFrame;
import UIController.UIController;
import UseCases.CalendarInterpreter;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }
    public static void main(String[] args) throws IOException{
        //essential for database classes

        initializeFirebase();
        FirebaseCollection cbc = new FirebaseCollection();
        FirebaseCollection ubc = new FirebaseCollection();
        FirebaseCollection tbc = new FirebaseCollection();
        UserDataManager ub = new UserDataManager(ubc);
        CourseDataManager cb = new CourseDataManager(cbc, ub);
        TagDataManager tb = new TagDataManager(tbc, ub);


        Student student = new Student("67890", "pass", "John Doe3", "A test subject");
        UIController uic = new UIController(student, cb, ub, tb);
        //TagSelectFrame tsf = new TagSelectFrame(uic);
        LabelSelectFrame lbs = new LabelSelectFrame(uic);
    }

}
