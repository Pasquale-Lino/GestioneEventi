package pasquale.alberico.GestioneEventi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO (
        @NotBlank String username,
        @NotBlank String name,
        @NotBlank String surname,
        @Email @NotBlank String email,
        @NotBlank String password,
        String role // "USER" , "ORGANIZER"
){}
