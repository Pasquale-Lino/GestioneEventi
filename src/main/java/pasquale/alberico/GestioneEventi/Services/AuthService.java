package pasquale.alberico.GestioneEventi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pasquale.alberico.GestioneEventi.DTO.LoginDTO;
import pasquale.alberico.GestioneEventi.Entities.User;
import pasquale.alberico.GestioneEventi.Exceptions.UnauthorizedException;
import pasquale.alberico.GestioneEventi.Security.JWTTools;

@Service
public class AuthService {

    @Autowired private UsersService usersService;
    @Autowired private JWTTools jwtTools;
    @Autowired private PasswordEncoder passwordEncoder;


    public String authenticateAndGetToken(LoginDTO body){
        User found= usersService.findByEmail(body.email());
        if (passwordEncoder.matches(body.password(), found.getPassword())){
            return jwtTools.createToken(found);
        }
        throw new UnauthorizedException("BAD CREDENTIALS");
    }
}
