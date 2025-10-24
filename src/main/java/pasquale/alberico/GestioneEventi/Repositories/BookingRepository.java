package pasquale.alberico.GestioneEventi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pasquale.alberico.GestioneEventi.Entities.Booking;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByEventId(UUID eventId);
    Optional<Booking> findByEmployeeIdAndBookingDateAndEventId(UUID employeeId, LocalDate date,UUID eventId);
    List<Booking> findByEmployeeIdAndBookingDate(UUID employeeId, LocalDate date);
}
