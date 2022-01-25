package r6guidebackend.models;

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
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;
    private String info;
    private String locationCity;
    private String locationCountry;

    @OneToMany(mappedBy = "map")
    private Set<Location> locations;
    // playlists (ranked/quick match/etc.) may be added later
}
