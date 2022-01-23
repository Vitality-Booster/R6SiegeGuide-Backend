package r6guidebackend.models.previews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import r6guidebackend.models.Weapon;

@Getter
@Setter
@NoArgsConstructor
public class WeaponPreview {
    private String name;
    private String type;

    public WeaponPreview(Weapon weapon) {
        this.name = weapon.getName();
        this.type = weapon.getType();
    }
}
