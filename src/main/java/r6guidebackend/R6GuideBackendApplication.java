package r6guidebackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class R6GuideBackendApplication {

    public static void main(String[] args) {

        if(FirebaseApp.getApps().isEmpty()) {
            FileInputStream firebaseAccount = null;
            try {
                firebaseAccount = new FileInputStream("./src/main/resources/firebase-account.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            FirebaseOptions options = null;
            try {
                options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(firebaseAccount))
                        //.setDatabaseUrl()
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FirebaseApp.initializeApp(options);
        }

        SpringApplication.run(R6GuideBackendApplication.class, args);
    }

}
