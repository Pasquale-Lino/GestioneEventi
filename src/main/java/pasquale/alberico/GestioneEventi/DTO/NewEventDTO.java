package pasquale.alberico.GestioneEventi.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventDTO (
        @NotBlank String title,
        String description,
        @NotNull @FutureOrPresent LocalDate date,
        @NotBlank String location,
        int seatsAvailable,
        UUID organizerId
) {}
