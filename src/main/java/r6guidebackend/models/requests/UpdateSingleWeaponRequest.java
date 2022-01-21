package r6guidebackend.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSingleWeaponRequest {
    private String type;
    private int damage;
    private int magazine;
}
