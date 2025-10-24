package pasquale.alberico.GestioneEventi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasquale.alberico.GestioneEventi.DTO.LoginDTO;
import pasquale.alberico.GestioneEventi.DTO.RegisterDTO;
import pasquale.alberico.GestioneEventi.Entities.User;
import pasquale.alberico.GestioneEventi.Services.AuthService;
import pasquale.alberico.GestioneEventi.Services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private AuthService authService;
    @Autowired private UsersService usersService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO body){
        return authService.authenticateAndGetToken(body);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated RegisterDTO body, BindingResult validation){
        if (validation.hasErrors())throw new IllegalArgumentException("Errore di validazione");
        return usersService.register(body);
    }
}
