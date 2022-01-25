package r6guidebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAsync
@CrossOrigin
public class R6GuideBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(R6GuideBackendApplication.class, args);
    }

}
