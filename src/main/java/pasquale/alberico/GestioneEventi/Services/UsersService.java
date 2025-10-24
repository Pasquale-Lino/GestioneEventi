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
    public User register(RegisterDTO body){
        usersRepository.findByEmail(body.email()).ifPresent(u->{
            try {
                throw new BadRequestException("Email già in uso");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        usersRepository.findByUsername(body.username()).ifPresent(u -> {
            try {
                throw new BadRequestException("Username already used");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        User u = new User();
        u.setUsername(body.username());
        u.setName(body.name());
        u.setSurname(body.surname());
        u.setEmail(body.email());
        u.setPassword(passwordEncoder.encode(body.password()));
        u.setRole(body.role() == null ? Role.USER : Role.valueOf(body.role()));
        return usersRepository.save(u);
    }
    public User findByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found: " + email));
    }

    public User findById(UUID id){
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
