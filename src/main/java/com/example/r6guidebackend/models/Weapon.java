package com.example.r6guidebackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
// Think about Constructor
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String type;
    private int damage;
    private int rof;
    private int magazine;

    @ManyToMany
    @JoinTable(name = "available_sights",
    joinColumns = @JoinColumn(name = "weapon_id"),
    inverseJoinColumns = @JoinColumn(name = "sight_id"))
    private Set<Sight> availableSights;

    @ManyToMany
    @JoinTable(name = "available_barrels",
    joinColumns = @JoinColumn(name = "weapon_id"),
    inverseJoinColumns = @JoinColumn(name = "barrel_id"))
    private Set<Barrel> availableBarrels;

    @ManyToMany
    @JoinTable(name = "available_grips",
    joinColumns = @JoinColumn(name = "weapon_id"),
    inverseJoinColumns = @JoinColumn(name = "grip_id"))
    private Set<Grip> availableGrips;

    @ManyToMany
    @JoinTable(name = "available_under_barrels",
    joinColumns = @JoinColumn(name = "weapon_id"),
    inverseJoinColumns = @JoinColumn(name = "under_barrel_id"))
    private Set<UnderBarrel> availableUnderBarrels;
}
