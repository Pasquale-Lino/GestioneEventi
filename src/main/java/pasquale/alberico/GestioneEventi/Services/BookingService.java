package pasquale.alberico.GestioneEventi.Services;

import pasquale.alberico.GestioneEventi.Entities.Booking;
import pasquale.alberico.GestioneEventi.Entities.Event;
import pasquale.alberico.GestioneEventi.Exceptions.BadRequestException;
import pasquale.alberico.GestioneEventi.Exceptions.NotFoundException;
import pasquale.alberico.GestioneEventi.DTO.NewBookingDTO;
import pasquale.alberico.GestioneEventi.Repositories.BookingRepository;
import pasquale.alberico.GestioneEventi.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired private BookingRepository bookingRepository;
    @Autowired private EventRepository eventRepository;

    public Booking create(NewBookingDTO dto){
        // basic checks
        Event event = eventRepository.findById(dto.eventId())
                .orElseThrow(() -> new NotFoundException("Event not found " + dto.eventId()));

        // la data di prenotazione deve corrispondere alla data dell'evento
            if (!dto.bookingDate().equals(event.getDate())){
            throw new BadRequestException("La data di prenotazione deve coincidere con la data dell'evento");
        }

        //l'utente non può avere più prenotazioni nello stesso giorno
        bookingRepository.findByEmployeeIdAndBookingDate(dto.employeeId(), dto.bookingDate())
                .stream().findAny()
                .ifPresent(b -> { throw new BadRequestException("Il dipendente ha già una prenotazione in quella data"); });

        //posti disponibili
        //  controllo il numero delle prenotazioni e dei posti disponibili
        long bookedForEvent = bookingRepository.findByEventId(dto.eventId()).size();
        if (bookedForEvent >= event.getSeatsAvailable()){
            throw new BadRequestException("Posti non disponibili per l'evento!");
        }

        Booking b = new Booking();
        b.setEventId(dto.eventId());
        b.setEmployeeId(dto.employeeId());
        b.setBookingDate(dto.bookingDate());
        b.setNotes(dto.notes());
        return bookingRepository.save(b);
    }

    public void cancel(UUID id) {
        bookingRepository.deleteById(id);
    }
}
