package pasquale.alberico.GestioneEventi.Services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pasquale.alberico.GestioneEventi.DTO.RegisterDTO;
import pasquale.alberico.GestioneEventi.Entities.Role;
import pasquale.alberico.GestioneEventi.Entities.User;
import pasquale.alberico.GestioneEventi.Exceptions.NotFoundException;
import pasquale.alberico.GestioneEventi.Repositories.UsersRepository;

import java.util.UUID;

@Service
public class UsersService {
    @Autowired private UsersRepository usersRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    public User register(RegisterDTO payload){
        usersRepository.findByEmail(payload.email()).ifPresent(u->{
            try {
                throw new BadRequestException("Email già in uso");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        usersRepository.findByUsername(payload.username()).ifPresent(u -> {
            try {
                throw new BadRequestException("Username already used");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        User u = new User();
        u.setUsername(payload.username());
        u.setName(payload.name());
        u.setSurname(payload.surname());
        u.setEmail(payload.email());
        u.setPassword(passwordEncoder.encode(payload.password()));
        u.setRole(payload.role() == null ? Role.USER : Role.valueOf(payload.role()));
        return usersRepository.save(u);
    }
    public User findByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found: " + email));
    }

    public User findById(UUID id){
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
