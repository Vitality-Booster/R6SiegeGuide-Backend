package r6guidebackend.models.requests;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String username;
    private String fullName;
    private String password;
}
