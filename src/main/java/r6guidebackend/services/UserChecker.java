package r6guidebackend.services;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import r6guidebackend.services.interfaces.IUserChecker;

@Service
public class UserChecker implements IUserChecker {
    @Override
    public boolean checkIfAdminServerSide() {
//        System.out.println("Got there!!!");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Auth: " + auth.toString());
//        System.out.println("Credentials: " + auth.getCredentials());
//        System.out.println("Details: " + auth.getDetails());
//        var details = auth.getDetails();
//        for (int i = 0; i < ((Object[])details).length; i++) {
//            System.out.println("Details from object that I got: " + ((Object[])details)[i]);
//        }
        return false;
    }
}
