package r6guidebackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
// Think about Constructor
@NoArgsConstructor
public class Operator
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;
    private String side;
    private String specialUnit;
    private int healthPoints;
    private int speedPoints;
    private int difficultyPoints;
    private String nationality;
    private String uniqueAbility;
    private int health = 0;
    @ManyToOne
//    @JoinColumn(name = "primary_weapon_1_id", nullable = false)
    @JoinColumn(name = "primary_weapon_1_id")
    private Weapon primaryWeapon1;
    @ManyToOne
    @JoinColumn(name = "primary_weapon_2_id")
    private Weapon primaryWeapon2;
    @ManyToOne
//    @JoinColumn(name = "secondary_weapon_1_id", nullable = false)
    @JoinColumn(name = "secondary_weapon_1_id")
    private Weapon secondaryWeapon1;
    @ManyToOne
    @JoinColumn(name = "secondary_weapon_2_id")
    private Weapon secondaryWeapon2;
    @ManyToOne
    @JoinColumn(name = "gadget_1_id")
    private Gadget gadget1;
    @ManyToOne
    @JoinColumn(name = "gadget_2_id")
    private Gadget gadget2;
    private String realFullName;
    private Date dateOfBirth;
    private String countryOfBirth;
    private String cityOfBirth;

    @Column(name = "biography", length = 2048)
    private String biography;

    public Operator(String name, String side, String specialUnit) {
        this.name = name;
        this.side = side;
        this.specialUnit = specialUnit;
    }
}
