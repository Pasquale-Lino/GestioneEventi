package pasquale.alberico.GestioneEventi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
public class User implements UserDetails {
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Questo metodo vogliamo che restituisca una lista di Authorities, cioè dei ruoli dell'utente
        // SimpleGrantedAuthority è una classe che implementa GrantedAuthority e ci serve per convertire il ruolo dell'utente
        // che nel nostro caso è un enum in un oggetto utilizzabile dai meccanismi di Spring Security
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Usa l'email come username per il login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
