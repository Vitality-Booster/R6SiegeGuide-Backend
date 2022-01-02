package r6guidebackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullName;

    @Column(unique=true)
    private String username;

    @Column(unique=true)
    private String email;

    private boolean admin;

    public User(String fullName, String email, String username) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.admin = false;
    }
}
