package pasquale.alberico.GestioneEventi.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    @Column(length = 2000)
    private String description;
    private LocalDate date;
    private String location;
    private int seatsAvailable;
    //riferimento all'organizer(unidirezionele)
    private UUID organizerId;
}
