package pasquale.alberico.GestioneEventi.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID eventId;
    private UUID employeeId; // user id che prenota
    private LocalDate bookingDate; // data per la prenotazione richiesta
    @Column(length = 1000)
    private String notes;
}
