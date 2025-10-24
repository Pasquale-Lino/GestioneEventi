package pasquale.alberico.GestioneEventi.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewBookingDTO(
        @NotNull UUID eventId,
        @NotNull UUID employeeId,
        @NotNull LocalDate bookingDate,
        String notes
) {}
