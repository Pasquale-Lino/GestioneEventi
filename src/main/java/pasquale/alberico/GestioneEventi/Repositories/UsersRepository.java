package pasquale.alberico.GestioneEventi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pasquale.alberico.GestioneEventi.Entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
