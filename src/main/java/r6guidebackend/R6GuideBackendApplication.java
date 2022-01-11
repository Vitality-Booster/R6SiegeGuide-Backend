package r6guidebackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringProperties;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
@EnableAsync
public class R6GuideBackendApplication {

//    private static Environment environment;
//
//    public R6GuideBackendApplication(Environment environment) {
//        R6GuideBackendApplication.environment = environment;
//    }
    @Value("${spring.profiles.active}")
    private static String activeProfile;

    // public static void main(String[] args) {
    public static void main(String[] args) {
        SpringApplication.run(R6GuideBackendApplication.class, args);
    }

}
