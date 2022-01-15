package r6guidebackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteWeaponRequest {
    // Probably add there (or not there) to check if the user that is trying to delete it is an Admin or not
    private String name;
}
