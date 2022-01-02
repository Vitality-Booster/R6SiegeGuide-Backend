package r6guidebackend.models.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private boolean admin;

    public LoginResponse(String username, boolean admin) {
        this.username = username;
        this.admin = admin;
    }
    public LoginResponse() {

    }
}
