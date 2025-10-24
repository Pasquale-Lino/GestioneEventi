package pasquale.alberico.GestioneEventi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "event_id", insertable = false, updatable = false)
    private UUID eventId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private UUID employeeId;

    // Relazione ManyToOne
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("bookings")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("bookings")
    private User user;

    private LocalDate bookingDate;

    @Column(length = 1000)
    private String notes;
}
