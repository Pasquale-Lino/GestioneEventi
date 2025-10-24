package pasquale.alberico.GestioneEventi.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class User {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(unique = true,nullable = false)
    public String username;
    public String name;
    public String surname;

    @Column(unique = true,nullable = false)
    public String email;
    @Column(nullable = false)
    public String password;//hashed
    @Enumerated(EnumType.STRING)
    public Role role=Role.USER;
    public String avatarUrl;
}
