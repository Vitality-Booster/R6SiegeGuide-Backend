package r6guidebackend.models.requests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSingleOperatorRequest {
    private String side;
    private String specialUnit;
    private int healthPoints;
    private int speedPoints;
    private int difficultyPoints;
    private String nationality;
    private String uniqueAbility;
    private String primaryWeapon1Name;
    private String primaryWeapon2Name;
    private String secondaryWeapon1Name;
    private String secondaryWeapon2Name;
    private String gadget1Name;
    private String gadget2Name;
    private String realFullName;
    private String dateOfBirth;
    private String countryOfBirth;
    private String cityOfBirth;
    private String biography;
}
